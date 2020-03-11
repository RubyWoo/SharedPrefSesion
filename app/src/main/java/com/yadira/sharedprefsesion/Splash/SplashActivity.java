package com.yadira.sharedprefsesion.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.yadira.sharedprefsesion.Util;
import com.yadira.sharedprefsesion.ui.LoginActivity;
import com.yadira.sharedprefsesion.ui.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPreferences();
        actionLogin();

    }

    private void initPreferences() {
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    }

    private void actionLogin() {

        Intent intentMain  = new Intent(this, MainActivity.class);
        Intent intentLogin = new Intent(this,LoginActivity.class);
        boolean flagLogin = Util.getLoginFlagPrefs(prefs);
        if(flagLogin){
            startActivity(intentMain);
        } else {
            startActivity(intentLogin);
        }
        finish();
    }
}
