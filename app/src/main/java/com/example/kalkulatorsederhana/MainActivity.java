package com.example.kalkulatorsederhana;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private double firstValue = 0;
    private double secondValue = 0;
    private String operator = "";
    private boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);

        // Nomor Tombol Listener
        setNumberButtonListeners();

        // Operator Tombol Listener
        setOperatorButtonListeners();
    }

    private void setNumberButtonListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String currentText = tvDisplay.getText().toString();
                String newText = button.getText().toString();

                if (currentText.equals("0") || isOperatorPressed) {
                    tvDisplay.setText(newText);
                } else {
                    tvDisplay.setText(currentText + newText);
                }

                isOperatorPressed = false;
            }
        };

        findViewById(R.id.btn0).setOnClickListener(listener);
        findViewById(R.id.btn1).setOnClickListener(listener);
        findViewById(R.id.btn2).setOnClickListener(listener);
        findViewById(R.id.btn3).setOnClickListener(listener);
        findViewById(R.id.btn4).setOnClickListener(listener);
        findViewById(R.id.btn5).setOnClickListener(listener);
        findViewById(R.id.btn6).setOnClickListener(listener);
        findViewById(R.id.btn7).setOnClickListener(listener);
        findViewById(R.id.btn8).setOnClickListener(listener);
        findViewById(R.id.btn9).setOnClickListener(listener);
    }

    private void setOperatorButtonListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                operator = button.getText().toString();
                firstValue = Double.parseDouble(tvDisplay.getText().toString());
                isOperatorPressed = true;
            }
        };

        findViewById(R.id.btnAdd).setOnClickListener(listener);
        findViewById(R.id.btnSubtract).setOnClickListener(listener);
        findViewById(R.id.btnMultiply).setOnClickListener(listener);
        findViewById(R.id.btnDivide).setOnClickListener(listener);

        // Equals button
        findViewById(R.id.btnEqual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondValue = Double.parseDouble(tvDisplay.getText().toString());
                double result = 0;

                switch (operator) {
                    case "+":
                        result = firstValue + secondValue;
                        break;
                    case "-":
                        result = firstValue - secondValue;
                        break;
                    case "*":
                        result = firstValue * secondValue;
                        break;
                    case "/":
                        if (secondValue != 0) {
                            result = firstValue / secondValue;
                        } else {
                            tvDisplay.setText("Error");
                            return;
                        }
                        break;
                }

                tvDisplay.setText(String.valueOf(result));
            }
        });

        // Clear button
        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDisplay.setText("0");
                firstValue = 0;
                secondValue = 0;
                operator = "";
            }
        });
    }
}
