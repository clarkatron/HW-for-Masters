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
    private static final String TAG = "SensorActivity";
    private PeripheralManager manager;

    private int i2c_address = 0x08;
    private int pwm_green, pwm_red, pwm_blue;

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

        FirebaseApp.initializeApp(this);
        databaseRef = FirebaseDatabase.getInstance().getReference();

        manager = PeripheralManager.getInstance();
        List<String> deviceList = manager.getI2cBusList();
        if (((List) deviceList).isEmpty()) {
            Log.i(TAG, "no i2c busses available");
        } else {
            deviceName = deviceList.get(0);
        }
        try {
            I2cDevice picdevice = manager.openI2cDevice("PicChip", i2c_address);
        } catch (IOException ex) {
            {
                Log.i(TAG, "i2c won't open");
            }
            //Send device name to the data initialization function
            getDataInit(deviceName);
        }

        /**
         * This will loop and give us data when data changes in the PWM[3-6] fields of the database and
         * in the DAC field of the PIC controller.
         * @param deviceName
         */
        public void getDataInit (I2cDevice deviceName){

            ValueEventListener dataListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

        }

        /**
         * This next function will write or read to the i2c line to access data registers.
         */
        public void writeI2c () {
            try {

                ledPWM = manager.openGpio(GPIO_LED_PWM);
                ledPWM.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
                if (ledPWM == null) {
                    ledPWM.setValue(true);
                }
            } catch (IOException e) {
                Log.i(TAG, "i2c not working");
            }
        }

        public void readI2c () {
            try {
                
                ledPWM = manager.openGpio(GPIO_LED_PWM);
                ledPWM.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
                if (ledPWM == null) {
                    ledPWM.setValue(true);
                }
            } catch (IOException e) {
                Log.i(TAG, "i2c not working");
            }
        }
    }
}
