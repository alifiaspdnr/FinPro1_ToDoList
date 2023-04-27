package com.example.finpro1_todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

public class HalamanUtamaActivity extends AppCompatActivity {

    Button button_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_halaman_utama);

        button_add = findViewById(R.id.btn_add);
    }
}