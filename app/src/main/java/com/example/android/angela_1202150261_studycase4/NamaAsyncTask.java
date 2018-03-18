package com.example.android.angela_1202150261_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NamaAsyncTask extends AppCompatActivity {
    Handler handler = new Handler();
    int counter = 0;
    private ProgressDialog PD;
    private ListView lvItem;
    public String mahasiswa[] = new String[]{
            "Angel", "Karina", "Khairen", "Muti", "Putri", "Adila",
            "Ardi", "Yuzar", "Hakim", "Tesar", "Brian"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nama_async_task);
        //mereferensikan object dengan id
        lvItem = findViewById(R.id.list_view);
        //mengeset array kedalam adapter
        lvItem.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
    }

    class MyAsyncTask extends AsyncTask<Void, String, String> {
        ArrayAdapter<String> adapter;

        @Override
        protected void onPreExecute() {
            //mengambil data array yang di simpan dalam sebuah adapter
            adapter = (ArrayAdapter<String>) lvItem.getAdapter();

            //membuat object progress dialog
            PD = new ProgressDialog(NamaAsyncTask.this);
            //membuat button cencel
            PD.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            //mengeset judul
            PD.setTitle("Loading Data");
            //mengeset panjang maksimal loading
            PD.setMax(mahasiswa.length);
            PD.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            //melakukan pengulangan untuk memunculkan nama nama
            for (String Names : mahasiswa) {
                publishProgress(Names);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Semua nama telah tertulis";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //menambahkan nama tersebut dimulai dengat indeks ke nol
            adapter.add(values[0]);
            //increaments untuk loading di bar
            Integer current_status = (int) ((counter/(float)mahasiswa.length)*100);
            //mengeset progres
            PD.setProgress(current_status);
            //mengeset messagae dalam bentuk progress
            PD.setMessage(String.valueOf(current_status+"%"));
            counter++;
            //kondisi jika progress sudah maksimal makan progress dialognya kek close
            if (counter == mahasiswa.length){
                PD.dismiss();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //membuat toast message
            Toast.makeText(NamaAsyncTask.this, result, Toast.LENGTH_LONG).show();
        }
    }

    public void Start(View view) {
        //membuat object MyAsyncTask
        MyAsyncTask mAT = new MyAsyncTask();
        //ekseskusi method MyAsyncTask
        mAT.execute();
    }
}
