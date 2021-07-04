package com.example.note0107;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DialogFragmentAdd extends BottomSheetDialogFragment {
    //private OnDialogListener dialogListener;

    public static DialogFragmentAdd newInstance() {
        DialogFragmentAdd fragment = new DialogFragmentAdd();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    //public void setOnDialogListener()

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog_add, container, false);
    }
}