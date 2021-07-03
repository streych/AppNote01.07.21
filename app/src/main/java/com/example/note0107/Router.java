package com.example.note0107;

import androidx.fragment.app.FragmentManager;

import com.example.note0107.menu_fragment.FragmentAbout;
import com.example.note0107.menu_fragment.FragmentSettings;
import com.example.note0107.menu_fragment.NotesFragmentAdd;

public class Router {
    private final FragmentManager fragmentManager;

    public Router(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void showNotes(){
        fragmentManager.beginTransaction().replace(R.id.container, new NotesFragment()).addToBackStack(null).commit();

    }

    public void showInfo(){
        fragmentManager.beginTransaction().replace(R.id.container, new FragmentAbout()).addToBackStack(null).commit();

    }

    public void showAdd(){
        fragmentManager.beginTransaction().replace(R.id.container, new NotesFragmentAdd()).addToBackStack(null).commit();
    }

    public void showSettings(){
        fragmentManager.beginTransaction().replace(R.id.container, new FragmentSettings()).addToBackStack(null).commit();

    }

    public void showAuth(){
        fragmentManager.beginTransaction().replace(R.id.container, new AuthFragment()).commit();
    }
}
