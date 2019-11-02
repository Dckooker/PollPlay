package com.example.hackathon2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class splashActivity extends AppCompatActivity {

    EditText etUser;
    Button btEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        etUser = findViewById(R.id.et_user);
        btEnter = findViewById(R.id.bt_enter);


        btEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btEnterMethod();
            }
        });
    }

    private void btEnterMethod() {
        String user = etUser.getText().toString();

        Intent next = new Intent(splashActivity.this, mainActivity.class);
        next.putExtra("USER", user);
        startActivity(next);
        finish();
    }



}
