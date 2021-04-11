package com.github.concussionconnect.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.concussionconnect.R;

public class ConsentDescriptionActivity extends Activity implements View.OnClickListener {
    private Button consentButton;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consent_description);

        consentButton = (Button) findViewById(R.id.consentFormButton);
        consentButton.setOnClickListener(this);

        back = (Button) findViewById(R.id.backButton);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v== consentButton)
            startActivity(new Intent(this, ConsentFormActivity.class));

        if(v == back) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}