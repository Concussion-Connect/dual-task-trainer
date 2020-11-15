package com.github.concussionconnect.Controller;
import com.github.concussionconnect.Model.ConnectToDB;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.Random;

import com.github.concussionconnect.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;


import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class TestSetupActivity extends Activity implements View.OnClickListener {
    private Spinner rosterSpinner;
    private Spinner wordListSpinner;
    private Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_setup);
        rosterSpinner = (Spinner) findViewById(R.id.rosterSpinner);
        wordListSpinner = (Spinner) findViewById(R.id.wordListSpinner);
        nextButton = (Button) findViewById(R.id.nextButton);
        ArrayAdapter<CharSequence> wordListAdapter = ArrayAdapter.createFromResource(this, R.array.wordBankSelector, android.R.layout.simple_spinner_item);
        wordListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wordListSpinner.setAdapter(wordListAdapter);
        nextButton.setOnClickListener(this);
        Bundle playerListBundle = getIntent().getExtras();
        ArrayList<String> playerList = playerListBundle.getStringArrayList("roster");
        ArrayAdapter<String> rosterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, playerList);
        rosterSpinner.setAdapter(rosterAdapter);


    }
    @Override
    public void onClick(View v) {
        if (v == nextButton) {
            Intent i = new Intent(this, SymptomsActivity.class);
            Bundle bundle = new Bundle();
            int wordListNumber = wordListSpinner.getSelectedItemPosition() + 1;
            String listAnswer = "memory_word_list_" + wordListNumber;
            int id = getResources().getIdentifier(listAnswer, "array", getPackageName());
            bundle.putInt("listId", id);
            String playerInfo = rosterSpinner.getSelectedItem().toString();
            bundle.putString("playerInfo", playerInfo);

            //put id into database
            String adminId = createID();
            bundle.putString("ID", adminId);
            Toast.makeText(this, "Your ID is " + adminId + ". Put this code into the VR App", Toast.LENGTH_LONG).show();

            ConnectToDB.createTrainerSession(adminId, wordListNumber, 0,
                    new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "Trainer session created!");
                        }
                    },
                    new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error creating trainer session", e);
                        }
                    }
                    );

            i.putExtras(bundle);


            startActivity(i);
        }
    }

    public String createID() {
        Random rand = new Random();
        String id = "";
        for (int i = 0; i < 6; i++) {
            int rand_int = rand.nextInt(9);
            id += rand_int;
        }
        return id;
    }


}
