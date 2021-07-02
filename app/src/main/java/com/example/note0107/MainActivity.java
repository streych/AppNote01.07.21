package com.example.note0107;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.note0107.menu_fragment.FragmentAbout;
import com.example.note0107.menu_fragment.FragmentSettings;
import com.example.note0107.menu_fragment.NotesFragmentAdd;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new NotesFragment()).addToBackStack(null).commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (id){
            case R.id.action_add:
                fragmentTransaction.replace(R.id.container, new NotesFragmentAdd()).addToBackStack(null).commit();
                recreate();
                return true;
            case R.id.about:
                fragmentTransaction.replace(R.id.container, new FragmentAbout()).addToBackStack(null).commit();
                recreate();
                return true;
            case R.id.settings:
                fragmentTransaction.replace(R.id.container, new FragmentSettings()).addToBackStack(null).commit();
                recreate();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}