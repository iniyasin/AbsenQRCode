package com.dummy.absenqrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HalamanMahasiswa extends AppCompatActivity {
    private Button inputDabim;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_mahasiswa);

        inputDabim = (Button)findViewById(R.id.inputDabim);
        logout = (Button)findViewById(R.id.btn_logout);

        inputDabim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanMahasiswa.this, InputDabim.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
