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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;;

/**
 * Created by nickkpoon on 12/4/17.
 */

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseDatabase Users;
    private DatabaseReference MyRef;
    private EditText password;
    private EditText login;
    private EditText name;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        TextView loginLogo = (TextView) findViewById(R.id.signup_logo);
        Typeface logoFont = Typeface.createFromAsset(getAssets(), "fonts/TitilliumWeb-ExtraLight.ttf");
        loginLogo.setTypeface(logoFont);

        login = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        name = findViewById(R.id.signup_name);
        phone = findViewById(R.id.signup_phone);


        findViewById(R.id.signup_button).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        Users = FirebaseDatabase.getInstance();

        /*Button signup = (Button) findViewById(R.id.signup_button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, MapsActivity.class);
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


    private void createAccount(String email, String password) {
       // Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        //showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            MyRef = Users.getReference("Users");
                            MyRef.child(mAuth.getUid()).child("Name").setValue(name.getText().toString());
                            MyRef.child(mAuth.getUid()).child("UserName").setValue(login.getText().toString());
                            MyRef.child(mAuth.getUid()).child("Phone Number").setValue(phone.getText().toString());
                            MyRef.child(mAuth.getUid()).child("Rides Given").setValue(0);
                            MyRef.child(mAuth.getUid()).child("Rides Taken").setValue(0);
                            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                        // [START_EXCLUDE]
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.signup_button) {
            createAccount(login.getText().toString(), password.getText().toString());
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