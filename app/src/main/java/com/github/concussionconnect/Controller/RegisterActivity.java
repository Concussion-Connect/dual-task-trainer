package com.github.concussionconnect.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.github.concussionconnect.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends Activity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextPassword;
    private TextView textLoginHere;
    private Button buttonRegister;
    private String email;
    private String password;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textLoginHere = (TextView) findViewById(R.id.textLoginHere);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(this);
        textLoginHere.setOnClickListener(this);
    }

    public void userRegister() {
        String givenName = editTextFirstName.getText().toString().trim();
        String familyName = editTextLastName.getText().toString().trim();
        email = editTextEmail.getText().toString().trim().toLowerCase();
        password = editTextPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your e-mail", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(givenName)) {
            Toast.makeText(this, "Please enter your first name", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(familyName)) {
            Toast.makeText(this, "Please enter your last name", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password length must be 6 or greater", Toast.LENGTH_LONG).show();
            return;
        }

        // First and last name are currently unused

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), AwaitConfirmationActivity.class));
                        } else {
                            // Sign-up failed, display exception
                            String message = task.getException().getMessage();
                            Log.wtf("sign up failed", message);
                            Toast.makeText(getApplicationContext(), "An error occurred: " + message, Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        if (v == buttonRegister) {
            userRegister();
        }

        if (v == textLoginHere) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
