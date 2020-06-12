package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class history_datatilang extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView menu_bawah;

    private RecyclerView recyclerview;
    private data_tilang_adapter data_tilang_adapter;
    private List<data_tilang> data_tilang;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_datatilang);
        menu_bawah = findViewById(R.id.menu_bawah);
        menu_bawah.setOnNavigationItemSelectedListener(this);
        menu_bawah.setSelectedItemId(R.id.history);


        recyclerview = findViewById(R.id.recycle_list);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        data_tilang = new ArrayList<>();
        data_tilang_adapter = new data_tilang_adapter(this, data_tilang);
        recyclerview.setAdapter(data_tilang_adapter);
        db = FirebaseFirestore.getInstance();
        db.collection("data_tilang").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if(!queryDocumentSnapshots.isEmpty()){

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            Log.d( "BERHASIL", list.toString());
                            for(DocumentSnapshot d : list){
                                data_tilang p = d.toObject(data_tilang.class);
                                data_tilang.add(p);
                                Log.d( "BERHASIL P", p.toString());

                            }
                        }data_tilang_adapter.notifyDataSetChanged();
                    }
                });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.input_data:
                //aksi ketika home di klik
                Intent i = new Intent(history_datatilang.this, input_polisi.class);
                startActivity(i);;
                break;
            case R.id.history:
                //aksi ketika profile di klik
//                Intent a = new Intent(history_datatilang.this, history_datatilang.class);
//                startActivity(a);;
                break;
            case R.id.profile:
                //aksi ketika folder di klik
                break;
        }
        return true;
    }
}
