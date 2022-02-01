package com.github.concussionconnect.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.concussionconnect.Model.ConnectToDB;
import com.github.concussionconnect.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.content.ContentValues.TAG;

public class EndBESSActivity extends Activity implements View.OnClickListener {
    private Button submit;
    private TextView BESSresults;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_b_e_s_s);
        submit = (Button) findViewById(R.id.submitButtonBESS);
        BESSresults = (TextView) findViewById(R.id.BESSResults);
        submit.setOnClickListener(this);
        BESSresults.setText("");
        bundle = getIntent().getExtras();
        BESSresults.append("Name: " + bundle.getString("participantName", "Name Not Added") + "\n");
        BESSresults.append("BESS Trial 1 Errors (Double Leg Errors): " + bundle.getInt("BESSTrial1Errors", 0) + "\n");
        BESSresults.append("BESS Trial 2 Errors (Single Leg Errors): " + bundle.getInt("BESSTrial2Errors", 0) + "\n");
        BESSresults.append("BESS Trial 3 Errors : " + bundle.getString("BESSTrial3Errors", "0") + "\n");
        BESSresults.append("BESS Trial 4 Errors (Tandem Leg Errors): " + bundle.getInt("BESSTrial4Errors", 0) + "\n");

        //unnecessary to have to change the trial back to 0 but may be useful
        bundle = getIntent().getExtras();
        String adminId = bundle.getString("ID");
        ConnectToDB.updateTrainerSessionTrial(adminId, 0, "bess",
                new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Trainer session trial updated!");
                    }
                },
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating trainer session trial", e);
                    }
                }
        );



    }

    public void onClick(View v) {
        if(v == submit) {
            Intent intent = new Intent(this, OpeningPageForTests.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
}