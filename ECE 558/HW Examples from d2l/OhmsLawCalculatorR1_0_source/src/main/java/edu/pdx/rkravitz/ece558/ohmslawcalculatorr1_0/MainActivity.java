/*
 * Ohms Law Calculator R1.0 - Main Activity
 *
 * author: Roy Kravitz (roy.kravitz@pdx.edu)
 *
 * Concept borrowed from "Android Programming step-by-step
 * by Stephan Schwark
 *
 * User selects what to calculate by pressing one of three buttons.
 * All buttons start the calculate activity after passing an EXTRA
 * specifying what is to be calculated
 */

package edu.pdx.rkravitz.ece558.ohmslawcalculatorr1_0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "Ohms Law Calculator Main Activity";

    private Button mCalcOhmsButton;
    private Button mCalcVoltsButton;
    private Button mCalcAmpsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCalcOhmsButton = (Button) findViewById(R.id.calcOhmsButton);
        mCalcOhmsButton.setOnClickListener(ButtonListener);

        mCalcVoltsButton = (Button) findViewById(R.id.calcVoltsButton);
        mCalcVoltsButton.setOnClickListener(ButtonListener);

        mCalcAmpsButton = (Button) findViewById(R.id.calcAmpsButton);
        mCalcAmpsButton.setOnClickListener(ButtonListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        double lastResult;

        if (data != null) {
            lastResult = data.getDoubleExtra(CalcActivity.EXTRA_RESULT, 0.00);
            Toast.makeText(MainActivity.this, ("Last Result was: " + lastResult), Toast.LENGTH_LONG).show();
        }
    }


    View.OnClickListener ButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, CalcActivity.class);

            switch (v.getId()) {
                case R.id.calcOhmsButton:
                    i.putExtra(CalcActivity.EXTRA_CALCWHAT, 0);
                    break;
                case R.id.calcVoltsButton:
                    i.putExtra(CalcActivity.EXTRA_CALCWHAT, 1);
                    break;
                case R.id.calcAmpsButton:
                    i.putExtra(CalcActivity.EXTRA_CALCWHAT, 2);
                    break;
            }
            startActivityForResult(i, 0);
        }
    };
}

