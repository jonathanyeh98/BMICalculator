/**
 * This is the main activity of the app. It calculates the BMI of a person based on their height and weight.
 * We can change the measurement system from US imperial to metric and the calculation algorithm changes.
 * @authors Jonathan Yeh and Daniel Tolentino
 */

package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityGetAdvice extends AppCompatActivity {
    String BMICode;
    TextView textViewBMICode;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_advice);
        Bundle bundle = getIntent().getExtras();
        BMICode = bundle.getString("BMICode");
        imageView = findViewById(R.id.imageView);
        textViewBMICode = findViewById(R.id.textViewBMICode);
        textViewBMICode.setText(BMICode);
        switch(BMICode)
        {
            case "Underweight":
                textViewBMICode.setText("Eat more food!");
                textViewBMICode.setBackgroundColor(Color.YELLOW);
                imageView.setImageResource(R.drawable.eatmore);
                break;
            case "Normal":
                textViewBMICode.setText("Good Job!");
                textViewBMICode.setBackgroundColor(Color.GREEN);
                imageView.setImageResource(R.drawable.goodjob);
                break;
            case "Overweight":
                textViewBMICode.setText("Exercise more!");
                textViewBMICode.setBackgroundColor(0xFFFFA500); //set to dark orange
                imageView.setImageResource(R.drawable.exercise);
                break;
            case "Obese":
                textViewBMICode.setText("Eat healthier and exercise");
                textViewBMICode.setBackgroundColor(Color.RED);
                imageView.setImageResource(R.drawable.exerciseandeatbetter);
                break;
        }

    }

}
