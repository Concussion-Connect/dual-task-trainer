package com.github.concussionconnect.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.github.concussionconnect.Model.ConnectToDB;
import com.github.concussionconnect.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class SCATTrial1Activity extends Activity implements View.OnClickListener {
    private Bundle bundle;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_c_a_t_trial1);
       /** wordChoice1 = (RadioButton) findViewById(R.id.SCATwordList1);
        wordChoice2 = (RadioButton) findViewById(R.id.SCATwordList2);
        wordChoice3 = (RadioButton) findViewById(R.id.SCATwordList3);
        wordChoice4 = (RadioButton) findViewById(R.id.SCATwordList4);
        wordChoice5 = (RadioButton) findViewById(R.id.SCATwordList5);
        wordChoice6 = (RadioButton) findViewById(R.id.SCATwordList6);*/
        next = (Button) findViewById(R.id.nextFromTrial1SCAT);
        next.setOnClickListener(this);

        bundle = getIntent().getExtras();


        String adminId = bundle.getString("ID");
        ConnectToDB.updateTrainerSessionTrial(adminId, 1, "scat",
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
        if(v == next) {
                Intent intent = new Intent(this, SCATTrial2Activity.class);
                //bundle.putStringArrayList("SCATWordList", chosenWordList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        /**if (v == wordChoice1) {
            String words1 = wordChoice1.getText().toString();
            words1 = words1.replaceAll("[\\n]", "");
            chosenWordList.clear();
            if (wordChoice1.isChecked()) {
                String tmp = "";
                for (int i = 0; i < words1.length(); i++) {
                    Character letter = words1.charAt(i);
                    if (Character.isUpperCase(letter)) {
                        if (tmp.length() != 0) {
                            tmp.trim();
                            chosenWordList.add(tmp);
                        }
                        tmp = "";
                        tmp += letter;
                    } else {
                        tmp += letter;
                    }
                }
                chosenWordList.add(tmp);
            }
        }

        if (v == wordChoice2) {
            String words2 = wordChoice2.getText().toString();
            words2 = words2.replaceAll("[\\n]", "");
            chosenWordList.clear();
            if (wordChoice2.isChecked()) {
                String tmp = "";
                for (int i = 0; i < words2.length(); i++) {
                    Character letter = words2.charAt(i);
                    if (Character.isUpperCase(letter)) {
                        if (tmp.length() != 0) {
                            tmp.trim();
                            chosenWordList.add(tmp);
                        }
                        tmp = "";
                        tmp += letter;
                    } else {
                        tmp += letter;
                    }
                }
                chosenWordList.add(tmp);
            }
        }
        if (v == wordChoice3) {
            String words3 = wordChoice3.getText().toString();
            words3 = words3.replaceAll("[\\n]", "");
            chosenWordList.clear();
            if (wordChoice3.isChecked()) {
                String tmp = "";
                for (int i = 0; i < words3.length(); i++) {
                    Character letter = words3.charAt(i);
                    if (Character.isUpperCase(letter)) {
                        if (tmp.length() != 0) {
                            tmp.trim();
                            chosenWordList.add(tmp);
                        }
                        tmp = "";
                        tmp += letter;
                    } else {
                        tmp += letter;
                    }
                }
                chosenWordList.add(tmp);
            }
        }
        if (v == wordChoice4) {
            String words4 = wordChoice4.getText().toString();
            words4 = words4.replaceAll("[\\n]", "");
            chosenWordList.clear();
            if (wordChoice4.isChecked()) {
                String tmp = "";
                for (int i = 0; i < words4.length(); i++) {
                    Character letter = words4.charAt(i);
                    if (Character.isUpperCase(letter)) {
                        if (tmp.length() != 0) {
                            tmp.trim();
                            chosenWordList.add(tmp);
                        }
                        tmp = "";
                        tmp += letter;
                    } else {
                        tmp += letter;
                    }
                }
                chosenWordList.add(tmp);
            }
        }
        if (v == wordChoice5) {
            String words5 = wordChoice5.getText().toString();
            words5 = words5.replaceAll("[\\n]", "");
            chosenWordList.clear();
            if (wordChoice5.isChecked()) {
                String tmp = "";
                for (int i = 0; i < words5.length(); i++) {
                    Character letter = words5.charAt(i);
                    if (Character.isUpperCase(letter)) {
                        if (tmp.length() != 0) {
                            tmp.trim();
                            chosenWordList.add(tmp);
                        }
                        tmp = "";
                        tmp += letter;
                    } else {
                        tmp += letter;
                    }
                }
                chosenWordList.add(tmp);
            }
        }
        if (v == wordChoice6) {
            String words6 = wordChoice6.getText().toString();
            words6 = words6.replaceAll("[\\n]", "");
            chosenWordList.clear();
            if (wordChoice6.isChecked()) {
                String tmp = "";
                for (int i = 0; i < words6.length(); i++) {
                    Character letter = words6.charAt(i);
                    if (Character.isUpperCase(letter)) {
                        if (tmp.length() != 0) {
                            tmp.trim();
                            chosenWordList.add(tmp);
                        }
                        tmp = "";
                        tmp += letter;
                    } else {
                        tmp += letter;
                    }
                }
                chosenWordList.add(tmp);
            }
        }*/
    }
}