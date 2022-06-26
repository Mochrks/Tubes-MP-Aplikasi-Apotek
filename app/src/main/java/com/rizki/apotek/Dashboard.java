package com.rizki.apotek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {
    Button btnTK,btnObat,btnLaporan,btnRS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //objek button
        btnObat  = findViewById(R.id.btnObat);
        btnLaporan =  findViewById(R.id.btnLaporan);
        btnRS = findViewById(R.id.btnRS);
        btnTK = findViewById(R.id.btnTK);

        btnTK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), layoutAbout.class);
                startActivity(i);
            }

        });
        btnObat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), obat.class);
                startActivity(i);
            }

        });
        btnLaporan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), LaporanPenjualan.class);
                startActivity(i);
            }

        });
        btnRS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), RumahSakit.class);
                startActivity(i);
            }

        });
    }
}