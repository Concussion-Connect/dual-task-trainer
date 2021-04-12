package com.github.concussionconnect.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;

import com.github.concussionconnect.R;

public class OpeningPageForTests extends Activity implements View.OnClickListener {
    private Button reset;
    private Button backToMain;
    private CheckedTextView BESS;
    private CheckedTextView SCAT;
    private CheckedTextView Dual;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_page_for_tests);

        BESS = (CheckedTextView) findViewById(R.id.BESStest);
        SCAT = (CheckedTextView) findViewById(R.id.SCATtest);
        Dual = (CheckedTextView) findViewById(R.id.Dualtest);
        reset = (Button) findViewById(R.id.reset);
        backToMain = (Button) findViewById(R.id.backToMain);
        BESS.setOnClickListener(this);
        SCAT.setOnClickListener(this);
        Dual.setOnClickListener(this);
        reset.setOnClickListener(this);
        bundle = getIntent().getExtras();
    }

    @Override
    public void onClick(View v) {
        if(v== backToMain) {
            startActivity(new Intent(this, LoginActivity.class));
        }
        if(v == BESS) {
            if(!BESS.isChecked()) {
                BESS.setChecked(true);
                startActivity(new Intent(this, BESSTrial1Activity.class));
            } else {
                BESS.setChecked(false);
            }

        }

        if(v == SCAT) {
            if(!SCAT.isChecked()) {
                SCAT.setChecked(true);
            }
        }

        if(v == Dual) {
            if(!Dual.isChecked()) {
                Dual.setChecked(true);
            }
        }
        if(v== reset) {
           if(BESS.isChecked()) {
               BESS.setChecked(false);
           }

           if(SCAT.isChecked()) {
               SCAT.setChecked(false);
           }

           if(Dual.isChecked()) {
               Dual.setChecked(false);
           }
        }
    }
}