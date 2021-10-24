package com.example.leymus;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;
    TextView seasonHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        seasonHeader = findViewById(R.id.SeasonHeader);

        viewModel.getMainPageTextFiles().observe(this, new Observer<List<File>>() {
            @Override
            public void onChanged(List<File> files) {
                setMainPageText(files);
            }
        });
    }

    private void setMainPageText(List<File> files){
        try {
            FileInputStream fin = new FileInputStream(files.get(0).getCanonicalFile());
            int i = 0;
            String s = files.get(0).getName() + files.get(0).getPath() + files.get(0).getAbsoluteFile().getName();
            while((i=fin.read())!=-1){
                s += (char)i;
            }
            seasonHeader.setText(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}