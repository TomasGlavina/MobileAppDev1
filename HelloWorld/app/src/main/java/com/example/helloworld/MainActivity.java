package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Basic setup
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing both text and button variables for event handling
        //Button is final because it shouldn't change, only the text
        TextView text = findViewById(R.id.textId);
        final Button button = findViewById(R.id.button_id);

        //checks if clicked button, if so, changes the text underneath
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text.getText().equals("Welcome to my first app!")) {
                    text.setText("Hello World");
                } else {
                    text.setText("Welcome to my first app!");
                }
            }
        });
    }
}