package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    private static final String CODE_UNDERWEIGHT = "Underweight";
    private static final String CODE_NORMAL = "Normal";
    private static final String CODE_OVERWEIGHT = "Overweight";
    private static final String CODE_OBESE = "Obese";

    Button buttonCalculate;
    Button buttonGetAdvice;
    EditText enterWeight;
    EditText enterHeight;
    TextView textViewBMIOutput;
    RadioGroup radioGroupMeasureSystem;
    Double resultBMI;
    String BMICode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCalculate = findViewById(R.id.buttonCalculate);
        buttonGetAdvice = findViewById(R.id.buttonGetAdvice);
        enterWeight = findViewById(R.id.enterWeight);
        enterHeight = findViewById(R.id.enterHeight);
        textViewBMIOutput = findViewById(R.id.textViewBMIOutput);
        radioGroupMeasureSystem = findViewById(R.id.radioGroupMeasureSystem);

        //set hints to correct measurement system
        switch(radioGroupMeasureSystem.getCheckedRadioButtonId()){
            case (R.id.radioImperial):
                enterHeight.setHint("Enter height in in");
                enterWeight.setHint("Enter weight in lbs");
                break;
            case (R.id.radioMetric):
                enterHeight.setHint("Enter height in cm");
                enterWeight.setHint("Enter weight in kgs");
        }

        //set hints to correct measurement system when there is a change in checked button
        radioGroupMeasureSystem.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case (R.id.radioImperial):
                        enterHeight.setHint("Enter height in in");
                        enterWeight.setHint("Enter weight in lbs");
                        break;
                    case (R.id.radioMetric):
                        enterHeight.setHint("Enter height in cm");
                        enterWeight.setHint("Enter weight in kgs");
                }
            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enterWeight.getText().toString().matches("") && enterHeight.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Must enter weight and height", Toast.LENGTH_SHORT).show();
                }
                else if(enterWeight.getText().toString().matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Must enter weight", Toast.LENGTH_SHORT).show();
                }
                else if(enterHeight.getText().toString().matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Must enter height", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String textWeight = enterWeight.getText().toString();
                    String textHeight = enterHeight.getText().toString();
                    Double doubleWeight = Double.parseDouble(textWeight);
                    Double doubleHeight = Double.parseDouble(textHeight);
                    switch(radioGroupMeasureSystem.getCheckedRadioButtonId()){
                        case (R.id.radioImperial):
                            resultBMI = calculateBMIImperial(doubleHeight,doubleWeight);
                            break;
                        case (R.id.radioMetric):
                            resultBMI = calculateBMIMetric(doubleHeight,doubleWeight);
                            break;
                    }
                    DecimalFormat df2 = new DecimalFormat("#.##");
                    textViewBMIOutput.setText(df2.format(resultBMI));
                }
            }
        });

        buttonGetAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textViewBMIOutput.getText().toString() == "")
                {
                    Toast.makeText(getApplicationContext(), "Must calculate BMI first", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    openAdvicePage();
                }
            }
        });
    }

    /**
     * Calculates BMI Values based on US imperial system
     * @param height holds height of person
     * @param weight holds weight of person
     * @return
     */
    public double calculateBMIImperial(double height, double weight){
        double result = (weight * 703) / (height * height);
        if(result < 18.5)
        {
            this.BMICode = CODE_UNDERWEIGHT;
        }
        else if(result >= 18.5 && result < 24.9)
        {
            this.BMICode = CODE_NORMAL;
        }
        else if(result >= 24.9 && result < 30.0)
        {
            this.BMICode = CODE_OVERWEIGHT;
        }
        else
        {
            this.BMICode = CODE_OBESE;
        }
        return result;
    }

    /**
     * Calculates BMI Values based on the metric system
     * @param height holds height of person
     * @param weight holds weight of person
     * @return
     */
    public double calculateBMIMetric(double height, double weight){
        double result = (weight) / ((height / 100) * (height / 100));
        if(result < 18.5)
        {
            this.BMICode = CODE_UNDERWEIGHT;
        }
        else if(result >= 18.5 && result < 24.9)
        {
            this.BMICode = CODE_NORMAL;
        }
        else if(result >= 24.9 && result < 30.0)
        {
            this.BMICode = CODE_OVERWEIGHT;
        }
        else
        {
            this.BMICode = CODE_OBESE;
        }
        return result;
    }

    /**
     * opens up new window with advice picture
     */
    public void openAdvicePage()
    {
        Intent intent = new Intent(this, ActivityGetAdvice.class);
        Bundle bundle = new Bundle();
        bundle.putString("BMICode",BMICode);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
