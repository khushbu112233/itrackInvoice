package com.invoice.aipxperts.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.invoice.aipxperts.R;
import com.invoice.aipxperts.Utils.Pref;

/**
 * Created by aipxperts-ubuntu-01 on 30/10/17.
 */

public class SplashActivity extends BaseActivity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if(Pref.getValue(SplashActivity.this,"first","").equalsIgnoreCase("1"))
                {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    // close this activity
                    finish();
                }else {
                    Intent intent = new Intent(SplashActivity.this, AddCompanyProfileActivity.class);
                    startActivity(intent);
                    // close this activity
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}