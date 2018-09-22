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

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

public class SettingsActivity extends AppCompatActivity {

    private TextView versionText;
    private Switch switchOne, switchTwo;
    private Button buttonLogin, buttonLoginFB, buttonLoginTwitter, buttonLoginGoogle;
    private AlertDialog.Builder mBuilder;
    private AlertDialog alertDialog;
    private CallbackManager callbackManager;

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

                callbackManager = CallbackManager.Factory.create();

                LoginManager.getInstance().registerCallback(callbackManager,
                        new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {
                                // App code
                            }

                            @Override
                            public void onCancel() {
                                // App code
                            }

                            @Override
                            public void onError(FacebookException exception) {
                                // App code
                            }
                        });

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
