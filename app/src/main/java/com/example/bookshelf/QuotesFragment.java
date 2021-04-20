package com.example.bookshelf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class QuotesFragment extends Fragment {

    View view;
    EditText quotes_input;
    public static String quotesFrag;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_quotes, container, false);
        quotes_input = (EditText)view.findViewById(R.id.quotes_input);
        quotesFrag=quotes_input.getText().toString().trim();
        return view;
    }
}