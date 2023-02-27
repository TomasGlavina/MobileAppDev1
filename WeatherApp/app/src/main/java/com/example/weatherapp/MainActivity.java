package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private String weatherVal;
    private double windVal;
    private double tempVal;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);

    }

    public void fetchWeatherData(View view) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=tampere&units=metric&appid=856e65d8ef6f0694042468033374dfd9";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Log.d("WEATHER_APP", response);
                    parseJsonAndUpdateUI(response);
                }, error -> {});

        queue.add(stringRequest);

    }



    private void parseJsonAndUpdateUI(String response) {
        try {
            JSONObject weatherResponse = new JSONObject(response);
            tempVal = weatherResponse.getJSONObject("main").getDouble("temp");
            windVal = weatherResponse.getJSONObject("wind").getDouble("speed");
            weatherVal = weatherResponse.getJSONArray("weather").getJSONObject(0).getString("main");
            updateUI();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateUI() {
        TextView tempTextView = findViewById(R.id.tempValID);
        tempTextView.setText(tempVal + " C");
        TextView weatherTextView = findViewById(R.id.weatherValID);
        weatherTextView.setText(weatherVal);
        TextView windTextView = findViewById(R.id.windValID);
        windTextView.setText(windVal + "m/s");
    }

    public void moveToForecast(View view){
        Intent intent = new Intent(MainActivity.this, WeatherForecast.class);
        intent.putExtra("key", tempVal);
        intent.putExtra("weather", weatherVal);
        startActivity(intent);

    }

}