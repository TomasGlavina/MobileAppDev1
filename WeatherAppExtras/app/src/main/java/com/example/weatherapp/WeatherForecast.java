package com.example.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class WeatherForecast extends AppCompatActivity {
    private String weatherVal;
    private double tempVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        Bundle extras = getIntent().getExtras();

        TextView weatherNow = findViewById(R.id.todayWeatherID);
        weatherVal = extras.getString("weather");
        weatherNow.setText(weatherVal);

        TextView tempNow = findViewById(R.id.forecastValID);
        tempVal = extras.getDouble("key");
        String tempString = Double.toString(tempVal);
        tempNow.setText(tempString);
    }
    public void moveToWeather(View view){

        Intent intent = new Intent(WeatherForecast.this, MainActivity.class);
        intent.putExtra("weather", weatherVal);
        intent.putExtra("temp", tempVal);

        startActivity(intent);
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        weatherVal = savedInstanceState.getString("WEATHER");
        tempVal = savedInstanceState.getDouble("TEMP");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("TEMP", tempVal);
        outState.putString("WEATHER", weatherVal);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
