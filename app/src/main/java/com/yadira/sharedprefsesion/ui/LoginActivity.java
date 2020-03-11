package com.yadira.sharedprefsesion.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.yadira.sharedprefsesion.R;
import com.yadira.sharedprefsesion.Util;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail,etPassword;
    private Switch schRemember;
    private Button btnLogin;

    private SharedPreferences prefs;
    private SharedPreferences prefsSwicth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindUI();
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        prefsSwicth = getSharedPreferences("PreferencesSwitch", Context.MODE_PRIVATE);
        schRemember.setChecked(prefsSwicth.getBoolean("schCheck",false));
        setCredentialsIfExist();
        events();
    }

    private void bindUI() {
        etEmail = findViewById(R.id.editText);
        etPassword = findViewById(R.id.editText2);
        schRemember = findViewById(R.id.switch1);
        btnLogin = findViewById(R.id.button);
    }

    private void setCredentialsIfExist(){
        String email = Util.getUserMailPrefs(prefs);
        String password = Util.getUserPassPrefs(prefs);

        if(schRemember.isChecked()){
            if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
                etEmail.setText(email);
                etPassword.setText(password);
            }
        }
    }

    private void events() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if(login(email,password)){
                    goToMain();
                    saveOnPreferences(email,password);
                }
            }
        });

        schRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    SharedPreferences.Editor editor = prefsSwicth.edit();
                    editor.putBoolean("schCheck",true);
                    editor.putBoolean("remember",true);
                    editor.apply();
                }else{
                    SharedPreferences.Editor editor = prefsSwicth.edit();
                    editor.putBoolean("schCheck",false);
                    editor.putBoolean("remember",false);
                    editor.apply();
                }
            }
        });
    }

    private boolean login(String email, String password){

        if(!isValidEmail(email)){
            etEmail.setError("Introduce un email");
            return false;
        }else if(!isValidPassword(password)){
            etPassword.setError("Debe tener mas de 3 caracteres");
            return false;
        }else{
            return true;
        }
    }

    //Método clave!!!!!!!
    private void saveOnPreferences(String email, String password){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email",email);
            editor.putString("pass",password);
            editor.putBoolean("login",true);
            editor.apply();
    }

    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password){
        return password.length()>= 4;
    }

    private void goToMain(){
        Toast.makeText(LoginActivity.this, "Sesión iniciada correctamente", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(i);
        //Destroy this activity
        finish();
    }
}
