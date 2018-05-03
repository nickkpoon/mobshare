package edu.ucsb.cs.mobile.npoon.mobshare;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by nickkpoon on 12/2/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private EditText password;
    private EditText login;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            TextView loginLogo = (TextView)findViewById(R.id.login_logo);
            Typeface logoFont = Typeface.createFromAsset(getAssets(), "fonts/TitilliumWeb-ExtraLight.ttf");
            loginLogo.setTypeface(logoFont);

            login = findViewById(R.id.login);
            password = findViewById(R.id.password);

            findViewById(R.id.login_button).setOnClickListener(this);
            mAuth = FirebaseAuth.getInstance();


           /* Button loginButton = (Button) findViewById(R.id.login_button);
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });*/

            /*final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, 2000);*/
        }


    private void signIn(String email, String password) {
        //Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.login_button) {
            signIn(login.getText().toString(), password.getText().toString());
        }
    }
    private boolean validateForm() {
        boolean valid = true;

        String email = login.getText().toString();
        if (TextUtils.isEmpty(email)) {
            login.setError("Required.");
            valid = false;
        } else {
            login.setError(null);
        }

        String pass = password.getText().toString();
        if (TextUtils.isEmpty(pass)) {
            password.setError("Required.");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }
    }

