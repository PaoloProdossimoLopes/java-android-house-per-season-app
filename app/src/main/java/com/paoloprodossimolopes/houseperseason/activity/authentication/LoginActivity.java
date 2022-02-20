package com.paoloprodossimolopes.houseperseason.activity.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.paoloprodossimolopes.houseperseason.R;

public class LoginActivity extends AppCompatActivity {

    private EditText emailET;
    private EditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        commonInit();
    }

    private void commonInit() {
        cofigureClicks();
        configureUIComponents();
    }

    private void configureUIComponents() {
        emailET = findViewById(R.id.loginScreen_emailEditText);
        passwordET = findViewById(R.id.loginScreen_passwordEditText);

        configureToolbarComponent();
    }

    private void configureToolbarComponent() {
        ((TextView) findViewById(R.id.backTollbar_title)).setText(R.string.loginScreen_toolbarTitle);
        ((ImageButton) findViewById(R.id.backToolbar_button)).setVisibility(View.INVISIBLE);
    }

    private void cofigureClicks() {

        //forgot password
        findViewById(R.id.loginScreen_forgotTextView).setOnClickListener(view -> forgotPasswordHandle());

        //enter button
        findViewById(R.id.loginScreen_loginButton).setOnClickListener(view -> enterButtonHandleTapped());

        //register button
        findViewById(R.id.loginScreen_createAccountButton).setOnClickListener(view -> goToRegisterActivity());
    }

    private void goToRegisterActivity() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    private void enterButtonHandleTapped() {

    }

    private void forgotPasswordHandle() {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);
    }
}