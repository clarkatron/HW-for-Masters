package com.group.cb.mobile;

import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.group.cb.api.API;
import com.group.cb.api.APIListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private final static int DAC_INCREMENT = 5;


    private API api;
    private TextView ambientTempVal;
    private TextView adc3Val;
    private TextView adc4Val;
    private TextView adc5Val;
    private Button minus;
    private Button plus;
    private SeekBar pwm4bar;
    private SeekBar pwm5bar;
    private SeekBar pwm6bar;
    private TextView dac1out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = new API(this);
        assignViews();
        attachListeners();
    }

    private void assignViews() {
        ambientTempVal = findViewById(R.id.ambient_temp_val);
        adc3Val = findViewById(R.id.adc3val);
        adc4Val = findViewById(R.id.adc4val);
        adc5Val = findViewById(R.id.adc5val);
        dac1out = findViewById(R.id.dac1outval);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        pwm4bar = findViewById(R.id.pwm4bar);
        pwm5bar = findViewById(R.id.pwm5bar);
        pwm6bar = findViewById(R.id.pwm6bar);
    }

    private void attachListeners() {
        bindViewAndDoubleEndpoint(ambientTempVal, API.ADA5IN);
        bindViewAndDoubleEndpoint(dac1out, API.DAC1OUT);
        bindViewAndDoubleEndpoint(adc3Val, API.ADC3IN);
        bindViewAndDoubleEndpoint(adc4Val, API.ADC4IN);
        bindViewAndDoubleEndpoint(adc5Val, API.ADC5IN);
        bindDACButtonsAndEndpoint();
        bindSeekBarsAndEndpoint();

    }

    private void bindViewAndDoubleEndpoint(TextView view, String endpoint) {
        final TextView ref = view;
        api.addAPIListener(endpoint, new APIListener() {
            @Override
            public void callback(double value) {
                ref.setText(Double.toString(value));
            }

            @Override
            public void callback(String value) {

            }
        });
    }

    private void bindDACButtonsAndEndpoint() {
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                api.setDac1out(api.getDac1out() - DAC_INCREMENT);
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                api.setDac1out(api.getDac1out() + DAC_INCREMENT);
            }
        });
    }

    private void bindSeekBarsAndEndpoint() {
        pwm4bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                api.setPwm4((double) progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        pwm5bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                api.setPwm5((double) progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        pwm6bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                api.setPwm6((double) progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
