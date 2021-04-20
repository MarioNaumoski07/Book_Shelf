package com.example.bookshelf;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static com.example.bookshelf.ContentFragment.contentFrag;
import static com.example.bookshelf.QuotesFragment.quotesFrag;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input, author_input, content_input, quotes_input;
    Button update_button, delete_button;
    byte[] image;
    Button fragment1, fragment2;
    ImageView buttonImage;

    ImageView imageV;

    String id, title, author, content, quotes ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);



        title_input = (EditText)  findViewById(R.id.title_input2);
        author_input = (EditText)findViewById(R.id.author_input2);
        content_input=(EditText)findViewById(R.id.content_input);
        quotes_input=(EditText)findViewById(R.id.quotes_input);
        update_button = (Button) findViewById(R.id.update_button2);
//        buttonImage= (ImageView) findViewById(R.id.imageView2);
       // imageV=(ImageView)findViewById(R.id.imageView2);

        //First we call this
        getAndSetIntentData();


//        buttonImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    fetchImage(v);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });


        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                title = title_input.getText().toString().trim();
                author = author_input.getText().toString().trim();
                content=contentFrag;
                quotes=quotesFrag;

                myDB.updateData(id, title, author,content,quotes);
            }
        });

        fragment1=findViewById(R.id.fragment1);
        fragment2=findViewById(R.id.fragment2);

        fragment1.setBackgroundColor(Color.GRAY);

        fragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setFragment(new ContentFragment());

            }
        });

        fragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent =new Intent( UpdateActivity.this, ContentFragment.class);
//                startActivity(intent);
                setFragment2(new QuotesFragment());
            }
        });

        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentView, new ContentFragment());
        fragmentTransaction.commit();

    }

    private void setFragment(Fragment Fragment) {
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentView, Fragment);
        fragmentTransaction.commit();
    }
    private void setFragment2(Fragment Fragment) {
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentView, Fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_this){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }




    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") ){

            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            content=getIntent().getStringExtra("content");
            quotes=getIntent().getStringExtra("quotes");



            //Setting Intent Data
            title_input.setText(title);
            author_input.setText(author);


        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }


//    public  void fetchImage(View view) throws IOException {
//
//        MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
//        File folder= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/demoImage.jpg/");
//        FileInputStream fis = new FileInputStream(folder);
//        byte[] image= new byte[fis.available()];
//        fis.read(image);
//        ContentValues values = new ContentValues();
//        values.put("image",image);
//        //myDB.updateData(id, title, author,content,quotes);
//        fis.close();
//        Toast.makeText(this,"Image Fetched", Toast.LENGTH_SHORT).show();
//    }


}