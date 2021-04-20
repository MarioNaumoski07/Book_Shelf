package com.example.bookshelf;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> book_id, book_title, book_author,book_content;



    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        add_button=findViewById(R.id.add_button);
//        add_Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,Add.class);
//                startActivity(intent);
//
//            }
//        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        book_id=new ArrayList<>();
        book_title=new ArrayList<>();
        book_author=new ArrayList<>();
        book_content=new ArrayList<>();
       // book_image=new ArrayList<>();
        displayData();

        customAdapter = new CustomAdapter(MainActivity.this,this, book_id, book_title, book_author,book_content);
        recyclerView.setAdapter(customAdapter);
        RecyclerView.LayoutManager mLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(mLayoutManager);


     }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

     void displayData(){
         Cursor cursor=myDB.readAllData();
         if(cursor.getCount()==0){
             Toast.makeText(this,"No data.", Toast.LENGTH_SHORT).show();
         }
         else {
             while (cursor.moveToNext()){
                 book_id.add(cursor.getString(0));
                 book_title.add(cursor.getString(1));
                 book_author.add(cursor.getString(2));
                 book_content.add(cursor.getString(3));
//                 byte[] image = cursor.getBlob(0);
//                 Bitmap bmp= BitmapFactory.decodeByteArray(image, 0 , image.length);
//                 imageView.setImageBitmap(bmp);
             }

         }
     }


    public void add_Button(View view) {
        Intent intent = new Intent(MainActivity.this,Add.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
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

}