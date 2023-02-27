package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private double degrees = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convert(View view) {
        //Getting the data from the views into strings
        TextView inputView = findViewById(R.id.degreeInputId);
        String inputString = inputView.getText().toString();
        TextView typeView = findViewById(R.id.typeToConvertId);
        String typeString = typeView.getText().toString();
        TextView result  =  findViewById(R.id.resultId);
        double convertedDegrees = 0;        //initializing variable for storing the conversion

        //checking that is not empty before inserting data to degrees variable
        if(!inputString.isEmpty()) this.degrees = Double.valueOf(inputString);

        //switch case for calling different formula
        switch (typeString){
            case "Celsius":
                convertedDegrees = convertCelsius(degrees);
                result.setText(roundOffTo2DecPlaces(convertedDegrees) + "° F");
                break;
            case "Fahrenheit":
                convertedDegrees = convertFahrenheit(degrees);
                result.setText(roundOffTo2DecPlaces(convertedDegrees) + "° C");
                break;
        }
    }

    public void switchType(View view) {
        TextView typeToConvert  =  findViewById(R.id.typeToConvertId);
        TextView typeConverted =  findViewById(R.id.typeConvertedId);
        if(typeToConvert.getText().equals("Celsius")){
            typeConverted.setText("Celsius");
            typeToConvert.setText("Fahrenheit");
        } else {
            typeConverted.setText("Fahrenheit");
            typeToConvert.setText("Celsius");
        }

    }

    public double convertCelsius(double input) {
        return ((input*1.8) + 32);
    }

    public double convertFahrenheit(double input){
        return ((input - 32) / 1.8);
    }

    String roundOffTo2DecPlaces(double val)    {
        return String.format("%.2f", val);
    }
}