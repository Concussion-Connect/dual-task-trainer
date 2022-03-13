package com.github.concussionconnect.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.github.concussionconnect.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            studyInfo.put("Study ID", studyID.getText().toString());
            studyInfo.put("Administrator ID", adminID.getText().toString());
            studyInfo.put("Administrator Name", adminName.getText().toString());
            bundle.putSerializable("studyInfo", studyInfo);

            HashMap<String, String> contactInfo = new HashMap<>(8);
            contactInfo.put("School Name", schoolName.getText().toString());
            contactInfo.put("Current Grade/Year In School", yearInSchool.getText().toString());
            contactInfo.put("Occupation", participantOccupation.getText().toString());
            contactInfo.put("Years Employed", yearsEmployed.getText().toString());
            contactInfo.put("Permanent Address", contactInfoAddress.getText().toString());
            contactInfo.put("Phone", contactInfoPhone.getText().toString());
            contactInfo.put("Email Address", contactInfoEmail.getText().toString());

            addAllIfChecked(new ArrayList<>(Arrays.asList(
                    call, sms, email
            )), contactInfo, "What is the best way to contact you?");
            bundle.putSerializable("contactInfo", contactInfo);

            intent.putExtras(bundle);
            startActivity(intent);
        }


    }

    private void addAllIfChecked(List<CheckBox> checkBoxes, Map<String, String> hashMap, String question) {
        ArrayList<String> checkBoxAnswers = new ArrayList<>(checkBoxes.size());
        for (CheckBox c : checkBoxes) {
            if (c.isChecked()) {
                checkBoxAnswers.add(c.getText().toString());
            }
        }
        hashMap.put(question, String.join(", ", checkBoxAnswers));
    }

}