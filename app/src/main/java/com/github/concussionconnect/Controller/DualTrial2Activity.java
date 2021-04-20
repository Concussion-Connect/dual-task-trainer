package com.github.concussionconnect.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageButton;

import com.github.concussionconnect.R;

import java.util.ArrayList;

public class DualTrial2Activity extends Activity implements View.OnClickListener {
    private ImageButton minusButton;
    private ImageButton plusButton;
    private EditText errorNumText;
    private Button nextButton;

    private CheckedTextView Dualword1;
    private CheckedTextView Dualword2;
    private CheckedTextView Dualword3;
    private CheckedTextView Dualword4;
    private CheckedTextView Dualword5;
    private EditText numCorrectWords;



    private ArrayList<String> rememberedWords;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dual_trial2);
        nextButton =  (Button) findViewById(R.id.nextButtonFromDual2);
        minusButton = (ImageButton) findViewById(R.id.minusDual2);
        plusButton = (ImageButton) findViewById(R.id.plusDual2);
        errorNumText = (EditText) findViewById(R.id.Dual2NumErrors);
        minusButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        bundle = getIntent().getExtras();
        Dualword1 = (CheckedTextView) findViewById(R.id.DualWord1);
        Dualword2 = (CheckedTextView) findViewById(R.id.DualWord2);
        Dualword3 = (CheckedTextView) findViewById(R.id.DualWord3);
        Dualword4 = (CheckedTextView) findViewById(R.id.DualWord4);
        Dualword5 = (CheckedTextView) findViewById(R.id.DualWord5);
        numCorrectWords = (EditText) findViewById(R.id.Dual2NumCorrectWords);

        rememberedWords = new ArrayList<String>();

        Dualword1.setOnClickListener(this);
        Dualword2.setOnClickListener(this);
        Dualword3.setOnClickListener(this);
        Dualword4.setOnClickListener(this);
        Dualword5.setOnClickListener(this);



    }

    public void onClick(View v) {
        int num =  Integer.valueOf(numCorrectWords.getText().toString());
        if (v == nextButton) {
            int singleLegErrors = Integer.valueOf(errorNumText.getText().toString());
            Intent intent = new Intent(this, DualTrial3Activity.class);
            bundle.putInt("SingleLegErrorsDualTrial2", singleLegErrors);
            bundle.putInt("numCorrectWordsDual2", num);
            bundle.putStringArrayList("DualTrial2Words", rememberedWords);
            intent.putExtras(bundle);
            startActivity(intent);

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
            if (value < 10) {
                value++;
            }
            errorNumText.setText(String.valueOf(value));
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
                Dualword1.setChecked(false);
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
        if(v==Dualword2) {
            String word2 = Dualword2.getText().toString();
            if(!Dualword2.isChecked()) {
                if(!rememberedWords.contains(word2)) {
                    rememberedWords.add(word2);
                }
                Dualword2.setChecked(true);
                num++;
                numCorrectWords.setText(String.valueOf(num));
            } else {
                if(rememberedWords.contains(word2)) {
                    rememberedWords.remove(word2);
                }
                Dualword2.setChecked(false);
                if(num > 0) {
                    num--;
                }
                numCorrectWords.setText(String.valueOf(num));
            }
        }
        if(v==Dualword3) {
            String word3 = Dualword3.getText().toString();
            if(!Dualword3.isChecked()) {
                if(!rememberedWords.contains(word3)) {
                    rememberedWords.add(word3);
                }
                Dualword3.setChecked(true);
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
                if(!rememberedWords.contains(word4)) {
                    rememberedWords.add(word4);
                }
                Dualword4.setChecked(true);
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
                if(!rememberedWords.contains(word5)) {
                    rememberedWords.add(word5);
                }
                Dualword5.setChecked(true);
                num++;
                numCorrectWords.setText(String.valueOf(num));
            } else {
                Dualword5.setChecked(false);
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