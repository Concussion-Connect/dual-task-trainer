package com.github.concussionconnect.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.concussionconnect.R;

public class ScreeningQuestionnairePart2 extends Activity implements View.OnClickListener {

    private Button back, Continue;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screening_questionnaire_part2);

        back = (Button) findViewById(R.id.backFromQuest);
        Continue = (Button) findViewById(R.id.Continue);
        back.setOnClickListener(this);
        Continue.setOnClickListener(this);
        bundle = getIntent().getExtras();

    }

    public void onClick(View v) {
        if(v == back) {
            Intent intent = new Intent(this, ScreeningQuestionnairePart1.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        if(v == Continue) {
            Intent intent = new Intent(this, ScreeningQuestionnairePart3.class);

            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}