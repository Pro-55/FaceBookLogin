package com.example.admin.testlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private TextView versionText;
    private Switch switchOne, switchTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        versionText = findViewById(R.id.versionText);
        versionText.setText("Version: " + BuildConfig.VERSION_NAME);

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
}
