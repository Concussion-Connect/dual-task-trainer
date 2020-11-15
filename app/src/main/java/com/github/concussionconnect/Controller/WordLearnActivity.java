package com.github.concussionconnect.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.github.concussionconnect.Model.ConnectToDB;
import androidx.annotation.NonNull;

import com.github.concussionconnect.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.content.ContentValues.TAG;

public class WordLearnActivity extends Activity implements View.OnClickListener {
    private ImageButton minusButton;
    private ImageButton plusButton;
    private EditText errorNumText;
    private Button nextButton;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_learn);
        nextButton =  (Button) findViewById(R.id.nextButton);
        minusButton = (ImageButton) findViewById(R.id.minusButton);
        plusButton = (ImageButton) findViewById(R.id.plusButton);
        errorNumText = (EditText) findViewById(R.id.errorNumText);
        minusButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        bundle = getIntent().getExtras();
        //updateTrainerSessionTrial(1);
        String adminId = bundle.getString("ID");
        ConnectToDB.updateTrainerSessionTrial(adminId, 1,
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

    @Override
    public void onClick(View v) {
        if (v == nextButton) {
            int doubleLegErrors = Integer.valueOf(errorNumText.getText().toString());
            bundle.putInt("doubleLegErrors", doubleLegErrors);
            startActivity(new Intent(this, MemoryTestActivity.class).putExtras(bundle));
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
            if (value < 99) {
                value++;
            }
            errorNumText.setText(String.valueOf(value));
        }
    }
}
