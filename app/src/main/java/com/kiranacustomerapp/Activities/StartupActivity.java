package com.kiranacustomerapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kiranacustomerapp.helper.SessionData;

public class StartupActivity extends AppCompatActivity {


    private String  sessionUserId;
    private SessionData sessionData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        sessionData = new SessionData(StartupActivity.this);
        sessionUserId = sessionData.getString("user_id","-1");

        //if user logs in first time

        if (sessionUserId.equals("-1")) {

            Intent i = (new Intent(StartupActivity.this, MainActivity.class));
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);

            finish();

        } else {

            int status = sessionData.getInt("status",0);

            if(status == 1)
            {
                Intent i = (new Intent(StartupActivity.this,HomeActivity.class));
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);

                finish();
            }
            else {

                Intent i = (new Intent(StartupActivity.this, OtpConfirmation.class));
                i.putExtra("email_id",sessionData.getString("email_id",""));
                i.putExtra("phone_no",sessionData.getString("phone_no",""));
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);

                finish();
            }
        }

    }
}
