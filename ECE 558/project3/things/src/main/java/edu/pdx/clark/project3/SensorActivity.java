package edu.pdx.clark.project3;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;
//import com.google.android.things.pio.PeripheralManagerService;
import com.google.android.things.pio.I2cDevice;

import com.group.cb.api.API;
import com.group.cb.api.APIListener;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 *
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */


public class SensorActivity extends Activity {

    private static final int DAC1OUT_ADDRESS = 0x04;
    private static final int PWM3_ADDRESS = 0x00;
    private static final int PWM4_ADDRESS = 0x01;
    private static final int PWM5_ADDRESS = 0x02;
    private static final int PWM6_ADDRESS = 0x03;
    private static final int SLAVE_ADDRESS = 0x08;
    private static final double SUPPLY_VOLTAGE = 3.3;
    private static final String TAG = SensorActivity.class.getSimpleName();
    public static final int DELAY_MILLIS = 2000;
    private PeripheralManager manager = PeripheralManager.getInstance();
    private I2cDevice picdevice;
    private API api;


    private static final String GPIO_LED_PWM = "BCM4";
    private Gpio ledPWM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        api = new API(this);

        try {
            picdevice = manager.openI2cDevice("I2C1", SLAVE_ADDRESS);
            ledPWM = manager.openGpio(GPIO_LED_PWM);
            ledPWM.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            if (picdevice == null) {
                ledPWM.setValue(true);
            }
        } catch (IOException ex) {
            Log.i(TAG, "i2c won't open");
            handleI2cIOException(ex);
        }

