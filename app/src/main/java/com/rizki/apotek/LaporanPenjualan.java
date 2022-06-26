package com.rizki.apotek;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LaporanPenjualan extends AppCompatActivity {
    EditText tanggal,jumlah,nama_obat,total;
    TextView textView;

    DB_Controller2 controller2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_penjualan);

        tanggal = (EditText)findViewById(R.id.tanggal_input);
        jumlah = (EditText)findViewById(R.id.jumlah_input);
        nama_obat = (EditText)findViewById(R.id.nama_obat_input);
        total = (EditText)findViewById(R.id.total_input);
        textView = (TextView)findViewById(R.id.textView);

        controller2 = new DB_Controller2(this,"",null,1);
    }

    public void btn_click(View view){
        switch(view.getId()){
            case R.id.btn_add:
                try {
                    controller2.insert_laporan(tanggal.getText().toString(),jumlah.getText().toString(),nama_obat.getText().toString(),total.getText().toString());
                }catch (SQLiteException e){
                    Toast.makeText(LaporanPenjualan.this, "ALREADY EXIST", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_delete:
                controller2.delete_laporan(tanggal.getText().toString());
                break;
            case R.id.btn_update:
                AlertDialog.Builder dialog = new AlertDialog.Builder(LaporanPenjualan.this);
                dialog.setTitle("Masukan  laporan baru");

                final EditText new_firstname = new EditText(this);
                dialog.setView(new_firstname);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        controller2.update_laporan(tanggal.getText().toString(),new_firstname.getText().toString());
                    }
                });
                dialog.show();
                break;
            case R.id.btn_list:
                controller2.list_all_laporan(textView);
                break;
        }

    }
}