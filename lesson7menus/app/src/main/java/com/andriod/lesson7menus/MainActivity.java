package com.andriod.lesson7menus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.telecom.Conference;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    private boolean isLandscape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainContainer, new MainFragment())
                .commit();

        if (isLandscape)
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.second_Container, new SecondFragment())
                    .commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        
        return true;
    }
}