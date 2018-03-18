package com.example.android.angela_1202150261_studycase4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

public class GambarAsyncTask extends AppCompatActivity {
    //membuat object
    private EditText edURL;
    private Button btnCari;
    private ImageView imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gambar_async_task);
        //mereferensikan object dengan id
        edURL = findViewById(R.id.edit_url);
        btnCari = findViewById(R.id.cari_image);
        imageUrl = findViewById(R.id.image_url);
    }

    class LoadImage extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... voids) {
            Bitmap bitmap = null;
            //menyimpan url dalam sebuah string
            String url = edURL.getText().toString();

            //mendecode url dan mengget contenya
            try{
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
            }catch (Exception e){
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //mengecek url tidak kosong kalau tidak maka akan dicari kemudian image bakalan diset ke imageview
            if (bitmap != null){
                imageUrl.setImageBitmap(bitmap);
            }else {
                Toast.makeText(GambarAsyncTask.this, "Link Salah", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void find(View view) {
        //mengekseskusi method LoadImage
        new LoadImage().execute();
    }
}
