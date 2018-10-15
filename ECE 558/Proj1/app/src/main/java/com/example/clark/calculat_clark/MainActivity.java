package com.example.clark.calculat_clark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button multiply, addition,  divide, square_root, subtract, percent, solve;
    private EditText editText, editText2;
    private TextView result;

    double input1, input2, sum;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        divide = (Button) findViewById(R.id.button);
        multiply = (Button) findViewById(R.id.button2);
        subtract = (Button) findViewById(R.id.button3);
        addition = (Button) findViewById(R.id.button4);
        percent = (Button) findViewById(R.id.button5);
        square_root = (Button) findViewById(R.id.button6);
        //solve = (Button) findViewById(R.id.button7);
        //editText = (EditText) findViewById(R.id.editText);
        //editText2 = (EditText) findViewById(R.id.editText2);
        result = (TextView) findViewById(R.id.editText3);

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = (EditText) findViewById(R.id.editText);
                editText2 = (EditText) findViewById(R.id.editText2);

                if (editText.getText().length() == 0 || editText2.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "You Need 2 Operands for this operation", Toast.LENGTH_SHORT).show();
                    if (editText.getText().length() == 0){ editText.requestFocus(); }
                    else
                        editText2.requestFocus();
                }
                else {
                    input1 = Float.parseFloat(editText.getText().toString());
                    input2 = Float.parseFloat(editText2.getText().toString());
                    sum = input1 + input2;
                    result.setText(Double.toString(sum));
                }
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = (EditText) findViewById(R.id.editText);
                editText2 = (EditText) findViewById(R.id.editText2);

                if (editText.getText().length() == 0 || editText2.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "You Need 2 Operands for this operation", Toast.LENGTH_SHORT).show();
                    if (editText.getText().length() == 0){ editText.requestFocus(); }
                    else
                        editText2.requestFocus();
                }
                else {
                    input1 = Float.parseFloat(editText.getText().toString());
                    input2 = Float.parseFloat(editText2.getText().toString());
                    sum = input1 - input2;
                    result.setText(Double.toString(sum));
                }
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = (EditText) findViewById(R.id.editText);
                editText2 = (EditText) findViewById(R.id.editText2);

                if (editText.getText().length() == 0 || editText2.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "You Need 2 Operands for this operation", Toast.LENGTH_SHORT).show();
                    if (editText.getText().length() == 0){ editText.requestFocus(); }
                    else
                        editText2.requestFocus();
                }
                else {
                    input1 = Float.parseFloat(editText.getText().toString());
                    input2 = Float.parseFloat(editText2.getText().toString());
                    sum = input1 * input2;
                    result.setText(Double.toString(sum));
                }
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = (EditText) findViewById(R.id.editText);
                editText2 = (EditText) findViewById(R.id.editText2);

                if (editText.getText().length() == 0 || editText2.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "You Need 2 Operands for this operation", Toast.LENGTH_SHORT).show();
                    if (editText.getText().length() == 0){ editText.requestFocus(); }
                    else
                        editText2.requestFocus();
                }
                else {
                    input1 = Float.parseFloat(editText.getText().toString());
                    input2 = Float.parseFloat(editText2.getText().toString());

                    if (input2 == 0) {
                        Toast.makeText(getApplicationContext(), "Can't Divide by 0", Toast.LENGTH_SHORT).show();
                        editText2.setText("");
                        editText2.requestFocus();
                    }
                    else {
                        sum = input1 / input2;
                        result.setText(Double.toString(sum));
                    }
                }
            }
        });

        square_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = (EditText) findViewById(R.id.editText);
                editText2 = (EditText) findViewById(R.id.editText2);

                if (editText.getText().length() == 0 && editText2.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "You Need 1 Operand for this operation!", Toast.LENGTH_SHORT).show();
                    editText.requestFocus();
                }
                else if (editText.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "You Need 1 Operand for this operation.", Toast.LENGTH_SHORT).show();
                    editText.requestFocus();
                }
                else {
                    input1 = Float.parseFloat(editText.getText().toString());
                    //input2 = Float.parseFloat(editText2.getText().toString());

                    if (input1 < 0) {
                        Toast.makeText(getApplicationContext(), "Can't take roots of negative numbers as they are imaginary", Toast.LENGTH_SHORT).show();
                        editText.setText("");
                        editText.requestFocus();
                    }
                    else {
                        sum = Math.sqrt(input1);
                        result.setText(Double.toString(sum));
                    }
                }
            }
        });

        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = (EditText) findViewById(R.id.editText);
                editText2 = (EditText) findViewById(R.id.editText2);

                if (editText.getText().length() == 0 && editText2.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "You Need 1 Operand for this operation!", Toast.LENGTH_SHORT).show();
                    editText.requestFocus();
                }
                else if (editText.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "You Need 1 Operand for this operation.", Toast.LENGTH_SHORT).show();
                    editText.requestFocus();
                }
                else {
                    input1 = Float.parseFloat(editText.getText().toString());
                    //input2 = Float.parseFloat(editText2.getText().toString());
                    sum = input1/100;
                    result.setText(Double.toString(sum));
                }
            }
        });
    }
}



