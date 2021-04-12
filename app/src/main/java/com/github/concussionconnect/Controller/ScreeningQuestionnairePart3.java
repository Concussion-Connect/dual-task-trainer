package com.github.concussionconnect.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.concussionconnect.R;

public class ScreeningQuestionnairePart3 extends Activity implements View.OnClickListener{

    private Button back;
    private Button next;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screening_questionnaire_part3);

        back = (Button) findViewById(R.id.backFromQuest2);
        next = (Button) findViewById(R.id.nextToSymptoms);

        back.setOnClickListener(this);
        next.setOnClickListener(this);
        bundle = getIntent().getExtras();
    }

    public void onClick(View v) {
        if(v == back) {
            startActivity(new Intent(this, ScreeningQuestionnairePart2.class));
        }

        if(v== next) {
            startActivity(new Intent(this, ResearchSymptomsActivity.class));
        }
    }
}