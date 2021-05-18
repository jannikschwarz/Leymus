package com.example.leymusapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.leymusapp.ui.MainActivityViewModel;
import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 42;
    private MainActivityViewModel viewModel;
    private Button login;
    private SharedPreferences prefs = getSharedPreferences("LanguageSetting",MODE_PRIVATE);
    private SharedPreferences.Editor editor = prefs.edit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        checkIfSignetIn();
        setContentView(R.layout.activity_main);
        String language = prefs.getString("Language","default_name");
        if(language.equals("English")){

        }else if(language.equals("Dansk")){

        }else{

        }
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    public void checkIfSignetIn(){
        viewModel.getCurrentUser().observe(this,user -> {
            if(user != null){
                goToFrontActivity();
            }
        });
    }

    private void goToFrontActivity() {
        startActivity(new Intent(this,FrontActivity.class));
        finish();
    }

    public void signIn(){
        List<AuthUI.IdpConfig> provders = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(provders)
                .setLogo(R.drawable.leymus_logo_transparent).build();

        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            handleSignInRequest(resultCode);
        }
    }

    private void handleSignInRequest(int resultCode) {
        if(resultCode == RESULT_OK){
            goToFrontActivity();
        }else{
            Toast.makeText(this,"SIGN IN CANCELLED",Toast.LENGTH_SHORT).show();
        }
    }
}