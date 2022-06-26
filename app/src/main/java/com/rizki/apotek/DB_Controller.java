package com.rizki.apotek;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;



public class DB_Controller extends SQLiteOpenHelper {
    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "OBAT.DB", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE OBAT(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAMA_OBAT TEXT UNIQUE, JENIS TEXT,HARGA TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS OBAT;");
        onCreate(sqLiteDatabase);
    }
    //DATA OBAT
    public void insert_obat(String nama_obat, String jenis,String harga){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAMA_OBAT", nama_obat);
        contentValues.put("JENIS", jenis);
        contentValues.put("HARGA", harga);
        this.getWritableDatabase().insertOrThrow("OBAT","",contentValues);
    }

    //delete obat
    public void delete_obat(String nama_obat){
        this.getWritableDatabase().delete("OBAT","NAMA_OBAT='"+nama_obat+"'",null);
    }

    //update obat
    public void update_obat(String old_nama_obat, String new_nama_obat){
        this.getWritableDatabase().execSQL("UPDATE OBAT SET NAMA_OBAT='"+new_nama_obat+"' WHERE NAMA_OBAT'"+old_nama_obat+"'");
    }

    //llist obat
    public void list_all_obat(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM OBAT", null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+"\n");
        }
    }



}