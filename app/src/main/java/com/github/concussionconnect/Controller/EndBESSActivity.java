package com.github.concussionconnect.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.concussionconnect.R;

public class EndBESSActivity extends Activity implements View.OnClickListener{
    private Button cancel;
    private Button submit;
    private TextView BESSresults;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_b_e_s_s);
        cancel = (Button) findViewById(R.id.cancelButtonBESS);
        submit = (Button) findViewById(R.id.submitButtonBESS);
        BESSresults = (TextView) findViewById(R.id.BESSResults);
        cancel.setOnClickListener(this);
        submit.setOnClickListener(this);
        BESSresults.setText("");
        bundle = getIntent().getExtras();
        BESSresults.append("Name: " + bundle.getString("participantName", "Name Not Added") + "\n");
        BESSresults.append("BESS Trial 1 Errors (Double Leg Errors): " + bundle.getInt("BESSTrial4Errors", 0) + "\n");
        BESSresults.append("BESS Trial 2 Errors (Single Leg Errors): " + bundle.getInt("BESSTrial2Errors", 0) + "\n");
        BESSresults.append("BESS Trial 3 Errors : " + bundle.getString("BESSTrial3Errors", "0") + "\n");
        BESSresults.append("BESS Trial 4 Errors (Tandem Leg Errors): " + bundle.getInt("BESSTrial4Errors", 0) + "\n");




    }

    public void onClick(View v) {
        if(v == cancel) {
            Intent intent = new Intent(this, OpeningPageForTests.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        if(v == submit) {
            Intent intent = new Intent(this, OpeningPageForTests.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
}