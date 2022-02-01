package com.github.concussionconnect.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.concussionconnect.R;

public class SignConsentActivity extends Activity implements View.OnClickListener{
    private Button backToConsentInfo;
    private Button nextToQuestionnaire;
    private EditText participantName;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_consent);

        backToConsentInfo = (Button) findViewById(R.id.backToConsentInfo);
        nextToQuestionnaire = (Button) findViewById(R.id.signConsentNext);
        participantName = (EditText) findViewById(R.id.consentName);
        participantName.setOnClickListener(this);
        backToConsentInfo.setOnClickListener(this);
        nextToQuestionnaire.setOnClickListener(this);
        bundle = getIntent().getExtras();

    }

    @Override
    public void onClick(View v) {
        if(v == backToConsentInfo) {
            Intent intent = new Intent(this, ConsentFormActivity.class);
            if(participantName.getText().length() != 0) {
                String name = participantName.getText().toString();
                bundle.putString("participantName", name);
            }
            intent.putExtras(bundle);
            startActivity(intent);
        }

        if(v == nextToQuestionnaire) {
            Intent intent = new Intent(this, ScreeningQuestionnairePart1.class);
            if(participantName.getText().length() != 0) {
                String name = participantName.getText().toString();
                bundle.putString("participantName", name);
            }
            intent.putExtras(bundle);
            startActivity(intent);

        }
    }
}