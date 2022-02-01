package com.github.concussionconnect.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.github.concussionconnect.Model.ConnectToDB;
import com.github.concussionconnect.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.chip.Chip;

import static android.content.ContentValues.TAG;

public class SCATTrial3Activity extends Activity implements View.OnClickListener{
    private TextView monthsBox;
    private Bundle bundle;
    private Button next;
    private RadioButton yes;
    private RadioButton no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_c_a_t_trial3);

        monthsBox = (TextView) findViewById(R.id.months);
        next = (Button) findViewById(R.id.nextFromTrial3SCAT);
        yes = (RadioButton) findViewById(R.id.YesSCAT3);
        no = (RadioButton) findViewById(R.id.NoSCAT3);
        next.setOnClickListener(this);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        bundle = getIntent().getExtras();
        String adminId = bundle.getString("ID");
        ConnectToDB.updateTrainerSessionTrial(adminId, 3, "scat",
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
        if(v == yes) {
            yes.toggle();
        }
        if(v== no) {
            no.toggle();
        }
        if(v== next) {
           if(yes.isChecked()) {
                bundle.putString("monthsInReverseSCAT3", "Yes");
            } else {
                bundle.putString("monthsInReverseSCAT3", "No");
            }

            Intent intent = new Intent(this, SCATTrial4Activity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}