package com.example.testmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btTouch = findViewById(R.id.btTouch);
        EditText edit_user = findViewById(R.id.edit_user);

        btTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_user.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, R.string.Toast, Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, R.string.Toast1, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}