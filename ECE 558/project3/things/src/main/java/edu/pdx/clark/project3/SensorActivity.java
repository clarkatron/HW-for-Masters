package edu.pdx.clark.project3;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.Object;
import java.util.List;
import java.io.IOException;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;
//import com.google.android.things.pio.PeripheralManagerService;
import com.google.android.things.pio.I2cDevice;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group.cb.api.API;


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

    private DatabaseReference databaseRef;
    public String deviceName;
    private static final String TAG = SensorActivity.class.getSimpleName();
    private PeripheralManager manager;
    private I2cDevice picdevice;
    private API api;

    private int i2c_address = 0x08;
    private int pwm_green, pwm_red, pwm_blue;

    private static final String GPIO_LED_PWM = "BCM4";
    private Gpio ledPWM;
    private API api;

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
        FirebaseApp.initializeApp(this);
        databaseRef = FirebaseDatabase.getInstance().getReference();

        manager = PeripheralManager.getInstance();

        try {
            picdevice = manager.openI2cDevice("PicChip", i2c_address);
            ledPWM = manager.openGpio(GPIO_LED_PWM);
            ledPWM.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            if (picdevice == null) {
                ledPWM.setValue(true);
            }
        } catch (IOException ex) { Log.i(TAG, "i2c won't open"); }
            //Send device name to the data initialization function

        getDataInit();

        api = new API(this);
        api.addAPIListener(API.DAC1OUT, new APIListener() {
            @Override
            public void callback(double value) {
                Log.d(TAG, "DAC1OUT: " + value);
            }

            @Override
            public void callback(String value) {

            }
        });
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
     * This will loop and give us data when data changes in the PWM[3-6] fields of the database and
     * in the DAC field of the PIC controller.
     * @param
     */
    public void getDataInit (){


    }

    /**
     * This next function will write or read to the i2c line to access data registers.
     */
    public void writeI2c (int reg_address, byte data) throws IOException {
        picdevice.writeRegByte(reg_address, data);
    }

    public byte readI2c (int reg_address) throws IOException {
        byte data = picdevice.readRegByte(reg_address);
        return data;
    }
}

