package com.github.concussionconnect.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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



    }

    public void onClick(View v) {


    }
}