package com.dummy.absenqrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GeneratorQR extends AppCompatActivity {
    private TextView nim;
    private TextView nama;
    private String id;
    private ImageView qrcode;
    private Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator_qr);

        nim = (TextView)findViewById(R.id.nim);
        nama = (TextView)findViewById(R.id.nama);
        qrcode = (ImageView)findViewById(R.id.qrcode);
        btn_logout = (Button)findViewById(R.id.btn_logout);
        Bitmap bitmap = getIntent().getParcelableExtra("pic");
        //Bitmap bitmap1 = getIntent().getParcelableExtra("pic1");
        qrcode.setImageBitmap(bitmap);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
