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

public class DualTrial1Activity extends Activity implements View.OnClickListener {
    private ImageButton minusButton;
    private ImageButton plusButton;
    private EditText errorNumText;
    private Button nextButton;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dual_trial1);
        nextButton =  (Button) findViewById(R.id.nextButtonFromDual1);
        minusButton = (ImageButton) findViewById(R.id.minusDual1);
        plusButton = (ImageButton) findViewById(R.id.plusDual1);
        errorNumText = (EditText) findViewById(R.id.errorNumTextDual1);
        minusButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        bundle = getIntent().getExtras();
    }

    public void onClick(View v) {
        if (v == nextButton) {
            int doubleLegErrors = Integer.valueOf(errorNumText.getText().toString());
            Intent intent = new Intent(this, DualTrial2Activity.class);
            bundle.putInt("DoubleLegErrorsDualTrial1", doubleLegErrors);
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
    }
}