package com.example.seathru;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = findViewById(R.id.go_search);
       btn1.setOnClickListener(new Button.OnClickListener(){
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, Search_beach.class);
               startActivity(intent);
           }
       });
        Button btn2 = findViewById(R.id.go_theme);
        btn2.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThemeLibrary.class);
                startActivity(intent);
            }
        });
    }

}