package com.github.concussionconnect.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.concussionconnect.R;

public class MainActivity extends Activity implements View.OnClickListener {
    private TextView welcomeText;
    private Button testButton;
    private Button settingsButton;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bundle = new Bundle();

        welcomeText = (TextView) findViewById(R.id.welcomeText);
        testButton = (Button) findViewById(R.id.testButton);
        settingsButton = (Button) findViewById(R.id.settingsButton);
        testButton.setOnClickListener(MainActivity.this);
        settingsButton.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        if (v == testButton) {
            startActivity(new Intent(this, TestSetupActivity.class).putExtras(bundle));
        }
        if (v == settingsButton) {
            startActivity(new Intent(this, SettingsActivity.class));
        }
    }
}
