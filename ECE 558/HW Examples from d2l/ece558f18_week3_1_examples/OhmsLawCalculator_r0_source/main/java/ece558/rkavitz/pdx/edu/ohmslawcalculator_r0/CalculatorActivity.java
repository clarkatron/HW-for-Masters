package ece558.rkavitz.pdx.edu.ohmslawcalculator_r0;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {
    private static final String TAG = "CalculatorActivity";

    private EditText minput1;
    private EditText minput2;
    private EditText minput3;

    private Button mCalcRButton;
    private Button mCalcVButton;
    private Button mCalcIButton;

    // OnCreate is called when the CalculatorActivity activity
    // is started.  We pretty much always need to call the
    // super.xxx method whenever we override a method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // calls the super.onCreate() method and inflates
        // the layout with an ID of R.id.layout.activity_calculator_const
        // (see R.java)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // returns the EditText objects for each of the text boxes
        // minput1 is the Resistance EditText object, minput2 is
        // the Voltage EditText object, minput3 is the Current
        // EditText object.
        minput1 = (EditText) findViewById(R.id.editText1);
        minput2 = (EditText) findViewById(R.id.editText2);
        minput3 = (EditText) findViewById(R.id.editText3);

        // Handle the Calculate Resistance Button
        // by getting the Calculate Resistance button object
        // The onClick() method listens for a "button clicked"
        // event for the "Calculate Resistance" button. When
        // the event occurs we check that the current and voltage
        // values are valid and calculates R = V / I
        mCalcRButton = (Button) findViewById(R.id.button1);
        mCalcRButton.setOnClickListener(new View.OnClickListener() {
            // this rather obtuse formatting (which makes sense after
            // you stare at it a while) is an example of an anonymous inner
            // class. You are basically passing the entire implementation
            // of the method to mCalcRButton.setOnClickListener() as a nameless
            // class.  This is sort of (I've seen it in 2 or 3 books I've looked
            // at) a "standard" way to do this because you a) put the implementation
            // of the method right where you want to see it and b) since you only
            // use the class once (here) you don't incur the overhead of a named class
            @Override
            public void onClick(View v) {
                // check that the voltage and current values are valid.  If
                // not, pop up a warning to the user and clear all of the inputs
                if (!isValidInput(false, true, true)) {
                    Toast.makeText(CalculatorActivity.this,
                            "Input Error: Voltage or Current is incorrect",
                            Toast.LENGTH_SHORT).show();
                    clear_input();
                } else {
                    // Voltage and current values are valid so
                    // Calculate Resistance = Voltage / Current and put the
                    // answer in the Resistance textbox.
                    double result = Double.valueOf(minput2.getText().toString()) /
                            Double.valueOf(minput3.getText().toString());
                    minput1.setText(String.format("%.03f", result));
                }
            }
        });

        // ---------------------------------------------------------
        // ADDED THE onClick() METHOD FOR THE CALCULATE VOLTAGE BUTTON
        // ---------------------------------------------------------
        // Handle the "Calculate Voltage" button
        mCalcVButton = (Button) findViewById(R.id.button2);
        mCalcVButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check that the Resistance and Current values are valid.  If
                // not, pop up a warning to the user and clear all of the inputs
                if (!isValidInput(true, false, true)) {
                    Toast.makeText(CalculatorActivity.this,
                            "Input Error: Resistance or Current is incorrect",
                            Toast.LENGTH_SHORT).show();
                    clear_input();
                } else {
                    // Resistance and current values are valid so
                    // Calculate Voltage = Current * Resistance / Current and put the
                    // answer in the Resistance textbox.
                    double result = Double.valueOf(minput1.getText().toString()) *
                            Double.valueOf(minput3.getText().toString());
                    minput2.setText(String.format("%.03f", result));
                }
            }
        });

        // ---------------------------------------------------------
        // ADDED THE CODE FOR THE CALCULATE CURRENT BUTTON.  YOU NEED
        // TO FIND THE "CALCULATE CURRENT" BUTTON OBJECT BY ITS ID AND
        // THEN IMPLEMENT AN onClickListener() FOR THE BUTTON
        // ---------------------------------------------------------
        mCalcIButton = (Button) findViewById(R.id.button3);
        mCalcIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check that the voltage and resistance values are valid.  If
                // not, pop up a warning to the user and clear all of the inputs
                if (!isValidInput(true, true, false)) {
                    Toast.makeText(CalculatorActivity.this,
                            "Input Error: Voltage or Resistance is incorrect",
                            Toast.LENGTH_SHORT).show();
                    clear_input();
                } else {
                    // Voltage and current resistance are valid so
                    // Calculate Current = Voltage / Resistance and put the
                    // answer in the Current textbox.
                    double result = Double.valueOf(minput2.getText().toString()) /
                            Double.valueOf(minput1.getText().toString());
                    minput3.setText(String.format("%.03f", result));
                }
            }
        });
    }

    // Override the Activity lifecycle callbacks for logging
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    // Helper methods

    /**
     *
     * Checks if the required input strings are valid.  The caller passes in parameters
     * to specify which values to check.  The assumption, here, is that the user wil
     * enter two of the three values and press the appropriate button to perform the
     * desired calculation
     *
     * @param NeedR		true if the calculation needs a valid resistance String
     * @param NeedV		true if the calculation needss a valid voltage String
     * @param NeedI		true if the calculation needs a valid current String
     *
     * @return			true if the required inputs are valid, false otherwise
     */
    private boolean isValidInput(boolean NeedR, boolean NeedV, boolean NeedI) {
        String resistanceString = minput1.getText().toString();
        String voltageString = minput2.getText().toString();
        String currentString = minput3.getText().toString();

        // Check if resistance <= 0 or if the string is empty
        // We use a float because the user can enter values
        // that aren't integers
        if (NeedR) {
            if (resistanceString.length() == 0) {
                return false;
            } else if (Double.parseDouble(resistanceString) <= 0.00) {
                return false;
            }
        }

        // Do the same check for voltage
        if (NeedV) {
            if (voltageString.length() == 0) {
                return false;
            } else if (Double.parseDouble(voltageString) <= 0.00) {
                return false;
            }
        }

        // Do the same check for current
        if (NeedI) {
            if (currentString.length() == 0) {
                return false;
            } else if (Double.parseDouble(currentString) <= 0.00) {
               return false;
            }
        }
        return true;
    }


    /**
     * Clears the input text edit boxes
     */
    private void clear_input() {
        minput1.setText("");
        minput2.setText("");
        minput3.setText("");

    }
}

