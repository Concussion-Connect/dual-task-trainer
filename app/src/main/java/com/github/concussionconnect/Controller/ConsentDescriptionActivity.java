package com.github.concussionconnect.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.concussionconnect.R;

public class ConsentDescriptionActivity extends Activity implements View.OnClickListener {
    private Button consentButton;
    private Button back;
    private Bundle bundle;
    private Button skipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consent_description);

        consentButton = (Button) findViewById(R.id.consentFormButton);
        consentButton.setOnClickListener(this);

        back = (Button) findViewById(R.id.backButton);
        back.setOnClickListener(this);

        skipButton = (Button) findViewById(R.id.skipButton);
        skipButton.setOnClickListener(this);


        bundle = getIntent().getExtras();

    }

    @Override
    public void onClick(View v) {
        if (v == consentButton) {
            Intent intent = new Intent(this, ConsentFormActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (v == back) {
            startActivity(new Intent(this, LoginActivity.class));
        }

        if (v == skipButton) {
            Intent intent = new Intent(this, ResearchSymptomsActivity.class);
            bundle.putString("participantName", "No Name entered");
            intent.putExtras(bundle);
            startActivity(intent);

        }
    }
}