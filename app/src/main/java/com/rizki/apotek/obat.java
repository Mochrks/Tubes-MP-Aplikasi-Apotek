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

public class obat extends AppCompatActivity {
    EditText nama_obat,jenis,harga;
    TextView textView;
    DB_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obat);

        nama_obat = (EditText)findViewById(R.id.nama_obat_input);
        jenis = (EditText)findViewById(R.id.jenis_input);
        harga = (EditText)findViewById(R.id.harga_input);
        textView = (TextView)findViewById(R.id.textView);

        controller = new DB_Controller(this,"",null,1);
    }

    public void btn_click(View view){
        switch(view.getId()){
            case R.id.btn_add:
                try {
                    controller.insert_obat(nama_obat.getText().toString(),jenis.getText().toString(),harga.getText().toString());
                }catch (SQLiteException e){
                    Toast.makeText(obat.this, "ALREADY EXIST", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_delete:
                controller.delete_obat(nama_obat.getText().toString());
                break;
            case R.id.btn_update:
                AlertDialog.Builder dialog = new AlertDialog.Builder(obat.this);
                dialog.setTitle("Masukan  nama obat baru ");

                final EditText new_firstname = new EditText(this);
                dialog.setView(new_firstname);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        controller.update_obat(nama_obat.getText().toString(),new_firstname.getText().toString());
                    }
                });
                dialog.show();
                break;
            case R.id.btn_list:
                controller.list_all_obat(textView);
                break;
        }

    }
}