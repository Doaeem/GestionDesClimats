package com.example.gestiondesclimats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyDataClimat db;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MyDataClimat(this);
        ArrayList<Climat> climats = new ArrayList<>();
        climats.add(new Climat(1,"Tetouan","Maroc",33,10));
        climats.add(new Climat(2,"Tanger","Maroc",34,10));
        climats.add(new Climat(3,"Marrakech","Maroc",41,5));
        climats.add(new Climat(4,"Paris","France",28,20));
        climats.add(new Climat(5,"Sevilla","Espagne",32,15));

        for(Climat p : climats)
            MyDataClimat.AddClimat(db.getWritableDatabase(),p);

        lst = findViewById(R.id.Lst);

        ArrayList<Climat> allClimats = MyDataClimat.getAllClimats(db.getReadableDatabase());

        ArrayList<String> clmts = new ArrayList<>();

        for(Climat e : allClimats){
            clmts.add(e.getNomVille() + "Température : " + e.getTemperature() + " °C " + "\n Pour de nuages :" + e.getPourcentageNuages() + "%");
        }

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,clmts);
        lst.setAdapter(ad);

    }
}