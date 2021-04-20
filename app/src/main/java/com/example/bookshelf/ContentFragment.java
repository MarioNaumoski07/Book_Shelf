package com.example.bookshelf;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class ContentFragment extends Fragment {

    View view;
    EditText title_input, author_input,content_input;
    Button insert_button;
    MyDatabaseHelper mydb;
    FragmentToActivity mCallback;
    public static  String contentFrag;
    String contentf,quotes,id,author,title;
    byte[] image;
    ImageView imageV;

    ArrayList<String> contents;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view = inflater.inflate(R.layout.fragment_content, container, false);

        //content_input=(EditText) view.findViewById(R.id.content_input);
        SharedPreferences settings=this.getActivity().getSharedPreferences("PREFS",0);

//        if(content_input!=null)
//        {
//            contentf=content_input.getText().toString().trim();
//        }

        insert_button=(Button)view.findViewById(R.id.update_button2);

//        insert_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MyDatabaseHelper myDB = new MyDatabaseHelper(ContentFragment.this);
//                content_input=(EditText)getActivity().findViewById(R.id.content_input);
//                contentFrag=content_input.getText().toString().trim();
//                insert_button=(Button)view.findViewById(R.id.update_button2);
//               content=content_input.getText().toString().trim();
//                myDB.updateData(id, title, author,content,quotes);
//            }
//        });
        return  view;
    }



    @Override public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);
        Intent intent=getActivity().getIntent();
        title_input = (EditText) getActivity().findViewById(R.id.title_input2);
        author_input = (EditText)getActivity().findViewById(R.id.author_input2);
        content_input=(EditText) getActivity().findViewById(R.id.content_input);

        getAndSetIntentData();
       //displayData();
        insert_button=(Button)getActivity().findViewById(R.id.update_button2);
        insert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(getActivity());
                title = title_input.getText().toString().trim();
                author = author_input.getText().toString().trim();
                contentf=content_input.getText().toString().trim();

                contentFrag=contentf;
                myDB.updateData(id, title, author,contentf,quotes);
            }
        });

    }

    void getAndSetIntentData(){
        if(true ){

            //Getting Data from Intent
            id = getActivity().getIntent().getStringExtra("id");
            title = getActivity().getIntent().getStringExtra("title");
            author = getActivity().getIntent().getStringExtra("author");
            contentf=getActivity().getIntent().getStringExtra("content");

            //Setting Intent Data
            title_input.setText(title);
            author_input.setText(author);
            if(contentf!=null){
                content_input.setText(contentf);

            }


        }
    }

    void displayData(){
        Cursor cursor=mydb.readAllData();
        if(cursor.getCount()==0){
            Toast.makeText(this.getActivity(),"No data.", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
               contents.add(cursor.getString(3));
            }

        }
    }

//    public  void fetchImage(View view) throws IOException {
//
//        File folder= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/demoImage.jpg/");
//        FileInputStream fis = new FileInputStream(folder);
//        byte[] image= new byte[fis.available()];
//        fis.read(image);
//        ContentValues values = new ContentValues();
//        values.put("image",image);
//        mydb.updateData(id, title, author,contentf,quotes,image);
//        fis.close();
//        Toast.makeText(this.getActivity(),"Image Fetched", Toast.LENGTH_SHORT).show();
//    }

}