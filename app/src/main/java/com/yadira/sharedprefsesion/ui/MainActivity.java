package com.yadira.sharedprefsesion.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.yadira.sharedprefsesion.R;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private SharedPreferences prefsSwicth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        prefsSwicth = getSharedPreferences("PreferencesSwitch", Context.MODE_PRIVATE);
    }

    private void logOut(){

        if(prefsSwicth.getBoolean("remember",false)){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("login",false);
            editor.apply();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        } else{
            removePreferences();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void removePreferences(){
        prefs.edit().clear().apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.logOut:
                logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
