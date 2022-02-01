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

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class EndDual1Activity extends Activity  implements View.OnClickListener{
    private TextView DualResults;
    private Button submit;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_dual1);

        DualResults = (TextView) findViewById(R.id.DualResults);
        submit = (Button) findViewById(R.id.submitButtonDual);
        bundle = getIntent().getExtras();

        submit.setOnClickListener(this);

        /**
         *Dual Trial 1
         */

        DualResults.append("Dual Task 1 Selected Words: \n");
        ArrayList<String> selectedWords = new ArrayList<String>();
        selectedWords.add("Candle");
        selectedWords.add("Paper");
        selectedWords.add("Sugar");
        selectedWords.add("Sandwich");
        selectedWords.add("Wagon");
        for(String word: selectedWords) {
            DualResults.append(" "+word + "\n");
        }

        DualResults.append("\n" + "Dual Task 1 (Number of Double Leg Errors): " + bundle.getInt("DoubleLegErrorsDualTrial1") + "\n");


        /**
         *Dual Trial 2
         */

        ArrayList<String> recallWordsTrial2 = new ArrayList<String>();
        recallWordsTrial2 = bundle.getStringArrayList("DualTrial2Words");

        DualResults.append("\nDual Task 1 Trial 2 (Recalled Words): \n");
        for(String word: recallWordsTrial2) {
            DualResults.append(" "+word + "\n");
        }

        DualResults.append("\nDual Task 1 Trial 2 (Number of Recalled Words): " + bundle.getInt("numCorrectWordsDual2")+ "\n");
        DualResults.append("\nDual Task 1 Trial 2 (Number of Single Leg Errors): " + bundle.getInt("SingleLegErrorsDualTrial2")+ "\n");


        /**
         *Dual Trial 3
         */

        DualResults.append("\nCould the patient name the months in reverse order? (Dual Trial 3): " + bundle.getString("monthsInReverseDual3")+ "\n");
        DualResults.append("\nDual Task 1 Trial 3 (Number of Tandem Leg Errors): " + bundle.getInt("TandemLegErrorsDualTrial3")+"\n");

        /**
         *Dual Trial 4
         */

        DualResults.append("\nDual Task 1 Trial 4 (Recalled Words): \n");
        ArrayList<String> recallWordsTrial4 = new ArrayList<String>();
        recallWordsTrial4 = bundle.getStringArrayList("DualTrial4Words");

        for(String word: recallWordsTrial4) {
            DualResults.append(" "+word + "\n");
        }
        DualResults.append("\nDual Task 1 Trial 4 (Number of Recalled Words): " + bundle.getInt("numCorrectWordsDual4"));



        bundle = getIntent().getExtras();
        String adminId = bundle.getString("ID");
        ConnectToDB.updateTrainerSessionTrial(adminId, 0, "dual",
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