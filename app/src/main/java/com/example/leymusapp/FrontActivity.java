package com.example.leymusapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leymusapp.ui.FrontViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class FrontActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private FrontViewModel viewModel;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://leymus-a4763-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference myRef = database.getReference();
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private SharedPreferences prefs = getSharedPreferences("LanguageSetting",MODE_PRIVATE);
    private SharedPreferences.Editor editor = prefs.edit();

    EditText lessonName;
    EditText lessonType;
    Button addLesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(FrontViewModel.class);
        myRef.setValue("Hello, World!");
        checkedIfSignetIn();
        setContentView(R.layout.activity_front);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_news)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        lessonName = findViewById(R.id.lessonNameEditText);
        lessonType = findViewById(R.id.lessonTypeEditText);
        addLesson = findViewById(R.id.addLesson);
        addLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLesson();
            }
        });
    }

    private void checkedIfSignetIn() {
        viewModel.getCurrentUser().observe(this, user ->{
            if(user == null){
                startMainActivity();
            }
        });
    }

    private void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.front, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void signOut(View v){
        viewModel.signOut();
        startMainActivity();
    }

    public void addLesson(){
        if(lessonType.getText().toString().isEmpty() || lessonName.getText().toString().isEmpty()){
            Toast.makeText(this, "Name or type is empty", Toast.LENGTH_SHORT).show();
        }else{
            viewModel.createNewLesson(lessonType.getText().toString(),lessonName.getText().toString());
        }
    }

    public void toggleEnglish(){
        editor.putString("Language","English");
        editor.apply();
    }

    public void toggleDanish(){
        editor.putString("Language","Dansk");
        editor.apply();
    }
}