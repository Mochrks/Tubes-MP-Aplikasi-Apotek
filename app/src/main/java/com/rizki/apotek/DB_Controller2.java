package com.rizki.apotek;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class DB_Controller2  extends SQLiteOpenHelper {
    public DB_Controller2(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Laporan.DB", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE LAPORAN(ID INTEGER PRIMARY KEY AUTOINCREMENT, TANGGAL TEXT UNIQUE, JUMLAH TEXT,NAMA_OBAT TEXT,TOTAL TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LAPORAN;");
        onCreate(sqLiteDatabase);
    }

    //DATA LAPORAN
    public void insert_laporan(String tanggal, String jumlah, String nama_obat, String total) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TANGGAL", tanggal);
        contentValues.put("JUMLAH", jumlah);
        contentValues.put("NAMA_OBAT", nama_obat);
        contentValues.put("TOTAL", total);
        this.getWritableDatabase().insertOrThrow("LAPORAN", "", contentValues);
    }
    //delete laporan

    public void delete_laporan(String tanggal) {
        this.getWritableDatabase().delete("LAPORAN", "TANGGAL='" + tanggal + "'", null);
    }

    //update laporan
    public void update_laporan(String old_tanggal, String new_tanggal) {
        this.getWritableDatabase().execSQL("UPDATE LAPORAN SET TANGGAL='" + new_tanggal + "' WHERE NAMA_OBAT'" + old_tanggal + "'");
    }

    //list laporan
    public void list_all_laporan(TextView textView) {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM LAPORAN", null);
        textView.setText("");
        while (cursor.moveToNext()) {
            textView.append(cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3) + " " + cursor.getString(4) + "\n");
        }
    }

}

