package com.example.admin.testlogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    String Head, Info;
    TextView headSecond, infoSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Head = getIntent().getStringExtra("Head");
        Info = getIntent().getStringExtra("Info");

        headSecond = findViewById(R.id.secondHead);
        infoSecond = findViewById(R.id.secondInfo);

        headSecond.setText(Head);
        infoSecond.setText(Info);
    }

    public static class LoginActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
        }
    }
}
