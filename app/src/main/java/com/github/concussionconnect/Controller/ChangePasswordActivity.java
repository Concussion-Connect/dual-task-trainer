package com.github.concussionconnect.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.github.concussionconnect.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends Activity implements View.OnClickListener {
    private Button buttonChangePassword;
    private EditText textNewPassword1;
    private EditText textNewPassword2;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        buttonChangePassword = (Button) findViewById(R.id.buttonChangePassword);
        buttonChangePassword.setOnClickListener(this);
        textNewPassword1 = (EditText) findViewById(R.id.textNewPassword1);
        textNewPassword2 = (EditText) findViewById(R.id.textNewPassword2);
    }

    public void changePassword() {
        String newPassword1 = textNewPassword1.getText().toString().trim();
        String newPassword2 = textNewPassword2.getText().toString().trim();
        if (newPassword1.length() < 6 || newPassword2.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password length must be 6 or greater", Toast.LENGTH_LONG).show();
            return;
        }
        if (!newPassword1.equals(newPassword2)) {
            Toast.makeText(this, "New passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseUser user = auth.getCurrentUser();
        user.updatePassword(newPassword1)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "User password updated.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "An error happened: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onClick(View v) {
        if (v == buttonChangePassword) {
            changePassword();
        }
    }
}
