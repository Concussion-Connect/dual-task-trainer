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

import com.github.concussionconnect.Model.ConnectToDB;
import com.github.concussionconnect.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.content.ContentValues.TAG;

public class BESSTrial2Activity extends Activity implements View.OnClickListener{
    private ImageButton minusButton;
    private ImageButton plusButton;
    private EditText errorNumText;
    private Button nextButton;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_e_s_s_trial2);
        nextButton =  (Button) findViewById(R.id.nextFromTrial2BESS);
        minusButton = (ImageButton) findViewById(R.id.minusBESS2);
        plusButton = (ImageButton) findViewById(R.id.plusBESS2);
        errorNumText = (EditText) findViewById(R.id.errorText2);
        minusButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        bundle = getIntent().getExtras();

        String adminId = bundle.getString("ID");
        ConnectToDB.updateTrainerSessionTrial(adminId, 2, "bess",
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
        if(v == nextButton) {
            int singleLegErrors = Integer.valueOf(errorNumText.getText().toString());
            Intent intent = new Intent(this, BESSTrial3Activity.class);
            bundle.putInt("BESSTrial2Errors", singleLegErrors);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (v == minusButton) {
            int num = Integer.valueOf(errorNumText.getText().toString());
            if (num > 0) {
                num--;
            }
            errorNumText.setText(String.valueOf(num));
        }
        if (v == plusButton) {
            int num = Integer.valueOf(errorNumText.getText().toString());
            if (num < 10) { // max score - 10
                num++;
            }
            errorNumText.setText(String.valueOf(num));
        }
    }
}