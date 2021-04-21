package com.github.concussionconnect.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;

import com.github.concussionconnect.Model.ChecklistAdapter;
import com.github.concussionconnect.Model.ChecklistModel;
import com.github.concussionconnect.Model.ConnectToDB;
import com.github.concussionconnect.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class SCATTrial2Activity extends Activity implements View.OnClickListener{
    private Bundle bundle;
    private CheckedTextView SCATword1;
    private CheckedTextView SCATword2;
    private CheckedTextView SCATword3;
    private CheckedTextView SCATword4;
    private CheckedTextView SCATword5;
    private EditText numCorrectWords;
    private Button next;
    private ArrayList<String> rememberedWords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_c_a_t_trial2);
        SCATword1 = (CheckedTextView) findViewById(R.id.SCATWord1);
        SCATword2 = (CheckedTextView) findViewById(R.id.SCATWord2);
        SCATword3 = (CheckedTextView) findViewById(R.id.SCATWord3);
        SCATword4 = (CheckedTextView) findViewById(R.id.SCATWord4);
        SCATword5 = (CheckedTextView) findViewById(R.id.SCATWord5);
        numCorrectWords = (EditText) findViewById(R.id.errorTextForSCAT2);
        next = (Button) findViewById(R.id.nextFromTrial2SCAT);
        SCATword1.setOnClickListener(this);
        SCATword2.setOnClickListener(this);
        SCATword3.setOnClickListener(this);
        SCATword4.setOnClickListener(this);
        SCATword5.setOnClickListener(this);
        numCorrectWords.setOnClickListener(this);
        bundle = getIntent().getExtras();
        String adminId = bundle.getString("ID");
        ConnectToDB.updateTrainerSessionTrial(adminId, 2, "scat",
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

        rememberedWords = new ArrayList<>();
        /**SCATword1.setText(selectedWords.get(0));
        SCATword2.setText(selectedWords.get(1));
        SCATword3.setText(selectedWords.get(2));
        SCATword4.setText(selectedWords.get(3));
        SCATword5.setText(selectedWords.get(4));*/

        SCATword1.setOnClickListener(this);
        SCATword2.setOnClickListener(this);
        SCATword3.setOnClickListener(this);
        SCATword4.setOnClickListener(this);
        SCATword5.setOnClickListener(this);
        numCorrectWords.setOnClickListener(this);
        next.setOnClickListener(this);


    }

    public void onClick(View v) {
        int num =  Integer.valueOf(numCorrectWords.getText().toString());
        if(v== next) {
            Intent intent = new Intent(this, SCATTrial3Activity.class);
            bundle.putInt("numCorrectWordsSCAT2", num);
            bundle.putStringArrayList("SCATTrial2Words", rememberedWords);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if(v==SCATword1) {
            String word1 = SCATword1.getText().toString();
            if(!SCATword1.isChecked()) {
                if(!rememberedWords.contains(word1)) {
                    rememberedWords.add(word1);
                }
                SCATword1.setChecked(true);
                num++;
                numCorrectWords.setText(String.valueOf(num));
            } else {
                SCATword1.setChecked(false);
                if(rememberedWords.contains(word1)) {
                    rememberedWords.remove(word1);
                }
                 num = Integer.valueOf(numCorrectWords.getText().toString());
                if(num > 0) {
                    num--;
                }
                numCorrectWords.setText(String.valueOf(num));
            }
        }
        if(v==SCATword2) {
            String word2 = SCATword2.getText().toString();
            if(!SCATword2.isChecked()) {
                if(!rememberedWords.contains(word2)) {
                    rememberedWords.add(word2);
                }
                SCATword2.setChecked(true);
                num++;
                numCorrectWords.setText(String.valueOf(num));
            } else {
                if(rememberedWords.contains(word2)) {
                    rememberedWords.remove(word2);
                }
                SCATword2.setChecked(false);
                if(num > 0) {
                    num--;
                }
                numCorrectWords.setText(String.valueOf(num));
            }
        }
        if(v==SCATword3) {
            String word3 = SCATword3.getText().toString();
            if(!SCATword3.isChecked()) {
                if(!rememberedWords.contains(word3)) {
                    rememberedWords.add(word3);
                }
                SCATword3.setChecked(true);
                num++;
                numCorrectWords.setText(String.valueOf(num));
            } else {
                SCATword3.setChecked(false);
                if(rememberedWords.contains(word3)) {
                    rememberedWords.remove(word3);
                }
                if(num > 0) {
                    num--;
                }
                numCorrectWords.setText(String.valueOf(num));
            }
        }
        if(v==SCATword4) {
            String word4 = SCATword4.getText().toString();
            if(!SCATword4.isChecked()) {
                if(!rememberedWords.contains(word4)) {
                    rememberedWords.add(word4);
                }
                SCATword4.setChecked(true);
                num++;
                numCorrectWords.setText(String.valueOf(num));
            } else {
                SCATword4.setChecked(false);
                if(rememberedWords.contains(word4)) {
                    rememberedWords.remove(word4);
                }
                if(num > 0) {
                    num--;
                }
                numCorrectWords.setText(String.valueOf(num));
            }
        }
        if(v==SCATword5) {
            String word5 = SCATword5.getText().toString();
            if(!SCATword5.isChecked()) {
                if(!rememberedWords.contains(word5)) {
                    rememberedWords.add(word5);
                }
                SCATword5.setChecked(true);
                num++;
                numCorrectWords.setText(String.valueOf(num));
            } else {
                SCATword5.setChecked(false);
                if(rememberedWords.contains(word5)) {
                    rememberedWords.add(word5);
                }
                if(num > 0) {
                    num--;
                }
                numCorrectWords.setText(String.valueOf(num));
            }
        }
    }


}