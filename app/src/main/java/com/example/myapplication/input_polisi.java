package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class input_polisi extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private BottomNavigationView menu_bawah;
    Button jeniskendaraan, jeniskelamin, barangbukti, popup, button_next;
    TextView pasal_dilanggar, denda_maximal;
    EditText nomorktp, namadepan, namabelakang, alamat, email, nomorhandphone, nopol;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_polisi);

        menu_bawah = findViewById(R.id.menu_bawah);
        menu_bawah.setOnNavigationItemSelectedListener(this);
        //jabaran form
        jeniskendaraan = findViewById(R.id.button_jk);
        jeniskelamin = findViewById(R.id.button_jkel);
        barangbukti = findViewById(R.id.button_bb);
        nomorktp = (EditText)findViewById(R.id.edit_ktp);
        namadepan = (EditText) findViewById(R.id.edit_nd);
        namabelakang = (EditText) findViewById(R.id.edit_nb);
        alamat = (EditText) findViewById(R.id.edit_alamat);
        email = (EditText) findViewById(R.id.edit_email);
        nomorhandphone = (EditText) findViewById(R.id.edit_nohp);
        nopol = (EditText) findViewById(R.id.edit_nopol);

        //tampilin pasal_dilanggar
        denda_maximal = (TextView) findViewById(R.id.denda_maximal);
        pasal_dilanggar =(TextView) findViewById(R.id.pasal_dilanggar);
        Intent i = getIntent();
        nomorktp.setText(i.getStringExtra("nomorktp"));
        jeniskendaraan.setText(i.getStringExtra("jeniskendaraan"));
        jeniskelamin.setText(i.getStringExtra("jeniskelamin"));
        barangbukti.setText(i.getStringExtra("barangbukti"));
        namadepan.setText(i.getStringExtra("namadepan"));
        namabelakang.setText(i.getStringExtra("namabelakang"));
        alamat.setText(i.getStringExtra("alamat"));
        email.setText(i.getStringExtra("email"));
        nomorhandphone.setText(i.getStringExtra("nomorhandphone"));
        nopol.setText(i.getStringExtra("nomorpolisi"));

        pasal_dilanggar.setText(i.getStringExtra("pasal_dilanggar"));
        denda_maximal.setText(i.getStringExtra("denda_dilanggar"));



        //button next + db firebase
        button_next = (Button)findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomer = "F" + new Date().getTime();
                Map<String, Object> laporan = new HashMap<>();
                laporan.put("nomorktp",nomorktp.getText().toString());
//                laporan.put("Nama Lengkap",namadepan.getText().toString()+" "+namabelakang.getText().toString());
//                laporan.put("Alamat",alamat.getText().toString());
//                laporan.put("Jenis Kendaraan",jeniskendaraan.getText().toString());
//                laporan.put("Jenis Kelamin",jeniskelamin.getText().toString());
//                laporan.put("Barang Bukti",barangbukti.getText().toString());
//                laporan.put("Email",email.getText().toString());
//                laporan.put("Nomor Handphone",nomorhandphone.getText().toString());
//                laporan.put("Nomor Polisi",nopol.getText().toString());
//                laporan.put("Pasal Dilanggar",pasal_dilanggar.getText().toString());
//                laporan.put("Denda Maximal",denda_maximal.getText().toString());


                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("data_tilang")
                        .document( nomer )
                        .set(laporan)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
//                                Log.d(TAG, "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
//                                Log.w(TAG, "Error writing document", e);
                            }
                        });
                Intent i = new Intent(input_polisi.this, input_polisi1.class);
                startActivity(i);
            }
        });

        //pop up
        popup = findViewById(R.id.button_pp);
        popup = (Button)findViewById(R.id.button_pp);
        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomorktp1 = nomorktp.getText().toString();
                String jeniskendaraan1 = jeniskendaraan.getText().toString();
                String jeniskelamin1 = jeniskelamin.getText().toString();
                String barangbukti1 = barangbukti.getText().toString();
                String namadepan1 = namadepan.getText().toString();
                String namabelakang1 = namabelakang.getText().toString();
                String alamat1 = alamat.getText().toString();
                String email1 = email.getText().toString();
                String nomorhandphone1 = nomorhandphone.getText().toString();
                String nomorpolisi1 = nopol.getText().toString();

                Intent i = new Intent(getApplicationContext(), popup.class);
                i.putExtra("nomorktp", nomorktp1);
                i.putExtra("jeniskendaraan1", jeniskendaraan1);
                i.putExtra("jeniskelamin", jeniskelamin1);
                i.putExtra("barangbukti", barangbukti1);
                i.putExtra("namadepan", namadepan1);
                i.putExtra("namabelakang", namabelakang1);
                i.putExtra("alamat", alamat1);
                i.putExtra("email", email1);
                i.putExtra("nomorhandphone", nomorhandphone1);
                i.putExtra("nomorpolisi", nomorpolisi1);
                startActivity(i);
            }
        });





        //button jenis kendaraan
        jeniskendaraan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu dropDownMenu = new PopupMenu(getApplicationContext(), jeniskendaraan);
                dropDownMenu.getMenuInflater().inflate(R.menu.jenis_kendaraan, dropDownMenu.getMenu());
                dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        jeniskendaraan.setText(menuItem.getTitle());
//                        Toast.makeText(getApplicationContext(), "You have clicked " + menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        return true;
                    }
                });
                dropDownMenu.show();
            }
        });

        //button jenis kelamin
        jeniskelamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu dropDownMenu1 = new PopupMenu(getApplicationContext(), jeniskelamin);
                dropDownMenu1.getMenuInflater().inflate(R.menu.jenis_kelamin, dropDownMenu1.getMenu());
                dropDownMenu1.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        jeniskelamin.setText(menuItem.getTitle());
//                        Toast.makeText(getApplicationContext(), "You have clicked " + menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        return true;
                    }
                });
                dropDownMenu1.show();
            }
        });
        //button barang bukti
        barangbukti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu dropDownMenu1 = new PopupMenu(getApplicationContext(), barangbukti);
                dropDownMenu1.getMenuInflater().inflate(R.menu.barang_bukti, dropDownMenu1.getMenu());
                dropDownMenu1.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        barangbukti.setText(menuItem.getTitle());
//                        Toast.makeText(getApplicationContext(), "You have clicked " + menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        return true;
                    }
                });
                dropDownMenu1.show();
            }
        });



    }


    //menu bawah
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.input_data:
                //aksi ketika home di klik
                Intent i = new Intent(input_polisi.this, input_polisi.class);
                startActivity(i);;
                break;
            case R.id.history:
                //aksi ketika profile di klik
                Intent a = new Intent(input_polisi.this, history_datatilang.class);
                startActivity(a);;
                break;
            case R.id.profile:
                //aksi ketika folder di klik
                break;
        }
        return true;
    }
}

