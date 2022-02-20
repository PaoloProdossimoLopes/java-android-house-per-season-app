package com.paoloprodossimolopes.houseperseason.activity.authentication;

import static com.paoloprodossimolopes.houseperseason.R.color.house_per_season_light_blue;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.paoloprodossimolopes.houseperseason.R;
import com.paoloprodossimolopes.houseperseason.activity.MainActivity;
import com.paoloprodossimolopes.houseperseason.model.UserAuth;

import java.util.Timer;
import java.util.TimerTask;

public class RegistrationActivity extends AppCompatActivity {

    //UI Components
    private EditText nameET;
    private EditText phoneET;
    private EditText emailET;
    private EditText passwordET;

    private Button registerButton;

    private ProgressBar progressbarIndicator;

    //Properties
    private boolean informationsIsvalid = false;

    private static void completion() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        commonInit();
    }

    private void commonInit() {
        configureComponents();
        configureClicks();
    }

    private void configureClicks() {
        //BACK BUTTON IN TOOLBAR
        findViewById(R.id.backToolbar_button).setOnClickListener(view -> finish());

        //REGISTER BUTTON
        registerButton.setOnClickListener(view -> registerButtonHandleTapped());
    }

    private void configureComponents() {
        TextView toolBarTitleTextView = findViewById(R.id.backTollbar_title);
        toolBarTitleTextView.setText(R.string.registerScreen_toolBarTitle);

        nameET = findViewById(R.id.registerScreen_nameEditText);
        phoneET = findViewById(R.id.registerScreen_phoneEditText);
        emailET = findViewById(R.id.registerScreen_emailEditText);
        passwordET = findViewById(R.id.registerScreen_passwordEditText);

        registerButton = findViewById(R.id.registerScreen_loginButton);

        progressbarIndicator = findViewById(R.id.registerScreen_progressBar);
    }

    @SuppressLint("ResourceAsColor")
    private void registerButtonHandleTapped() {
        validadeFields();
    }

    private void validadeFields() {

        if (nameET.getText().toString().isEmpty()) { //Validate name
            nameET.requestFocus();
            nameET.setError("Coloque um nome valido");
            informationsIsvalid = false;
            return;
        } else if (phoneET.getText().toString().isEmpty()) { // Validate phone number
            phoneET.requestFocus();
            phoneET.setError("Coloque um numero valido");
            informationsIsvalid = false;
            return;
        } else if (passwordET.getText().toString().isEmpty()) { // validate email
            emailET.requestFocus();
            emailET.setError("Coloque um email valido");
            informationsIsvalid = false;
            return;
        } else if (emailET.getText().toString().isEmpty()) { // validate password
            passwordET.requestFocus();
            passwordET.setError("Coloque uma senha valida");
            informationsIsvalid = false;
            return;
        } else { // if all ok dirve us to home and log in firebase
            informationsIsvalid = true;

            progressbarIndicator.setVisibility(View.VISIBLE);;// start
            registerUser();
        }
    }

    private void registerUser() {
        if (informationsIsvalid) {
            UserAuth user = new UserAuth();
            user.setName(nameET.getText().toString());
            user.setPhone(phoneET.getText().toString());
            user.setEmail(emailET.getText().toString());
            user.setPassword(passwordET.getText().toString());
            user.registerMeOnFirebaseAuth((isSuccesful, taskError) -> {
                if (isSuccesful && taskError == null) {
                    Toast.makeText(this, "Usuario cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, taskError, Toast.LENGTH_LONG).show();
                }
                progressbarIndicator.setVisibility(View.INVISIBLE);
                goToHome();
                return;
            });
        }
    }

    private void goToHome() {
        finish(); // close this before open other activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}