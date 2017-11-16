package com.invoice.aipxperts.Activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.WindowManager;

import com.invoice.aipxperts.Migration;
import com.invoice.aipxperts.R;
import com.invoice.aipxperts.Utils.ConnectionDetector;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class BaseActivity extends Activity {


    public ConnectionDetector cd;

    public static Realm realm;
    public static RealmConfiguration config;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Initialize facebook
         */


        cd=new ConnectionDetector(this);

        StatusBar();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        config = new RealmConfiguration.Builder()
                .schemaVersion(1) // Must be bumped when the schema changes
                .migration(new Migration())
                .build();

        realm = Realm.getInstance(config);
    }
    public  void StatusBar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccent));
        }

    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}
