package com.github.concussionconnect.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.github.concussionconnect.R;
import java.util.HashMap;

public class ScreeningQuestionnairePart3 extends Activity implements View.OnClickListener {

    private Button back, next;
    private Bundle bundle;
    private Spinner headache, pressureInHead, neckPain, nausea, dizziness, blurredVision,
            balanceProblems, lightSensitivity, noise, slowedDown, fog, dontFeelRight, concentrating,
            remembering, energy, confusion, drowsiness, asleep, emotional, irritability, sadness,
            nervous, gotUp, sleepLastNight;
    private EditText hoursSlept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screening_questionnaire_part3);
        connectWidgets();

        back.setOnClickListener(this);
        next.setOnClickListener(this);
        bundle = getIntent().getExtras();
    }

    private void connectWidgets() {
        back = findViewById(R.id.backFromQuest2);
        next = findViewById(R.id.nextToSymptoms);
        headache = findViewById(R.id.headache);
        pressureInHead = findViewById(R.id.pressureInHead);
        neckPain = findViewById(R.id.neckPain);
        nausea = findViewById(R.id.nausea);
        dizziness = findViewById(R.id.dizziness);
        blurredVision = findViewById(R.id.blurredVision);
        balanceProblems = findViewById(R.id.balanceProblems);
        lightSensitivity = findViewById(R.id.lightSensitivity);
        noise = findViewById(R.id.noise);
        slowedDown = findViewById(R.id.slowedDown);
        fog = findViewById(R.id.fog);
        dontFeelRight = findViewById(R.id.dontFeelRight);
        concentrating = findViewById(R.id.concentrating);
        remembering = findViewById(R.id.remembering);
        energy = findViewById(R.id.energy);
        confusion = findViewById(R.id.confusion);
        drowsiness = findViewById(R.id.drowsiness);
        asleep = findViewById(R.id.asleep);
        emotional = findViewById(R.id.emotional);
        irritability = findViewById(R.id.irritability);
        sadness = findViewById(R.id.sadness);
        nervous = findViewById(R.id.nervous);
        hoursSlept = findViewById(R.id.hoursSlept);
        gotUp = findViewById(R.id.gotUp);
        sleepLastNight = findViewById(R.id.sleepLastNight);
    }

    private void saveInputs() {
        HashMap<String, String> currentSymptoms = new HashMap<>();
        bundle.putSerializable("Current Symptoms", currentSymptoms);

        currentSymptoms.put("Headache", headache.getSelectedItem().toString());
        currentSymptoms.put("Pressure in head", pressureInHead.getSelectedItem().toString());
        currentSymptoms.put("Neck Pain", neckPain.getSelectedItem().toString());
        currentSymptoms.put("Nausea or vomiting", nausea.getSelectedItem().toString());
        currentSymptoms.put("Dizziness", dizziness.getSelectedItem().toString());
        currentSymptoms.put("Blurred Vision", blurredVision.getSelectedItem().toString());
        currentSymptoms.put("Balance Problems", balanceProblems.getSelectedItem().toString());
        currentSymptoms.put("Sensitivity to light", lightSensitivity.getSelectedItem().toString());
        currentSymptoms.put("Sensitivity to noise", noise.getSelectedItem().toString());
        currentSymptoms.put("Feeling slowed down", slowedDown.getSelectedItem().toString());
        currentSymptoms.put("Feeling like in a fog", fog.getSelectedItem().toString());
        currentSymptoms.put("Don't feel right", dontFeelRight.getSelectedItem().toString());
        currentSymptoms.put("Difficulty concentrating", concentrating.getSelectedItem().toString());
        currentSymptoms.put("Difficulty remembering", remembering.getSelectedItem().toString());
        currentSymptoms.put("Fatigue or low energy", energy.getSelectedItem().toString());
        currentSymptoms.put("Confusion", confusion.getSelectedItem().toString());
        currentSymptoms.put("Drowsiness", drowsiness.getSelectedItem().toString());
        currentSymptoms.put("Trouble falling asleep", asleep.getSelectedItem().toString());
        currentSymptoms.put("More emotional", emotional.getSelectedItem().toString());
        currentSymptoms.put("Irritability", irritability.getSelectedItem().toString());
        currentSymptoms.put("Sadness", sadness.getSelectedItem().toString());
        currentSymptoms.put("Nervous or anxious", nervous.getSelectedItem().toString());
        currentSymptoms.put("16. How many hours of sleep did you get last night?", hoursSlept.getText().toString());
        currentSymptoms.put("17. When I got up this morning, I felt", gotUp.getSelectedItem().toString());
        currentSymptoms.put("18. Overall, my sleep last night was", sleepLastNight.getSelectedItem().toString());
    }

    public void onClick(View v) {
        if (v == back) {
            Intent intent = new Intent(this, ScreeningQuestionnairePart2.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        if (v == next) {
            saveInputs();
            Intent intent = new Intent(this, ResearchSymptomsActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}