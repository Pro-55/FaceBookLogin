package com.example.admin.testlogin;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private TextView versionText;
    private Switch switchOne, switchTwo;
    private Button buttonLogin, buttonLoginFB, buttonLoginTwitter, buttonLoginGoogle;
    private AlertDialog.Builder mBuilder;
    private AlertDialog alertDialog;
    private static final String TAG = "SettingsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        versionText = findViewById(R.id.versionText);
        versionText.setText("Version: " + BuildConfig.VERSION_NAME);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginDialog();
            }
        });

        switchOne = findViewById(R.id.switchOne);
        switchOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean stateOne) {
                if (stateOne) {
                    Toast.makeText(SettingsActivity.this, "Switch One On!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SettingsActivity.this, "Switch One Off!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switchTwo = findViewById(R.id.switchTwo);
        switchTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean StateTwo) {
                if (StateTwo) {
                    Toast.makeText(SettingsActivity.this, "Switch Two On!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SettingsActivity.this, "Switch Two Off!", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void goToLogin() {
        Intent loginIntent = new Intent(SettingsActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }

    private void openLoginDialog() {
        mBuilder = new AlertDialog.Builder(SettingsActivity.this);
        View view = getLayoutInflater().inflate(R.layout.login_dialog, null);

        buttonLoginGoogle = view.findViewById(R.id.buttonLoginGoogle);
        buttonLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });

        buttonLoginFB = view.findViewById(R.id.buttonLoginFB);
        buttonLoginFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });

        buttonLoginTwitter = view.findViewById(R.id.buttonLoginTwitter);
        buttonLoginTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });

        mBuilder.setView(view);
        alertDialog = mBuilder.create();
        alertDialog.show();
    }
}
