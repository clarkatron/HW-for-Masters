package com.example.clark.calculat_clark;

// Setting up the importing libraries.
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //setting up the variables for the buttons
    Button multiply, addition,  divide, square_root, subtract, percent, solve;
    //setting up the text fields
    private EditText editText, editText2;
    private TextView result;
    //setting up the read-in variables
    double input1, input2, sum;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this is our oncreate function with our button setup and getting id's
        //we are also setting up onclicklisteners (interrupts) for each button
        //we also set up our view of the Content
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        divide = (Button) findViewById(R.id.button);
        multiply = (Button) findViewById(R.id.button2);
        subtract = (Button) findViewById(R.id.button3);
        addition = (Button) findViewById(R.id.button4);
        percent = (Button) findViewById(R.id.button5);
        square_root = (Button) findViewById(R.id.button6);

        result = (TextView) findViewById(R.id.editText3);

        // Addition setup and listener. The error components for this are where you need 2 operands
        // for the function. It will also clear and request focus to whatever operand field is empty.
        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = (EditText) findViewById(R.id.editText);
                editText2 = (EditText) findViewById(R.id.editText2);

                // handling the error conditions and requesting focus to whatever field is causing the
                // problems.
                if (editText.getText().length() == 0 || editText2.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "You Need 2 Operands for this operation", Toast.LENGTH_SHORT).show();
                    if (editText.getText().length() == 0){ editText.requestFocus(); }
                    else
                        editText2.requestFocus();
                }
                else {
                    // parsing the entry and setting the result field.
                    input1 = Float.parseFloat(editText.getText().toString());
                    input2 = Float.parseFloat(editText2.getText().toString());
                    sum = input1 + input2;
                    result.setText(Double.toString(sum));
                    editText.requestFocus();
                }
            }
        });

       // Subtraction setup and listener. The error components for this are where you need 2 operands
       // for the function. It will also clear and request focus to whatever operand field is empty.
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = (EditText) findViewById(R.id.editText);
                editText2 = (EditText) findViewById(R.id.editText2);

                // handling the error conditions and requesting focus to whatever field is causing the
                // problems.
                if (editText.getText().length() == 0 || editText2.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "You Need 2 Operands for this operation", Toast.LENGTH_SHORT).show();
                    if (editText.getText().length() == 0){ editText.requestFocus(); }
                    else
                        editText2.requestFocus();
                }
                else {
                    // parsing the entry and setting the result field.
                    input1 = Float.parseFloat(editText.getText().toString());
                    input2 = Float.parseFloat(editText2.getText().toString());
                    sum = input1 - input2;
                    result.setText(Double.toString(sum));
                    editText.requestFocus();
                }
            }
        });

       // Multiplication setup and listener. The error components for this are where you need 2 operands
       // for the function. It will also clear and request focus to whatever operand field is empty.
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = (EditText) findViewById(R.id.editText);
                editText2 = (EditText) findViewById(R.id.editText2);

                // handling the error conditions and requesting focus to whatever field is causing the
                // problems.
                if (editText.getText().length() == 0 || editText2.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "You Need 2 Operands for this operation", Toast.LENGTH_SHORT).show();
                    if (editText.getText().length() == 0){ editText.requestFocus(); }
                    else
                        editText2.requestFocus();
                }
                else {
                    // parsing the entry and setting the result field.
                    input1 = Float.parseFloat(editText.getText().toString());
                    input2 = Float.parseFloat(editText2.getText().toString());
                    sum = input1 * input2;
                    result.setText(Double.toString(sum));
                    editText.requestFocus();
                }
            }
        });

       // Division setup and listener. The error components for this are where you need 2 operands
       // for the function. There will also be an error message if operand 2 is a zero.
       // It will also clear and request focus to whatever operand field is empty or on the zero
       // condition.
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = (EditText) findViewById(R.id.editText);
                editText2 = (EditText) findViewById(R.id.editText2);

                // handling the error conditions and requesting focus to whatever field is causing the
                // problems.
                if (editText.getText().length() == 0 || editText2.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "You Need 2 Operands for this operation", Toast.LENGTH_SHORT).show();
                    if (editText.getText().length() == 0){ editText.requestFocus(); }
                    else
                        editText2.requestFocus();
                }
                else {
                    input1 = Float.parseFloat(editText.getText().toString());
                    input2 = Float.parseFloat(editText2.getText().toString());

                    // handling the divide by 0 error condition.
                    if (input2 == 0) {
                        Toast.makeText(getApplicationContext(), "Can't Divide by 0", Toast.LENGTH_SHORT).show();
                        editText2.setText("");
                        editText2.requestFocus();
                    }
                    else {
                        // parsing the entry and setting the result field.
                        sum = input1 / input2;
                        result.setText(Double.toString(sum));
                        editText.requestFocus();
                    }
                }
            }
        });

        // Square Root setup and listener. The error components for this are where you need 1 operand
        // for the function and you get 0. There will also be an error message if operand 1 is negative
        // or if operand 1 is emty.  It will also clear and request focus to whatever operand field is
        // is having the error condition.
        square_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = (EditText) findViewById(R.id.editText);
                editText2 = (EditText) findViewById(R.id.editText2);

                // handling the error conditions and requesting focus to whatever field is causing the
                // problems.
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

                    // handling the negative number error conditions.
                    if (input1 < 0) {
                        Toast.makeText(getApplicationContext(), "Can't take roots of negative numbers as they are imaginary", Toast.LENGTH_SHORT).show();
                        editText.setText("");
                        editText.requestFocus();
                    }
                    else {
                        // parsing the entry and setting the result field.
                        sum = Math.sqrt(input1);
                        result.setText(Double.toString(sum));
                        editText.requestFocus();
                    }
                }
            }
        });

        // Percent function setup and listener. For this function there is an error condition when
        // you get 0 Operands or when there is only an entry in the second operand field. This will
        // handle negative numbers as well as positive.
        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = (EditText) findViewById(R.id.editText);
                editText2 = (EditText) findViewById(R.id.editText2);

                // the next two parts of the if statements handle the operand problem with it having 0
                // operands or if there is only entry in the second operand field.
                if (editText.getText().length() == 0 && editText2.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "You Need 1 Operand for this operation!", Toast.LENGTH_SHORT).show();
                    editText.requestFocus();
                }
                else if (editText.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "You Need 1 Operand for this operation.", Toast.LENGTH_SHORT).show();
                    editText.requestFocus();
                }
                else {
                    // parsing the entry and setting the result field.
                    input1 = Float.parseFloat(editText.getText().toString());
                    sum = input1/100;
                    result.setText(Double.toString(sum));
                    editText.requestFocus();
                }
            }
        });
    }
}



