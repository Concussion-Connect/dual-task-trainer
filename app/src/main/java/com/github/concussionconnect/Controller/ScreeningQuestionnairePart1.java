package com.github.concussionconnect.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.github.concussionconnect.R;

import java.util.HashMap;

public class ScreeningQuestionnairePart1 extends Activity implements View.OnClickListener {
    private Button back, next;
    private Bundle bundle;
    private EditText studyID, adminID, adminName, schoolName, yearInSchool, participantOccupation,
            yearsEmployed, contactInfoAddress, contactInfoPhone, contactInfoEmail;
    private CheckBox call, email, sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screening_questionnaire);

        back = (Button) findViewById(R.id.BackFromContactInfo);
        next = (Button) findViewById(R.id.NextToQuestionnaire);

        studyID = (EditText) findViewById(R.id.studyID);
        adminID = (EditText) findViewById(R.id.adminID);
        adminName = (EditText) findViewById(R.id.adminName);

        schoolName = (EditText) findViewById(R.id.schoolName);
        yearInSchool = (EditText) findViewById(R.id.yearInSchool);
        participantOccupation = (EditText) findViewById(R.id.participantOccupation);
        yearsEmployed = (EditText) findViewById(R.id.yearsEmployed);

        contactInfoAddress = (EditText) findViewById(R.id.contactInfoAddress);
        contactInfoPhone = (EditText) findViewById(R.id.contactInfoPhone);
        contactInfoEmail = (EditText) findViewById(R.id.contactInfoEmail);

        sms = (CheckBox) findViewById(R.id.sms);
        call = (CheckBox) findViewById(R.id.call);
        email = (CheckBox) findViewById(R.id.email);

        back.setOnClickListener(this);
        next.setOnClickListener(this);

        bundle = getIntent().getExtras();
    }

    public void onClick(View v) {
        if (v == back) {
            Intent intent = new Intent(this, SignConsentActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        if (v == next) {
            Intent intent = new Intent(this, ScreeningQuestionnairePart2.class);
            HashMap<String, String> studyInfo = new HashMap<>(3);
            studyInfo.put("studyID", studyID.getText().toString());
            studyInfo.put("adminID", adminID.getText().toString());
            studyInfo.put("adminName", adminName.getText().toString());
            bundle.putSerializable("studyInfo", studyInfo);

            HashMap<String, String> contactInfo = new HashMap<>(8);
            contactInfo.put("schoolName", schoolName.getText().toString());
            contactInfo.put("yearInSchool", yearInSchool.getText().toString());
            contactInfo.put("participantOccupation", participantOccupation.getText().toString());
            contactInfo.put("yearsEmployed", yearsEmployed.getText().toString());
            contactInfo.put("contactInfoAddress", contactInfoAddress.getText().toString());
            contactInfo.put("contactInfoPhone", contactInfoPhone.getText().toString());
            contactInfo.put("contactInfoEmail", contactInfoEmail.getText().toString());

            StringBuilder bestWayOfContact = new StringBuilder();
            for (CheckBox contactWay : new CheckBox[]{call, sms, email}) {
                if (contactWay.isChecked()) {
                    bestWayOfContact.append(contactWay.getText().toString()).append(" ");
                }
            }
            contactInfo.put("bestWayOfContact", bestWayOfContact.toString());
            bundle.putSerializable("contactInfo", contactInfo);

            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}