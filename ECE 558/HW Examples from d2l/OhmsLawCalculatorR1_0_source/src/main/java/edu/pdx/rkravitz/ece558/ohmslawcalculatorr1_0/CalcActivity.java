/*
 * Ohms Law Calculator - Calculate Activity
 *
 * Author: Roy Kravitz (roy.kravitz@pdx.edu)
 *
 * Concept borrowed from "Android Programming step-by-step
 * by Stephan Schwark
 *
 * Receives an EXTRA from the Main Activity specifying what
 * to calculate.  The Edit Text box hints are changed depending
 * on what values are needed.  The user presses the calculate
 * button to perform the calculation and the results are displayed
 * Press the BACK button on the device to return to the main activity
 */
package edu.pdx.rkravitz.ece558.ohmslawcalculatorr1_0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class CalcActivity extends AppCompatActivity {
    public static final String TAG = "Calculate Activity";

    public static final String EXTRA_CALCWHAT = "ece558.rkavitz.pdx.edu.calcwhat";
    public static final String EXTRA_RESULT = "ece558.rkavitz.pdx.edu.result";
    public static final String SAVED_RESULT_STRING = "ece558.rkavitz.pdx.edu.savedresultstring";

    private int mCalcWhatValue;
    private String mResultString;

    private EditText mEditTextParam1;
    private EditText mEditTextParam2;

    private Button mCalcButton;
    private TextView mResultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        mEditTextParam1 = (EditText) findViewById(R.id.editTextParam1);
        mEditTextParam2 = (EditText) findViewById(R.id.editTextParam2);
        mResultText = (TextView) findViewById(R.id.resultText);

        // retrieve value to calculate from the main activity and set
        // the parameter hints and labels appropriately
        mCalcWhatValue = getIntent().getIntExtra(EXTRA_CALCWHAT, 10);
        setParamHints(mCalcWhatValue);

        // restore the result string on configuration changes
        if (savedInstanceState != null) {
            Log.d(TAG, "Restoring Result String");
            mResultString = savedInstanceState.getString(SAVED_RESULT_STRING);
            mResultText.setText(mResultString);
        }
        else {
            mResultString = getString(R.string.ResultText);
            mResultText.setText(mResultString);
        }

        mCalcButton = (Button) findViewById(R.id.calcButton);
        mCalcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result;

                // calculate result from the parameters that the user entered
                switch(mCalcWhatValue) {
                    case 0:  	// calculate resistance
                        result = CalcResistance();
                        mResultString = String.format(Locale.getDefault(), "%.03f", result) + " Ohms";
                        break;
                    case 1:		// calculate voltage
                        result = CalcVoltage();
                        mResultString = String.format(Locale.getDefault(), "%.03f", result) + " Volts";
                        break;
                    case 2:		// calculate current
                        result = CalcCurrent();
                        mResultString = String.format(Locale.getDefault(), "%.03f", result) + " Amps";
                        break;
                     default:
                         result = 0.00;
                         mResultString = "";
                         break;
                }

                // display the result if it's valid.  Return result
                // to the main activity.
                if (result >= 0.00 ) {
                    mResultText.setText(mResultString);
                    Intent data = new Intent();
                    data.putExtra(EXTRA_RESULT, result);
                    setResult(RESULT_OK, data);
                }
                else {
                    mResultString = getString(R.string.NoResultText);
                    mResultText.setText(mResultString);
                    Intent data = new Intent();
                    data.putExtra(EXTRA_RESULT, result);
                    setResult(RESULT_CANCELED, data);
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // save the result string so it can be restored on configuration changes
        // don't forget to call the superclass method first.
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "Saving Result String");
        savedInstanceState.putString(SAVED_RESULT_STRING, mResultString);
    }

    // Methods to calculate the result

    /**
     * Calculates resistance using Ohms Law (R = V / I).  The two
     * parameters are checked for validity before calculating the result
     *
     * @return double Resistance in ohms or -1.00 if the parameter were invalid.
     */
    private double CalcResistance() {
        if (isValidInput()) {
            double volts = Double.valueOf(mEditTextParam1.getText().toString());
            double amps = Double.valueOf(mEditTextParam2.getText().toString());
            return volts / amps;
        }
        else {
            Toast.makeText(CalcActivity.this,
                    "Input Error: Voltage or Current is incorrect",
                    Toast.LENGTH_SHORT).show();
            clear_input();
            return -1.00;
        }
    }


    /**
     * Calculates voltage using Ohms Law (V = I * R).  The two
     * parameters are checked for validity before calculating the result
     *
     * @return double Voltage in volts or -1.00 if the parameter were invalid.
     */
    private double CalcVoltage() {
        if (isValidInput()) {
            double ohms = Double.valueOf(mEditTextParam1.getText().toString());
            double amps = Double.valueOf(mEditTextParam2.getText().toString());
            return ohms * amps;
        }
        else {
            Toast.makeText(CalcActivity.this,
                    "Input Error: Resitance or Current is incorrect",
                    Toast.LENGTH_SHORT).show();
            clear_input();
            return -1.00;
        }
    }


    /**
     * Calculates current using Ohms Law (I = V / R).  The two
     * parameters are checked for validity before calculating the result
     *
     * @return double Current in amps or -1.00 if the parameter were invalid.
     */
    private double CalcCurrent() {
        if (isValidInput()) {
            double volts = Double.valueOf(mEditTextParam1.getText().toString());
            double ohms = Double.valueOf(mEditTextParam2.getText().toString());
            return volts / ohms;
        }
        else {
            Toast.makeText(CalcActivity.this,
                    "Input Error: Voltage or Resistance is incorrect",
                    Toast.LENGTH_SHORT).show();
            clear_input();
            return -1.00;
        }
    }


    // Helper methods

    /**
     *
     * Checks if the parameter strings are valid by checking whether
     * the string contains a value and that the value is > 0.00
     *
     * @return			true if the input strings are valid, false otherwise
     */
    private boolean isValidInput() {
        String param1String = mEditTextParam1.getText().toString();
        String param2String = mEditTextParam2.getText().toString();
        boolean result = true;

        // check the first parameter
        if ( (param1String.length() == 0) || (Double.parseDouble(param1String) <= 0.00) ) {
            result = false;
        }

        // check the second parameter
        if ( (param2String.length() == 0) || (Double.parseDouble(param2String) <= 0.00) ) {
            result = false;
        }
        return result;
    }


    /**
     * Clears the input text edit boxes
     */
    private void clear_input() {
        mEditTextParam1.setText("");
        mEditTextParam2.setText("");
    }


    /**
     * Sets up the parameter hints based on what value the user
     * wants calculated.
     *
     * @param calcWhat What value to calculate (0: resistance, 1: voltage, 2: current)
     */
    private void setParamHints(int calcWhat) {
        TextView param1Label = (TextView) findViewById(R.id.Param1Label);
        TextView param2Label = (TextView) findViewById(R.id.Param2Label);

        switch (calcWhat) {
            case 0:		// calculate Resistance
                param1Label.setText(R.string.textVoltsLabel);
                mEditTextParam1.setHint(R.string.EnterVoltsHintText);
                param2Label.setText(R.string.textAmpsLabel);
                mEditTextParam2.setHint(R.string.EnterAmpsHintText);
                break;
            case 1:		// calculate Voltage
                param1Label.setText(R.string.textOhmsLabel);
                mEditTextParam1.setHint(R.string.EnterOhmsHintText);
                param2Label.setText(R.string.textAmpsLabel);
                mEditTextParam2.setHint(R.string.EnterAmpsHintText);
                break;
            case 2:		// calculate Current
                param1Label.setText(R.string.textVoltsLabel);
                mEditTextParam1.setHint(R.string.EnterVoltsHintText);
                param2Label.setText(R.string.textOhmsLabel);
                mEditTextParam2.setHint(R.string.EnterOhmsHintText);
                break;
            default:
                param1Label.setText(R.string.textParam1Label);
                mEditTextParam1.setHint(R.string.Param1HintText);
                param2Label.setText(R.string.textParam2Label);
                mEditTextParam2.setHint(R.string.Param2HintText);
                break;
        }
        mResultText.setText(R.string.ResultText);
    }
}

