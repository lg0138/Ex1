package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final char UNINITIALIZED = ' ';
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULT = '*';
    private static final char DIV = '/';

    double num1, num2;
    char operation = UNINITIALIZED;
    double result;
    boolean resultInitialized;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
    }

    public void onPlusClick(View view) {
        updateOperationAndFirstNumber(PLUS);
    }

    public void onMinusClick(View view) {
        updateOperationAndFirstNumber(MINUS);
    }

    public void onMultClick(View view) {
        updateOperationAndFirstNumber(MULT);
    }

    public void onDivClick(View view) {
        updateOperationAndFirstNumber(DIV);;
    }

    private void updateOperationAndFirstNumber(char op) {
        if(operation == UNINITIALIZED) {
            String editTextString = getEditTextString();

            if(editTextString.length() == 0) {
                Toast.makeText(this, "Please enter a number first!", Toast.LENGTH_SHORT).show();
            } else {
                operation = op;
                num1 = Double.parseDouble(editTextString);
                editText.setText("");
                editText.setHint(num1 + " " + operation);
            }
        } else if(resultInitialized == false) {
            if(handleEquals()) {
                num1 = result;
                operation = op;
                editText.setText("");
                editText.setHint(num1 + " " + operation);
                resultInitialized = false;
            }
        }
    }

    public void onACClick(View view) {
        reset();
    }

    public void onEqualClick(View view) {
        handleEquals();
    }

    public void onCreditsClick(View view) {
        Intent si = new Intent(this,secondActivity.class);
        si.putExtra("n", result);
        startActivity(si);
    }

    private boolean handleEquals() {
        if(operation == UNINITIALIZED) {
            Toast.makeText(this, "Please enter two numbers an select an operation first",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        if(resultInitialized) {
            Toast.makeText(this, "Please start over by pressing A/C", Toast.LENGTH_SHORT).show();
            return false;
        }


        String editTextString = getEditTextString();

        if(editTextString.length() == 0) {
            Toast.makeText(this, "Please enter a number first!", Toast.LENGTH_SHORT).show();
            return false;
        }

        num2 = Double.parseDouble(editTextString);

        if(operation == PLUS) {
            result = num1 + num2;
        } else if(operation == MINUS) {
            result = num1 - num2;
        } else if(operation == MULT) {
            result = num1 * num2;
        }else if (num2 == 0.0 && operation == DIV){
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
            reset();
            return false;
        } else {
            result = num1 / num2;
        }

        resultInitialized = true;
        editText.setHint("");
        editText.setText("" + result);
        return true;
    }

    private void reset() {
        editText.setText("");
        editText.setHint("");//רמז
        operation = UNINITIALIZED;
        resultInitialized = false;
    }

    private String getEditTextString() {
        return editText.getText().toString();
    }
}
