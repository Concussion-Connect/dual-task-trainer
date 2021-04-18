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
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consent_form);

        backButton = (Button) findViewById(R.id.backToKeyInfo);
        backButton.setOnClickListener(this);
        signConsent = (Button) findViewById(R.id.clickToSign);
        signConsent.setOnClickListener(this);
        bundle = getIntent().getExtras();
    }

    @Override
    public void onClick(View v) {
        if(v == backButton) {
            Intent intent = new Intent(this, ConsentDescriptionActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        if(v == signConsent) {
            Intent intent = new Intent(this, SignConsentActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}