package edu.pdx.clark.project3;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.function.Function;

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
    private PeripheralManager manager = PeripheralManager.getInstance();
    private I2cDevice picdevice;
    private API api;


    private static final String GPIO_LED_PWM = "BCM4";
    private Gpio ledPWM;

    /**
     * Create the app, setup the i2c device list and set the name of the device.
     * Then we will go into the data acquasition loop.
     *
     * @param savedInstanceState
     */
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
        } catch (IOException ex) { Log.i(TAG, "i2c won't open"); }

        setFireBaseToPICListener(API.DAC1OUT, DAC1OUT_ADDRESS, null);
        setFireBaseToPICListener(API.PWM3, PWM3_ADDRESS, null);
        setFireBaseToPICListener(API.PWM4, PWM4_ADDRESS, null);
        setFireBaseToPICListener(API.PWM5, PWM5_ADDRESS, null);
//        setFireBaseToPICListener(API.PWM6, PWM6_ADDRESS, null);
        setADAListener();
        setADCListeners();
    }

    private void setFireBaseToPICListener(final String event,final int i2cAddress, final Function<Double, Double> process) {
        api.addAPIListener(event, new APIListener() {
            @Override
            public void callback(double value) {

                if(event.equals(API.DAC1OUT)) {Log.d(TAG, "DAC1OUT = " + value);}

                double tmpValue;

                if (process != null) {
                    tmpValue = process.apply(value);
                } else {
                    tmpValue = value;
                }

                byte message = (byte) ((int) tmpValue);
                Log.d(TAG, event + ": " + message);
                writeI2c(i2cAddress, message);

                try {
                    byte checkValue = readI2c(i2cAddress);
                    Log.d(TAG, "Value successfully wrote: " + (checkValue == message) + ", written=" + message + ", read=" + checkValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void callback(String value) {

            }
        });
    }

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
                    e.printStackTrace();
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
                handler.postDelayed(this, 1000);
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

        handler.postDelayed(runnable, 1000);
    }

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
                    e.printStackTrace();
                }
                handler.postDelayed(this, 2000);
            }
        };

        handler.postDelayed(runnable, 2000);
        //handler.postDelayed(runnable, 1000);
    }

    private long getWordFromBytes(byte lsb, byte msb) {
        long base = 0x00;
        base |= (msb << 8) | lsb;
        Log.d(TAG, String.format("base = 0x%04x", base & 0xFFFF));
        return base & 0xFFFF;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (picdevice != null) {
            try {
                picdevice.close();
                picdevice = null;
            } catch (IOException e) { Log.i(TAG, "Unable to close I2C device", e); }
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
        } catch (java.io.IOException e) {
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

}

