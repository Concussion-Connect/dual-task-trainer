package com.github.concussionconnect.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.concussionconnect.Model.ConnectToDB;
import com.github.concussionconnect.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.content.ContentValues.TAG;

public class BESSTrial3Activity extends Activity implements View.OnClickListener{

    private Button nextButton;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_e_s_s_trial3);

        nextButton = (Button) findViewById(R.id.nextFromTrial3BESS);
        nextButton.setOnClickListener(this);
        bundle = getIntent().getExtras();

        String adminId = bundle.getString("ID");
        ConnectToDB.updateTrainerSessionTrial(adminId, 3, "bess",
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
            Intent intent = new Intent(this, BESSTrial4Activity.class);
            bundle.putString("BESSTrial3Errors","Pause 20 seconds");
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}