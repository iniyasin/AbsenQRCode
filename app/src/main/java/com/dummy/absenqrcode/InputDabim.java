package com.dummy.absenqrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

public class InputDabim extends AppCompatActivity {
    private EditText nim;
    private EditText nama;
    private EditText materi;
    private EditText progres;
    private EditText masalah;
    private EditText revisi;
    private Button submit;
    private Button logout;

    private final String IMAGE_DIRECTORY = "/QRCode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_dabim);

        nim = (EditText)findViewById(R.id.nim);
        nama = (EditText)findViewById(R.id.nama);
        materi = (EditText)findViewById(R.id.materi);
        progres = (EditText)findViewById(R.id.progres);
        masalah = (EditText)findViewById(R.id.masalah);
        revisi = (EditText)findViewById(R.id.revisi);
        submit = (Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
    }

    private void submit(){
        //inisiasi dulu variabelnya
        final String NIM = nim.getText().toString().trim();
        final String Nama = nama.getText().toString().trim();
        final String Materi = materi.getText().toString().trim();
        final String Progress = progres.getText().toString().trim();
        final String Masalah = masalah.getText().toString().trim();
        final String Revisi = revisi.getText().toString().trim();

        class Submit extends AsyncTask<Void, Void, String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InputDabim.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(InputDabim.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_NIM,NIM);
                params.put(Konfigurasi.KEY_NAMA,Nama);
                params.put(Konfigurasi.KEY_MATERI,Materi);
                params.put(Konfigurasi.KEY_PROGRES,Progress);
                params.put(Konfigurasi.KEY_MASALAH,Masalah);
                params.put(Konfigurasi.KEY_REVISI,Revisi);

                RequestHandler rh = new RequestHandler();
                return rh.sendPostRequest(Konfigurasi.URL_ADD, params);
            }
        }

        Submit submit = new Submit();
        submit.execute();

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(NIM, BarcodeFormat.QR_CODE,
                    200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            String path = saveImage(bitmap);

            Intent intent = new Intent(InputDabim.this, GeneratorQR.class);
            intent.putExtra("pic", bitmap);
            startActivity(intent);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public String saveImage(Bitmap myBitmap){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.

        if (!wallpaperDirectory.exists()) {
            Log.d("dirrrrrr", "" + wallpaperDirectory.mkdirs());
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();   //give read write permission
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
