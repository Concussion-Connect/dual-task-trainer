package com.github.concussionconnect.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.github.concussionconnect.R;

public class BESSTrial4Activity extends Activity implements View.OnClickListener{
    private ImageButton minusButton;
    private ImageButton plusButton;
    private EditText errorNumText;
    private Button finishButton;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_e_s_s_trial4);

        finishButton =  (Button) findViewById(R.id.FinishBESS);
        minusButton = (ImageButton) findViewById(R.id.minusBESS4);
        plusButton = (ImageButton) findViewById(R.id.plusBESS4);
        errorNumText = (EditText) findViewById(R.id.errorText4);
        minusButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        finishButton.setOnClickListener(this);

        bundle = getIntent().getExtras();
    }

    public void onClick(View v) {
        if(v == finishButton) {
            int tandemLegErrors = Integer.valueOf(errorNumText.getText().toString());
            Intent intent = new Intent(this, EndBESSActivity.class);
            bundle.putInt("BESSTrial4Errors", tandemLegErrors);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (v == minusButton) {
            int num = Integer.valueOf(errorNumText.getText().toString());
            if (num > 0) {
                num--;
            }
            errorNumText.setText(String.valueOf(num));
        }
        if (v == plusButton) {
            int num = Integer.valueOf(errorNumText.getText().toString());
            if (num < 10) { // max score - 10
                num++;
            }
            errorNumText.setText(String.valueOf(num));
        }
    }

}