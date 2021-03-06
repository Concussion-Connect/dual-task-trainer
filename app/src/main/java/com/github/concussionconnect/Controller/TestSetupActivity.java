package com.github.concussionconnect.Controller;
import com.github.concussionconnect.Model.ConnectToDB;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.Random;

import com.github.concussionconnect.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

import static android.content.ContentValues.TAG;

public class TestSetupActivity extends Activity implements View.OnClickListener {
    private EditText personNameEditText;
    private Spinner wordListSpinner;
    private Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_setup);
        personNameEditText = (EditText) findViewById(R.id.personNameEditText);
        wordListSpinner = (Spinner) findViewById(R.id.wordListSpinner);
        nextButton = (Button) findViewById(R.id.nextButton);
        ArrayAdapter<CharSequence> wordListAdapter = ArrayAdapter.createFromResource(this, R.array.wordBankSelector, android.R.layout.simple_spinner_item);
        wordListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wordListSpinner.setAdapter(wordListAdapter);
        nextButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if (v == nextButton) {
            //put id into database
            final int wordListNumber = wordListSpinner.getSelectedItemPosition() + 1;
            final String adminId = createID();

            ConnectToDB.getSessionReference(adminId,
                new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot snap) {
                        if (snap.exists()) {
                            Toast.makeText(getApplicationContext(), "Autogenerated ID failed; try again!", Toast.LENGTH_LONG).show();
                        } else {
                            ConnectToDB.createTrainerSession(adminId, wordListNumber, 0,
                                new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        beginTest(adminId);
                                        //Toast.makeText(getApplicationContext(), "Your ID is " + adminId + ". Put this code into the VR App", Toast.LENGTH_LONG).show();

                                        //added
                                        LayoutInflater inflater = getLayoutInflater();
                                        View layout = inflater.inflate(R.layout.custom_toast,
                                                (ViewGroup) findViewById(R.id.custom_toast_container));

                                        TextView text = (TextView) layout.findViewById(R.id.text);
                                        text.setText("Your ID is " + adminId + ". Put this code into the VR App");

                                        Toast toast = new Toast(getApplicationContext());
                                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                                        toast.setDuration(Toast.LENGTH_LONG);
                                        toast.setView(layout);
                                        toast.show();
                                        //end added
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
                        }
                    }
                },
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error creating trainer session", e);
                    }
                }
            );
        }
    }

    public void beginTest(String adminId) {
        Intent i = new Intent(this, SymptomsActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("ID", adminId);
        final int wordListNumber = wordListSpinner.getSelectedItemPosition() + 1;
        String listAnswer = "memory_word_list_" + wordListNumber;
        int id = getResources().getIdentifier(listAnswer, "array", getPackageName());
        bundle.putInt("listId", id);
        String playerInfo = personNameEditText.getText().toString().trim();
        bundle.putString("playerInfo", playerInfo);

        i.putExtras(bundle);
        startActivity(i);
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
