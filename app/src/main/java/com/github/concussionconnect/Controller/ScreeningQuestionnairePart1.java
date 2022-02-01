package com.github.concussionconnect.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.concussionconnect.R;

public class ScreeningQuestionnairePart1 extends Activity implements View.OnClickListener {
    private Button back;
    private Button next;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screening_questionnaire);

        back = (Button) findViewById(R.id.BackFromContactInfo);
        next = (Button) findViewById(R.id.NextToQuestionnaire);

        back.setOnClickListener(this);
        next.setOnClickListener(this);

        bundle = getIntent().getExtras();
    }

    public void onClick(View v) {
        if(v == back) {
            Intent intent = new Intent(this, SignConsentActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        if(v == next) {
            Intent intent = new Intent(this, ScreeningQuestionnairePart2.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}