package com.github.concussionconnect.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.github.concussionconnect.Model.ConnectToDB;
import com.github.concussionconnect.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.content.ContentValues.TAG;

public class DualTrial3Activity extends Activity implements View.OnClickListener{
    private Bundle bundle;

    private TextView monthsBox;

    private Button next;
    private RadioButton yes;
    private RadioButton no;

    private ImageButton minusButton;
    private ImageButton plusButton;
    private EditText errorNumText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dual_trial3);
        monthsBox = (TextView) findViewById(R.id.monthsDual);
        next = (Button) findViewById(R.id.nextFromTrial3Dual);
        yes = (RadioButton) findViewById(R.id.YesDual3);
        no = (RadioButton) findViewById(R.id.NoDual3);
        next.setOnClickListener(this);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);

        next =  (Button) findViewById(R.id.nextFromTrial3Dual);
        minusButton = (ImageButton) findViewById(R.id.minusDual3);
        plusButton = (ImageButton) findViewById(R.id.plusDual3);

        errorNumText = (EditText) findViewById(R.id.errorNumTextDual3);
        minusButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        next.setOnClickListener(this);

        bundle = getIntent().getExtras();
        String adminId = bundle.getString("ID");
        ConnectToDB.updateTrainerSessionTrial(adminId, 3, "dual",
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
       if (v == next) {
            int tandemLegErrors = Integer.valueOf(errorNumText.getText().toString());
            Intent intent = new Intent(this, DualTrial4Activity.class);
            bundle.putInt("TandemLegErrorsDualTrial3", tandemLegErrors);
            if(yes.isChecked()) {
                bundle.putString("monthsInReverseDual3", "Yes");
            } else {
                bundle.putString("monthsInReverseDual3", "No");
            }

            intent.putExtras(bundle);
            startActivity(intent);
        }
        if(v == yes) {
            yes.toggle();
        }
        if(v== no) {
            no.toggle();
        }
        if (v == minusButton) {
            int value = Integer.valueOf(errorNumText.getText().toString());
            if (value > 0) {
                value--;
            }
            errorNumText.setText(String.valueOf(value));
        }
        if (v == plusButton) {
            int value = Integer.valueOf(errorNumText.getText().toString());
            if (value < 10) {
                value++;
            }
            errorNumText.setText(String.valueOf(value));
        }

    }
}