package com.github.concussionconnect.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.concussionconnect.R;

public class ConsentFormActivity extends Activity implements View.OnClickListener {

    private Button backButton;
    private Button signConsent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consent_form);

        backButton = (Button) findViewById(R.id.backToKeyInfo);
        backButton.setOnClickListener(this);
        signConsent = (Button) findViewById(R.id.clickToSign);
        signConsent.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == backButton) {
            startActivity(new Intent(this, ConsentDescriptionActivity.class));
        }

        if(v == signConsent) {
            startActivity(new Intent(this, SignConsentActivity.class));
        }
    }
}