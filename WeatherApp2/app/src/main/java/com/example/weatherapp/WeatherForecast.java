package com.example.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WeatherForecast extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        Bundle extras = getIntent().getExtras();
        TextView weatherNow = findViewById(R.id.todayWeatherID);
        String weatherString = extras.getString("weather");
        weatherNow.setText(weatherString);
        TextView tempNow = findViewById(R.id.forecastValID);
        String tempString = Double.toString(extras.getDouble("key"));
        tempNow.setText(tempString + " C");
    }
    public void moveToWeather(View view){

        Intent intent = new Intent(WeatherForecast.this, MainActivity.class);


        startActivity(intent);
    }

}
