package com.github.concussionconnect.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.concussionconnect.R;

public class BESSTrial3Activity extends Activity implements View.OnClickListener{

    private Button nextButton;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_e_s_s_trial3);

        nextButton = (Button) findViewById(R.id.nextFromTrial3BESS);
        nextButton.setOnClickListener(this);

    }

    public void onClick(View v) {
        if(v == nextButton) {
            startActivity(new Intent(this, BESSTrial4Activity.class));
        }
    }
}