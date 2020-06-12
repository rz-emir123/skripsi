package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.security.Key;

public class popup extends AppCompatActivity {
    Button button_ok;
    CheckBox pasal1, pasal2, pasal3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .7));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);


        //pilih pasal
        pasal1 = findViewById(R.id.pasal1);
        pasal2 = findViewById(R.id.pasal2);
        pasal3 = findViewById(R.id.pasal3);
        button_ok = (Button) findViewById(R.id.button_ok);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String pasal_dilanggar = "";
                    Integer denda_dilanggar = 0;
                    if (pasal1.isChecked()) {
                        pasal_dilanggar += "1. Tidak Menggunakan Helm\n";
                        denda_dilanggar += 5000000;
                    }
                    if(pasal2.isChecked()){
                        pasal_dilanggar += "2. Pasal Yang Dilanggar\n";
                        denda_dilanggar += 2000000;

                    }
                    if(pasal3.isChecked()){
                        pasal_dilanggar += "3. Tidak Memiliki SIM\n";
                    }
                    String denda_dilanggar1 = Integer.toString(denda_dilanggar);
                    //coba
                    Intent a = getIntent();
                    String nomorktp1 = a.getStringExtra("nomorktp");
                    String jeniskendaraan1 = a.getStringExtra("jeniskendaraan");
                    String jeniskelamin1 = a.getStringExtra("jeniskelamin");
                    String barangbukti1 = a.getStringExtra("barangbukti");
                    String namadepan1 = a.getStringExtra("namadepan");
                    String namabelakang1 = a.getStringExtra("namabelakang");
                    String alamat1 = a.getStringExtra("alamat");
                    String email1 = a.getStringExtra("email");
                    String nomorhandphone1 = a.getStringExtra("nomorhandphone");
                    String nomorpolisi1 = a.getStringExtra("nomorpolisi");

                    Intent i = new Intent(getApplicationContext(), input_polisi.class);
                    i.putExtra("pasal_dilanggar", pasal_dilanggar);
                    i.putExtra("denda_dilanggar", denda_dilanggar1);
                    i.putExtra("nomorktp", nomorktp1);
                    i.putExtra("jeniskendaraan", jeniskendaraan1);
                    i.putExtra("jeniskelamin", jeniskelamin1);
                    i.putExtra("barangbukti", barangbukti1);
                    i.putExtra("namadepan", namadepan1);
                    i.putExtra("namabelakang", namabelakang1);
                    i.putExtra("alamat", alamat1);
                    i.putExtra("email", email1);
                    i.putExtra("nomorhandphone", nomorhandphone1);
                    i.putExtra("nomorpolisi", nomorpolisi1);
                    startActivity(i);
                } catch (Exception e) {
                }
            }
        });
    }

}
