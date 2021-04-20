  package com.example.bookshelf;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

  public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private Fragment cfragment;
    private static final String DATABASE_NAME = "BookShelf.db";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID ="_id";
    private static final String COLUMN_TITLE ="book_title";
    private static final String COLUMN_AUTHOR ="book_author";
    private static final String COLUMN_CONTENT ="book_content";
    private static final String COLUMN_QUOTES ="book_quotes";
   // private static final String COLUMN_IMAGE = ("book_image");



    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
        this.context = context;
    }




//    public MyDatabaseHelper(ContentFragment fragment) {
//        super(fragment, DATABASE_NAME, null, DATABASE_VERSION );
//        this.fragment = fragment;
//    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                  " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                  COLUMN_TITLE + " TEXT, " +
                  COLUMN_AUTHOR + " TEXT, " +
                  COLUMN_CONTENT + " TEXT,  " +
                  COLUMN_QUOTES + " TEXT); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void addBook(String title, String author){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);

        long result=db.insert(TABLE_NAME, null,cv);
        if(result==-1){
            Toast.makeText(context,"Filed.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Added Succesfully!", Toast.LENGTH_SHORT).show();
        }

    }


    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db!=null){
           cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    void updateData(String row_id, String title, String author, String content, String quotes ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_CONTENT,content);
        cv.put(COLUMN_QUOTES,quotes);
      //  cv.put(COLUMN_IMAGE,image);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void insertContent(String content){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CONTENT,content);
        long result=db.insert(TABLE_NAME, null,cv);
        if(result==-1){
            Toast.makeText(context,"Filed.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Added Succesfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void insertQuotes(String quotes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CONTENT,quotes);
        long result=db.insert(TABLE_NAME, null,cv);
        if(result==-1){
            Toast.makeText(context,"Filed.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Added Succesfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_NAME);
    }



}
