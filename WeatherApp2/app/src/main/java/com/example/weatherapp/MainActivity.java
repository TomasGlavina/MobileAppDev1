package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.AlarmClock;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("WIND", windVal);
        outState.putDouble("TEMPERATURE", tempVal);
        outState.putString("WEATHER_DESCRIPTION", weatherVal);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        weatherVal = savedInstanceState.getString("WEATHER_DESCRIPTION");
        tempVal = savedInstanceState.getDouble("TEMPERATURE");
        windVal = savedInstanceState.getDouble("WIND");

        updateUI();
    }

    public void fetchWeatherData(View view) {
	String apiKey = "";
        String url = "https://api.openweathermap.org/data/2.5/weather?q=tampere&units=metric&appid=" + apiKey;
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

    public void openMaps(View view) {
        Uri geoLocation = Uri.parse("geo:0,0?q=Kuntokatu+3%2C%20Tampere");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        try{
            startActivity(intent);
        }
        catch (ActivityNotFoundException e) {
               //not found
           }
    }

    public void addAlarm(View view) {
            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                    .putExtra(AlarmClock.EXTRA_MESSAGE, "wake up!")
                    .putExtra(AlarmClock.EXTRA_HOUR, 9)
                    .putExtra(AlarmClock.EXTRA_MINUTES, 00);
           try{
                startActivity(intent);
            }
           catch (ActivityNotFoundException e) {
               //not found
           }
    }

    public void openTuni(View view) {
        String urlString = "https://www.tuni.fi";
        Uri uri = Uri.parse(urlString);

        Intent openWebPage = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(openWebPage);
        } catch (ActivityNotFoundException e) {
            System.out.println("No web browser found: " + e);
        }
    }
}
