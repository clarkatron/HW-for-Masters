package edu.pdx.clark.project3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class API {
    private final static String TAG = API.class.getSimpleName();

    /* Firebase JSON keys */
    public static final String ADA5IN = "/ADA5IN";
    public static final String ADC3IN = "/ADC3IN";
    public static final String ADC4IN = "/ADC4IN";
    public static final String ADC5IN = "/ADC5IN";
    public static final String DAC1OUT = "/DAC1OUT";
    public static final String PWM3 = "/PWM3";
    public static final String PWM4 = "/PWM4";
    public static final String PWM5 = "/PWM5";
    public static final String PWM6 = "/PWM6";
    public static final String TIMESTAMP = "/TIMESTAMP";

    String[] keyList = {ADA5IN, ADC3IN, ADC4IN, ADC5IN, DAC1OUT, PWM3, PWM4, PWM5, PWM6, TIMESTAMP};

    private double ada5in = 0;
    private double adc3in = 0;
    private double adc4in = 0;
    private double adc5in = 0;
    private double dac1out = 0;
    private double pwm3 = 0;
    private double pwm4 = 0;
    private double pwm5 = 0;
    private double pwm6 = 0;
    private String timestamp = "";

    private DatabaseReference ada5inRef;
    private DatabaseReference adc3inRef;
    private DatabaseReference adc4inRef;
    private DatabaseReference adc5inRef;
    private DatabaseReference dac1outRef;
    private DatabaseReference pwm3Ref;
    private DatabaseReference pwm4Ref;
    private DatabaseReference pwm5Ref;
    private DatabaseReference pwm6Ref;
    private DatabaseReference timestampRef;

    private FirebaseDatabase database;

    private HashMap<String, DatabaseReference> refs;

    /**
     * The api access to firebase
     * @param context The application context in which this api is instantiated
     */
    public API(Context context) {
        FirebaseApp.initializeApp(context);
        assignDatabase();
        assignRefs();
        attachUpdateValueListeners();
    }

    /**
     * Add a listener for when data changes
     * <p>
     * Use ADC3IN, ADC4IN, ... TIMESTAMP, etc as keys.
     */
    public void addAPIListener(String key, APIListener listener) {
        if (!keyIsValid(key)) {
            Log.d(TAG, "INVALID KEY");
            return;
        }

        DatabaseReference ref = refs.get(key);

        if (ref == null) {
            return;
        }

        final String fKey = key;
        final APIListener fListener = listener;
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (fKey.equals(TIMESTAMP)) {
                    String value = dataSnapshot.getValue(String.class);
                    fListener.callback(value);
                } else {
                    Double value = dataSnapshot.getValue(Double.class);
                    if (value != null) {
                        fListener.callback(value);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                /* Do nothing, unused */
            }
        });
    }

    private boolean keyIsValid(String key) {
        for (String s : keyList) {
            if (s.equals(key)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Assign the db field.
     * "Single level of abstraction" for the constructor.
     */
    private void assignDatabase() {
        database = FirebaseDatabase.getInstance();
    }

    /**
     * Assign database refs and store them in maps for later use.
     */
    private void assignRefs() {
        /* Setup db refs */
        ada5inRef = database.getReference(ADA5IN);
        adc3inRef = database.getReference(ADC3IN);
        adc4inRef = database.getReference(ADC4IN);
        adc5inRef = database.getReference(ADC5IN);
        dac1outRef = database.getReference(DAC1OUT);
        pwm3Ref = database.getReference(PWM3);
        pwm4Ref = database.getReference(PWM4);
        pwm5Ref = database.getReference(PWM5);
        pwm6Ref = database.getReference(PWM6);
        timestampRef = database.getReference(TIMESTAMP);

        /* Setup mapping to references, used to attach listeners */
        refs = new HashMap<>();
        refs.put(ADA5IN, ada5inRef);
        refs.put(ADC3IN, adc3inRef);
        refs.put(ADC4IN, adc4inRef);
        refs.put(ADC5IN, adc5inRef);
        refs.put(DAC1OUT, dac1outRef);
        refs.put(PWM3, pwm3Ref);
        refs.put(PWM4, pwm4Ref);
        refs.put(PWM5, pwm5Ref);
        refs.put(PWM6, pwm6Ref);
        refs.put(TIMESTAMP, timestampRef);
    }

    /**
     * These listeners keep the value fields up-to-date with the firebase database.
     */
    private void attachUpdateValueListeners() {
        ada5inRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    ada5in = dataSnapshot.getValue(Double.class);
                } catch (NullPointerException e) {
                    Log.d(TAG, "ADC5IN null ptr");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        adc3inRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    adc3in = dataSnapshot.getValue(Double.class);
                } catch (NullPointerException e) {
                    Log.d(TAG, "null ptr");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        adc4inRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    adc4in = dataSnapshot.getValue(Double.class);
                } catch (NullPointerException e) {
                    Log.d(TAG, "null ptr");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        adc5inRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    adc5in = dataSnapshot.getValue(Double.class);
                } catch (NullPointerException e) {
                    Log.d(TAG, "null ptr");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dac1outRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    dac1out = dataSnapshot.getValue(Double.class);
                } catch (NullPointerException e) {
                    Log.d(TAG, "null ptr");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        pwm3Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    pwm3 = dataSnapshot.getValue(Double.class);
                } catch (NullPointerException e) {
                    Log.d(TAG, "null ptr");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        pwm4Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    pwm4 = dataSnapshot.getValue(Double.class);
                } catch (NullPointerException e) {
                    Log.d(TAG, "null ptr");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        pwm5Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    pwm5 = dataSnapshot.getValue(Double.class);
                } catch (NullPointerException e) {
                    Log.d(TAG, "null ptr");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        pwm6Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    pwm6 = dataSnapshot.getValue(Double.class);
                } catch (NullPointerException e) {
                    Log.d(TAG, "null ptr");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        timestampRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    timestamp = dataSnapshot.getValue(String.class);
                    Log.d(TAG, timestamp);
                } catch (NullPointerException e) {
                    Log.d(TAG, "null ptr");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public double getAda5in() {
        return ada5in;
    }

    public void setAda5in(double ada5in) {
        ada5inRef.setValue(ada5in);
    }

    public double getAdc3in() {
        return adc3in;
    }

    public void setAdc3in(double adc3in) {
        adc3inRef.setValue(adc3in);
    }

    public double getAdc4in() {
        return adc4in;
    }

    public void setAdc4in(double adc4in) {
        adc4inRef.setValue(adc4in);
    }

    public double getAdc5in() {
        return adc5in;
    }

    public void setAdc5in(double adc5in) {
        adc5inRef.setValue(adc5in);
    }

    public double getDac1out() {
        return dac1out;
    }

    public void setDac1out(double dac1out) {
        dac1outRef.setValue(dac1out);
    }

    public double getPwm3() {
        return pwm3;
    }

    public void setPwm3(double pwm3) {
        pwm3Ref.setValue(pwm3);
    }

    public double getPwm4() {
        return pwm4;
    }

    public void setPwm4(double pwm4) {
        pwm4Ref.setValue(pwm4);
    }

    public double getPwm5() {
        return pwm5;
    }

    public void setPwm5(double pwm5) {
        pwm5Ref.setValue(pwm5);
    }

    public double getPwm6() {
        return pwm6;
    }

    public void setPwm6(double pwm6) {
        pwm6Ref.setValue(pwm6);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        timestampRef.setValue(timestamp);
    }
}


