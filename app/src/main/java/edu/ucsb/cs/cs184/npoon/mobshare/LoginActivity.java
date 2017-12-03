package edu.ucsb.cs.cs184.npoon.mobshare;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by nickkpoon on 12/2/17.
 */

public class LoginActivity extends AppCompatActivity  {


        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            TextView loginLogo = (TextView)findViewById(R.id.login_logo);
            Typeface logoFont = Typeface.createFromAsset(getAssets(), "fonts/simplifica.ttf");
            loginLogo.setTypeface(logoFont);

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
    }

