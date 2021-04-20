package com.github.concussionconnect.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.concussionconnect.R;

import java.util.ArrayList;

public class EndSCATActivity extends Activity implements View.OnClickListener{
    private TextView SCATResults;
    private Button cancel;
    private Button submit;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_s_c_a_t);

        SCATResults = (TextView) findViewById(R.id.SCATResults);
        cancel = (Button) findViewById(R.id.cancelButtonSCAT);
        submit = (Button) findViewById(R.id.submitButtonSCAT);
        bundle = getIntent().getExtras();

        cancel.setOnClickListener(this);
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










    }

    public void onClick(View v) {
        if(v == cancel) {
            Intent intent = new Intent(this, OpeningPageForTests.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        if(v == submit) {
            Intent intent = new Intent(this, OpeningPageForTests.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}