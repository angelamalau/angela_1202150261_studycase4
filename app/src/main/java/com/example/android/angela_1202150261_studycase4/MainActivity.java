package com.example.android.angela_1202150261_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //membuat object
    private Button btnName, btnImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mereferensikan object dengan id
        btnName = findViewById(R.id.cari_nama);
        btnImage = findViewById(R.id.cari_gambar);

        //fungsi ketika button di klik maka akan melakukan sebuah aktivitas
        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NamaAsyncTask.class));
            }
        });

        //fungsi ketika button di klik maka akan melakukan sebuah aktivitas
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GambarAsyncTask.class));
            }
        });
    }
}
