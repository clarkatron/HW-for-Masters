package edu.pdx.clark.project3;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.Object;
import java.util.List;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;
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
    private String deviceName;
    private static final String TAG = "SensorActivity";

    private int pwm_green, pwm_red, pwm_blue;

    private static final String GPIO_LED_PWM = "BCM4";
    private Gpio ledPWM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        FirebaseApp.initializeApp(this);
        databaseRef = FirebaseDatabase.getInstance().getReference();

        PeripheralManager manager = PeripheralManager.getInstance();
        List<String> deviceList = manager.getI2cBusList();
        if (((List) deviceList).isEmpty()) {
            Log.i(TAG, "no i2c busses available");
        }
        else { deviceName = deviceList.get(0); }

        getDataInit(deviceName);
    }

    public void getDataInit(I2cDevice deviceName) {



    }
}
