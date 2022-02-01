package com.github.concussionconnect.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.concussionconnect.Model.ConnectToDB;
import com.github.concussionconnect.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class EndSCATActivity extends Activity implements View.OnClickListener{
    private TextView SCATResults;
    private Button submit;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_s_c_a_t);

        SCATResults = (TextView) findViewById(R.id.SCATResults);
        submit = (Button) findViewById(R.id.submitButtonSCAT);
        bundle = getIntent().getExtras();

        submit.setOnClickListener(this);

        ArrayList<String> recallWordsTrial2 = new ArrayList<String>();
        ArrayList<String> selectedWords = new ArrayList<String>();
        ArrayList<String> recallWordsTrial4 = new ArrayList<String>();
        recallWordsTrial4 = bundle.getStringArrayList("SCATTrial4Words");
        recallWordsTrial2 = bundle.getStringArrayList("SCATTrial2Words");
        //selectedWords = bundle.getStringArrayList("SCATWordList");
        selectedWords.add("Finger");
        selectedWords.add("Penny");
        selectedWords.add("Blanket");
        selectedWords.add("Lemon");
        selectedWords.add("Insect");
        SCATResults.append("SCAT Selected Words: \n");
        for(String word: selectedWords) {
            SCATResults.append(" "+word + "\n");
        }
        SCATResults.append("\nSCAT Trial 2 (Recalled Words): \n");
        for(String word: recallWordsTrial2) {
            SCATResults.append(" "+word + "\n");
        }
        SCATResults.append("\nSCAT Trial 2 (Number of Recalled Words): " + bundle.getInt("numCorrectWordsSCAT2")+ "\n");
        SCATResults.append("\nCould the patient name the months in reverse order? (SCAT Trial 3): " + bundle.getString("monthsInReverseSCAT3") +"\n");
        SCATResults.append("\nSCAT Trial 4 (Recalled Words): \n");

        for(String word: recallWordsTrial4) {
            SCATResults.append(" "+word + "\n");
        }
        SCATResults.append("\nSCAT Trial 4 (Number of Recalled Words): " + bundle.getInt("numCorrectWordsSCAT4"));

        bundle = getIntent().getExtras();
        String adminId = bundle.getString("ID");
        ConnectToDB.updateTrainerSessionTrial(adminId, 0, "scat",
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