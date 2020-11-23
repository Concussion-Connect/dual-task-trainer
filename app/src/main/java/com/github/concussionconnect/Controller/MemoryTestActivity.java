package com.github.concussionconnect.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.github.concussionconnect.Model.ChecklistAdapter;
import com.github.concussionconnect.Model.ChecklistModel;
import com.github.concussionconnect.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.github.concussionconnect.Model.ConnectToDB;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MemoryTestActivity extends Activity implements View.OnClickListener {
    private ArrayList<ChecklistModel> wordList;
    private Button nextButton;
    private ListView listView;
    private ImageButton minusButton;
    private ImageButton plusButton;
    private EditText errorNumText;
    ChecklistAdapter checklistAdapter;
    Bundle bundle;
    //updateTrainerSessionTrial(2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_test);
        nextButton =  (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ChecklistModel checklistModel = ((ChecklistAdapter) listView.getAdapter()).getWordList().get(position);
                CheckBox box = (CheckBox) view.findViewById(R.id.checkBox1);
                box.setChecked(!box.isChecked());
            }
        });
        minusButton = (ImageButton) findViewById(R.id.minusButton);
        plusButton = (ImageButton) findViewById(R.id.plusButton);
        errorNumText = (EditText) findViewById(R.id.errorNumText);
        minusButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        bundle = new Bundle();
        bundle = getIntent().getExtras();
        int id = bundle.getInt("listId");
        String[] selectedWords = getResources().getStringArray(id);
        wordList = ChecklistModel.getChecklistArray(selectedWords);
        checklistAdapter = new ChecklistAdapter(wordList, this);
        listView.setAdapter(checklistAdapter);
        String adminId = bundle.getString("ID");
        ConnectToDB.updateTrainerSessionTrial(adminId, 2,
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
            int total = 0;
            for (ChecklistModel x : checklistAdapter.getWordList()) {
                if (x.isChecked()) {
                    total++;
                }
            }
            int singleLegErrors = Integer.valueOf(errorNumText.getText().toString());
            bundle.putInt("shortMemScore", total);
            bundle.putInt("singleLegErrors", singleLegErrors);
            startActivity(new Intent(this, MonthsActivity.class).putExtras(bundle));
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
