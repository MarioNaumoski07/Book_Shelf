package com.example.bookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add extends AppCompatActivity {

    EditText title_input, author_intput;
    Button add_Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input=findViewById(R.id.title_Input);
        author_intput=findViewById(R.id.author_input);
        add_Button=findViewById(R.id.add_button);
        add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB=new MyDatabaseHelper(Add.this);
                myDB.addBook(title_input.getText().toString().trim(),
                        author_intput.getText().toString().trim());
            }
        });


    }
}