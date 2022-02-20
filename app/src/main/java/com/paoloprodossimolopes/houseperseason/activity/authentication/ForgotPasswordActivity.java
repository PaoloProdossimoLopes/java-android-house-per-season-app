package com.paoloprodossimolopes.houseperseason.activity.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.paoloprodossimolopes.houseperseason.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        commonInit();
    }

    private void commonInit() {
        configureComponents();
        configureClicks();
    }

    private void configureClicks() {
        //BACK BUTTON
        findViewById(R.id.backToolbar_button).setOnClickListener(view -> finish());
    }

    private void configureComponents() {
        TextView toolBarTitleTextView = findViewById(R.id.backTollbar_title);
        toolBarTitleTextView.setText(R.string.forgotPasswordScreen_toolBarTitle);
    }
}