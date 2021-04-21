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

public class EndDual2Activity extends Activity implements View.OnClickListener{
    private Bundle bundle;
    private TextView DualResults;
    private Button cancel;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_dual2);
        bundle = getIntent().getExtras();

        DualResults = (TextView) findViewById(R.id.Dual2Results);
        cancel = (Button) findViewById(R.id.cancelButtonDual2);
        submit = (Button) findViewById(R.id.submitButtonDual2);
        bundle = getIntent().getExtras();

        cancel.setOnClickListener(this);
        submit.setOnClickListener(this);

        /**
         *Dual 2 Trial 1
         */

        DualResults.append("Dual Task 2 Selected Words: \n");
        ArrayList<String> selectedWords = new ArrayList<String>();
        selectedWords.add("Baby");
        selectedWords.add("Monkey");
        selectedWords.add("Perfume");
        selectedWords.add("Sunset");
        selectedWords.add("Iron");
        for(String word: selectedWords) {
            DualResults.append(" "+word + "\n");
        }

        DualResults.append("\n" + "Dual Task 2 (Number of Double Leg Errors): " + bundle.getInt("DoubleLegErrorsDual2Trial1") + "\n");


        /**
         *Dual 2 Trial 2
         */

        ArrayList<String> recallWordsTrial2 = new ArrayList<String>();
        recallWordsTrial2 = bundle.getStringArrayList("Dual2Trial2Words");

        DualResults.append("\nDual Task 2 Trial 2 (Recalled Words): \n");
        for(String word: recallWordsTrial2) {
            DualResults.append(" "+word + "\n");
        }

        DualResults.append("\nDual Task 2 Trial 2 (Number of Recalled Words): " + bundle.getInt("numCorrectWordsDual22")+ "\n");
        DualResults.append("\nDual Task 2 Trial 2 (Number of Single Leg Errors): " + bundle.getInt("SingleLegErrorsDual2Trial2")+ "\n");


        /**
         *Dual 2 Trial 3
         */

        DualResults.append("\nCould the patient name the months in reverse order? (Dual 2 Trial 3): " + bundle.getString("monthsInReverseDual23")+ "\n");
        DualResults.append("\nDual Task 2 Trial 3 (Number of Tandem Leg Errors): " + bundle.getInt("TandemLegErrorsDual2Trial3")+"\n");

        /**
         *Dual 2 Trial 4
         */

        DualResults.append("\nDual Task 2 Trial 4 (Recalled Words): \n");
        ArrayList<String> recallWordsTrial4 = new ArrayList<String>();
        recallWordsTrial4 = bundle.getStringArrayList("Dual2Trial4Words");

        for(String word: recallWordsTrial4) {
            DualResults.append(" "+word + "\n");
        }
        DualResults.append("\nDual Task 2 Trial 4 (Number of Recalled Words): " + bundle.getInt("numCorrectWordsDual24"));

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
        if(v == cancel) {
            Intent intent = new Intent(this, OpeningPageForTests.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        if(v == submit) {
            Intent intent = new Intent(this, EndAllResearchActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}