        setFireBaseToPICListener(API.DAC1OUT, DAC1OUT_ADDRESS);
        setPWM345Listeners();
        setADAListener();
        setADCListeners();
    }

    /**
     * @brief Sets the database listeners for PWMs 3, 4, and 5.
     *
     * Control the colors of an RGB LED connected to the PIC by adjusting the duty cycle of
     * three of the PIC PWM channels. The duty cycles will be set in the mobile app and written to
     * the Firebase database. The RPI reads the values from Firebase and tells the PIC to adjust the
     * PWM values according to slider values in the mobile app.
     */
    private void setPWM345Listeners() {
        setFireBaseToPICListener(API.PWM3, PWM3_ADDRESS);
        setFireBaseToPICListener(API.PWM4, PWM4_ADDRESS);
        setFireBaseToPICListener(API.PWM5, PWM5_ADDRESS);
    }

    /**
     * @brief Set a FireBase data change to trigger I2C write on the PIC
     * @param event The API FireBase event to get the data from
     * @param i2cAddress The I2C address to write to on the PIC
     */
    private void setFireBaseToPICListener(final String event,final int i2cAddress) {
        api.addAPIListener(event, new APIListener() {
            @Override
            public void callback(double value) {
                byte message = (byte) ((int) value);
                Log.d(TAG, event + ": " + message);
                writeI2c(i2cAddress, message);

                try {
                    byte checkValue = readI2c(i2cAddress);
                    Log.d(TAG, "Value successfully wrote: " + (checkValue == message) + ", written=" + message + ", read=" + checkValue);
                } catch (IOException e) {
                    handleI2cIOException(e);
                }
            }

            @Override
            public void callback(String value) {

            }
        });
    }

    /**
     * @brief Set the ADA listeners for FireBase and device communication
     *
     * Based on the current ambient temperature (TMP36), set the duty cycle of the PWM6
     * channel of the PIC to adjust the speed of the 6V hobbyist motor.
     */
    private void setADAListener() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                byte lsb;
                byte msb;
                try {
                    lsb = readI2c(0x05);
                    msb = readI2c(0x06);
                } catch (IOException e) {
                    handleI2cIOException(e);
                    return;
                }

                /* Swap msb and lsb */
                double result = (double)((int) (getWordFromBytes(lsb, msb)));
                result = Math.abs(result);

                if (result == 0) {
                    return;
                }

                double rawTemp = (result / 1024.0) * SUPPLY_VOLTAGE * 100;
                double tempFahr = rawTemp * 1.8;

                api.setAda5in(tempFahr);
                handler.postDelayed(this, DELAY_MILLIS / 2);
                Log.d(TAG, "temperature = " + tempFahr);

                double val;
                double tempCels = rawTemp - 27.5;
                if (15 >= tempCels) {
                    Log.d(TAG, "Set duty to 80%");
                    val = 80;
                } else if (15 < tempCels && tempCels <= 18) {
                    Log.d(TAG, "Set duty to 30%");
                    val = 30;
                } else if (18 < tempCels && tempCels <= 22) {
                    Log.d(TAG, "Set duty to 50%");
                    val = 50;
                } else if (22 < tempCels && tempCels <= 25) {
                    Log.d(TAG, "Set duty to 70%");
                    val = 70;
                } else {
                    Log.d(TAG, "Set duty to 40%");
                    val = 40;
                }

                writeI2c(PWM6_ADDRESS, (byte) val);
                api.setPwm6(val);
            }
        };

        handler.postDelayed(runnable, DELAY_MILLIS / 2);
    }

    /**
     *  @brief Sets the ADC listeners for Firebase to ADC communication
     *
     *  Continuously read the ADC channels of the PIC microcontroller using the I2C protocol and update the
     *  values in the FireBase database and in the mobile app.
     */
    void setADCListeners() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    short adc3value = readI2cWord(0x07);
                    short adc4value = readI2cWord(0x09);
                    short adc5value = readI2cWord(0x0b);
                    
                    Log.d(TAG, String.format("ADC3=%d, ADC4=%d, ADC5=%d", adc3value, adc4value, adc5value));
                    api.setAdc3in(adc3value);
                    api.setAdc4in(adc4value);
                    api.setAdc5in(adc5value);
                } catch (IOException e) {
                    handleI2cIOException(e);
                }
                handler.postDelayed(this, DELAY_MILLIS);
            }
        };

        handler.postDelayed(runnable, DELAY_MILLIS);
    }

    private long getWordFromBytes(byte lsb, byte msb) {
        long base = 0x00;
        base |= (msb << 8) | lsb;
        return base & 0xFFFF;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (picdevice != null) {
            try {
                picdevice.close();
                picdevice = null;
            } catch (IOException e) {
                Log.i(TAG, "Unable to close I2C device", e);
                handleI2cIOException(e);
            }
        }
    }

    /**
     * This next function will write or read to the i2c line to access data registers.
     */
    public void writeI2c (int reg_address, byte data) {
        try {
            if (picdevice != null) {
                picdevice.writeRegByte(reg_address, data);
            }
        } catch (IOException e) {
            handleI2cIOException(e);
            Log.d(TAG, "writeI2c failed" + e);
        }
    }

    public byte readI2c (int reg_address) throws IOException {
        byte data = 0;
        if (picdevice != null) {
                data = picdevice.readRegByte(reg_address);
        }
        return data;
    }

    public short readI2cWord (int reg_address) throws IOException {
        short data = 0;
        if (picdevice != null) {
            data = picdevice.readRegWord(reg_address);
        }
        return data;
    }

    /**
     * Blink LED in 1 second intervals
     *
     * Set a loop to turn ON the LED every two seconds.
     * After a one second delay, set a loop to turn OFF the led every two seconds.
     * This creates an ON/OFF cycle of one second.
     */
    private void doLED() {
        final Handler handler = new Handler();
        Runnable onRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    ledPWM.setValue(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                handler.postDelayed(this, DELAY_MILLIS);
            }
        };

        Runnable startOffRunnable = new Runnable() {
            @Override
            public void run() {
                Runnable offRunnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ledPWM.setValue(false);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        handler.postDelayed(this, DELAY_MILLIS);
                    }
                };

                handler.postDelayed(offRunnable, DELAY_MILLIS);
            }
        };

        handler.postDelayed(onRunnable, DELAY_MILLIS);
        handler.postDelayed(startOffRunnable, DELAY_MILLIS / 2);
    }

    private void handleI2cIOException(IOException e) {
        e.printStackTrace();
        doLED();
    }
}

