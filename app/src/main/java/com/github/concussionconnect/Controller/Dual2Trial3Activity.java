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

public class Dual2Trial3Activity extends Activity implements View.OnClickListener{
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
        setContentView(R.layout.activity_dual2_trial3);
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

        monthsBox = (TextView) findViewById(R.id.monthsDual2);
        next = (Button) findViewById(R.id.nextFromTrial3Dual2);
        yes = (RadioButton) findViewById(R.id.YesDual23);
        no = (RadioButton) findViewById(R.id.NoDual23);
        next.setOnClickListener(this);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);

        next =  (Button) findViewById(R.id.nextFromTrial3Dual2);
        minusButton = (ImageButton) findViewById(R.id.minusDual23);
        plusButton = (ImageButton) findViewById(R.id.plusDual23);

        errorNumText = (EditText) findViewById(R.id.errorNumTextDual23);
        minusButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        next.setOnClickListener(this);


    }

    public void onClick(View v) {
        if (v == next) {
            int tandemLegErrors = Integer.valueOf(errorNumText.getText().toString());
            Intent intent = new Intent(this, Dual2Trial4Activity.class);
            bundle.putInt("TandemLegErrorsDual2Trial3", tandemLegErrors);
            if(yes.isChecked()) {
                bundle.putString("monthsInReverseDual23", "Yes");
            } else {
                bundle.putString("monthsInReverseDual23", "No");
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