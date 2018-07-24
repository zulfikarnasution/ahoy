package com.example.acer.tbcrudmysql;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.acer.tbcrudmysql.Dosen.MainActivityDosen;
import com.example.acer.tbcrudmysql.Jadwal.MainActivityJadwal;
import com.example.acer.tbcrudmysql.Mahasiswa.MainActivityMhs;

public class TampilanUtama extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilan_utama);
    }

    public void klik1(View v) {
        Intent i = (new Intent(TampilanUtama.this, MainActivityMhs.class));
        startActivity(i);
    }
    public void klik2(View v) {
        Intent i = (new Intent(TampilanUtama.this, MainActivityJadwal.class));
        startActivity(i);
    }

    public void klik3(View v) {
        Intent i = (new Intent(TampilanUtama.this, MainActivityDosen.class));
        startActivity(i);
    }


}
