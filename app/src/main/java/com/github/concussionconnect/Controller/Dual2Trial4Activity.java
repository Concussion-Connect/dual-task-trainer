package com.github.concussionconnect.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;

import com.github.concussionconnect.R;

import java.util.ArrayList;

public class Dual2Trial4Activity extends Activity implements View.OnClickListener {
    private Bundle bundle;

    private CheckedTextView Dualword1;
    private CheckedTextView Dualword2;
    private CheckedTextView Dualword3;
    private CheckedTextView Dualword4;
    private CheckedTextView Dualword5;
    private EditText numCorrectWords;
    private Button next;

    private ArrayList<String> rememberedWords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dual2_trial4);

        bundle = getIntent().getExtras();
        Dualword1 = (CheckedTextView) findViewById(R.id.Dual2Word11);
        Dualword2 = (CheckedTextView) findViewById(R.id.Dual2Word12);
        Dualword3 = (CheckedTextView) findViewById(R.id.Dual2Word13);
        Dualword4 = (CheckedTextView) findViewById(R.id.Dual2Word14);
        Dualword5 = (CheckedTextView) findViewById(R.id.Dual2Word15);
        numCorrectWords = (EditText) findViewById(R.id.errorTextForDual24);
        next = (Button) findViewById(R.id.nextFromTrial4Dual2);
        Dualword1.setOnClickListener(this);
        Dualword2.setOnClickListener(this);
        Dualword3.setOnClickListener(this);
        Dualword4.setOnClickListener(this);
        Dualword5.setOnClickListener(this);
        numCorrectWords.setOnClickListener(this);

        rememberedWords = new ArrayList<>();

        Dualword1.setOnClickListener(this);
        Dualword2.setOnClickListener(this);
        Dualword3.setOnClickListener(this);
        Dualword4.setOnClickListener(this);
        Dualword5.setOnClickListener(this);
        numCorrectWords.setOnClickListener(this);
        next.setOnClickListener(this);


    }


    public void onClick(View v) {
        int num =  Integer.valueOf(numCorrectWords.getText().toString());
        if(v== next) {
            Intent intent = new Intent(this, EndDual2Activity.class);
            bundle.putStringArrayList("Dual2Trial4Words", rememberedWords);
            bundle.putInt("numCorrectWordsDual24", num);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if(v==Dualword1) {
            String word1 = Dualword1.getText().toString();
            if(!Dualword1.isChecked()) {
                if(!rememberedWords.contains(word1)) {
                    rememberedWords.add(word1);
                }
                Dualword1.setChecked(true);
                num++;
                numCorrectWords.setText(String.valueOf(num));
            } else {
                if(rememberedWords.contains(word1)) {
                    rememberedWords.remove(word1);
                }
                Dualword1.setChecked(false);
                num = Integer.valueOf(numCorrectWords.getText().toString());
                if(num > 0) {
                    num--;
                }
                numCorrectWords.setText(String.valueOf(num));
            }
        }
        if(v==Dualword2) {
            String word2 = Dualword2.getText().toString();
            if(!Dualword2.isChecked()) {
                Dualword2.setChecked(true);
                if(!rememberedWords.contains(word2)) {
                    rememberedWords.add(word2);
                }
                num++;
                numCorrectWords.setText(String.valueOf(num));
            } else {
                Dualword2.setChecked(false);
                if(rememberedWords.contains(word2)) {
                    rememberedWords.remove(word2);
                }
                if(num > 0) {
                    num--;
                }
                numCorrectWords.setText(String.valueOf(num));
            }
        }
        if(v==Dualword3) {
            String word3 = Dualword3.getText().toString();
            if(!Dualword3.isChecked()) {
                Dualword3.setChecked(true);
                if(!rememberedWords.contains(word3)) {
                    rememberedWords.add(word3);
                }
                num++;
                numCorrectWords.setText(String.valueOf(num));
            } else {
                Dualword3.setChecked(false);
                if(rememberedWords.contains(word3)) {
                    rememberedWords.remove(word3);
                }
                if(num > 0) {
                    num--;
                }
                numCorrectWords.setText(String.valueOf(num));
            }
        }
        if(v==Dualword4) {
            String word4 = Dualword4.getText().toString();
            if(!Dualword4.isChecked()) {
                Dualword4.setChecked(true);
                if(!rememberedWords.contains(word4)) {
                    rememberedWords.add(word4);
                }
                num++;
                numCorrectWords.setText(String.valueOf(num));
            } else {
                Dualword4.setChecked(false);
                if(rememberedWords.contains(word4)) {
                    rememberedWords.remove(word4);
                }
                if(num > 0) {
                    num--;
                }
                numCorrectWords.setText(String.valueOf(num));
            }
        }
        if(v==Dualword5) {
            String word5 = Dualword5.getText().toString();
            if(!Dualword5.isChecked()) {
                Dualword5.setChecked(true);
                if(!rememberedWords.contains(word5)) {
                    rememberedWords.add(word5);
                }
                num++;
                numCorrectWords.setText(String.valueOf(num));
            } else {
                Dualword5.setChecked(false);
                if(rememberedWords.contains(word5)) {
                    rememberedWords.remove(word5);
                }
                if(num > 0) {
                    num--;
                }
                numCorrectWords.setText(String.valueOf(num));
            }
        }
    }
}