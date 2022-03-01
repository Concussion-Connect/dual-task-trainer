package com.github.concussionconnect.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.concussionconnect.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class SignConsentActivity extends Activity implements View.OnClickListener {
    private Button backToConsentInfo;
    private Button nextToQuestionnaire;
    private EditText consentGivenDate;
    private EditText participantID;
    private EditText participantName;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_consent);

        backToConsentInfo = (Button) findViewById(R.id.backToConsentInfo);
        nextToQuestionnaire = (Button) findViewById(R.id.signConsentNext);
        participantID = (EditText) findViewById(R.id.consentID);
        consentGivenDate = (EditText) findViewById(R.id.consentDate);
        participantName = (EditText) findViewById(R.id.consentName);
        participantID.setOnClickListener(this);
        backToConsentInfo.setOnClickListener(this);
        nextToQuestionnaire.setOnClickListener(this);
        bundle = getIntent().getExtras();

    }

    @Override
    public void onClick(View v) {
        Context context = getApplicationContext();
        if (v == backToConsentInfo) {
            Intent intent = new Intent(this, ConsentFormActivity.class);
            startActivity(intent);
        }

        if (v == nextToQuestionnaire) {
            Intent intent = new Intent(this, ScreeningQuestionnairePart1.class);
            String consentDate = consentGivenDate.getText().toString();
            if (consentDate.length() > 0 && isNotValidDate(consentDate)) {
                Toast.makeText(context, "Incorrect date format", Toast.LENGTH_SHORT).show();
                return;
            }
            bundle.putString("consentID", !participantID.getText().toString().equals("") ? participantID.getText().toString() : "No ID entered");
            bundle.putString("consentDate", consentDate);
            String inputName = participantName.getText().toString();
            bundle.putString("participantName", inputName.length() > 1 ? inputName : "No Name entered");
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    private boolean isNotValidDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy", Locale.ENGLISH);
        try {
            LocalDate.parse(input, formatter);
            return false;
        } catch (DateTimeParseException e) {
            return true;
        }
    }
}