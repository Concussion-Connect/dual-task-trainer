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
    private CheckedTextView Dual1;
    private CheckedTextView Dual2;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_page_for_tests);

        BESS = (CheckedTextView) findViewById(R.id.BESStest);
        SCAT = (CheckedTextView) findViewById(R.id.SCATtest);
        Dual1 = (CheckedTextView) findViewById(R.id.Dual1test);
        Dual2 = (CheckedTextView) findViewById(R.id.Dual2test);
        reset = (Button) findViewById(R.id.reset);
        backToMain = (Button) findViewById(R.id.backToMain);
        BESS.setOnClickListener(this);
        SCAT.setOnClickListener(this);
        Dual1.setOnClickListener(this);
        Dual2.setOnClickListener(this);
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
                Intent intent = new Intent(this, BESSTrial1Activity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                BESS.setChecked(false);
            }

        }

        if(v == SCAT) {
            if(!SCAT.isChecked()) {
                SCAT.setChecked(true);
                Intent intent = new Intent(this, SCATTrial1Activity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                SCAT.setChecked(false);
            }
        }

        if(v == Dual1) {
            if(!Dual1.isChecked()) {
                Dual1.setChecked(true);
                Intent intent = new Intent(this, DualTrial1Activity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Dual1.setChecked(false);
            }
        }
        if(v == Dual2) {
            if(!Dual2.isChecked()) {
                Dual2.setChecked(true);
                Intent intent = new Intent(this, Dual2Trial1Activity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Dual2.setChecked(false);
            }
        }

        if(v== reset) {
           if(BESS.isChecked()) {
               BESS.setChecked(false);
           }

           if(SCAT.isChecked()) {
               SCAT.setChecked(false);
           }

           if(Dual1.isChecked()) {
               Dual1.setChecked(false);
           }

            if(Dual2.isChecked()) {
                Dual2.setChecked(false);
            }
        }
    }
}