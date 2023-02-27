package com.example.buttonlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeColor(View view){
        Button topButton = findViewById(R.id.topButton_id);
        Button leftButton = findViewById(R.id.leftButton_id);
        Button rightButton = findViewById(R.id.rightButton_id);
        Button bottomButton = findViewById(R.id.bottomButton_id);

        switch(view.getId())
        {
            case R.id.topButton_id:
                setColor(topButton);
                break;
            case R.id.leftButton_id:
                setColor(leftButton);
                break;
            case R.id.rightButton_id:
                setColor(rightButton);
                break;
            case R.id.bottomButton_id:
                setColor(bottomButton);
                break;
            default:
                throw new RuntimeException("Unknow button ID");
        }

    }

    public void setColor(Button button){
        int color = button.getBackgroundTintList().getDefaultColor();
        System.out.println(color);
        if(color == Color.YELLOW) button.setBackgroundTintList(ColorStateList.valueOf(Color.MAGENTA));
        else button.setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
    }
}