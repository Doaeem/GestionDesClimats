package com.example.gestiondesclimats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ajout_climat extends AppCompatActivity {

    Spinner spin;
    EditText eville, etemp,eprc;
    Button btn1,btn2;
    MyDataClimat db;
    ArrayList<Climat> climats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_climat);

        db=new MyDataClimat(this);
        climats = MyDataClimat.getAllClimats(db.getReadableDatabase());

        spin=findViewById(R.id.sp);
        eville=findViewById(R.id.eville);
        etemp=findViewById(R.id.etemp);
        eprc=findViewById(R.id.eprc);
        btn1=findViewById(R.id.btneff);
        btn2=findViewById(R.id.btnenr);

        ArrayList<String> pays = new ArrayList<>();
        pays.add("Zambie");
        pays.add("Lybie");
        pays.add("Italie");
        pays.add("Espagne");
        pays.add("France");
        pays.add("Canada");

        ArrayAdapter<String> ad=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,pays);
        spin.setAdapter(ad);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eville.getText().clear();
                eprc.getText().clear();
                eprc.setText("0");
                etemp.getText().clear();
                etemp.setText("0");
                eville.requestFocus();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(eville.getText().toString().isEmpty()){
                    Toast.makeText(ajout_climat.this, "Libelle vide", Toast.LENGTH_SHORT).show();
                    eville.requestFocus();
                    return;
                }
                if (eprc.getText().toString() >= 0 || eprc.getText().toString() <= 100) {
                    Toast.makeText(ajout_climat.this, "Pourcentage Faux", Toast.LENGTH_SHORT).show();
                    eprc.requestFocus();
                    return;
                }
                if(eville.getText().toString()==db.getWritableDatabase().toString()) {
                    String ville = eville.getText().toString();
                    int temp = Integer.valueOf(etemp.getText().toString());
                    int prc = Integer.valueOf(eprc.getText().toString());
                    String pays = spin.getSelectedItem().toString();

                    Climat c = new Climat(ville, pays, temp, prc);

                    if (MyDataClimat.UpdateClimat(db.getWritableDatabase(), c) != -1)
                        Toast.makeText(ajout_climat.this, "Modification effectue", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(ajout_climat.this, "Modification echoue", Toast.LENGTH_SHORT).show();
                }
                String ville = eville.getText().toString();
                int temp = Integer.valueOf(etemp.getText().toString());
                int prc = Integer.valueOf(eprc.getText().toString());
                String pays = spin.getSelectedItem().toString();

                Climat c = new Climat(ville, pays, temp, prc);

                if(MyDataClimat.AddClimat(db.getWritableDatabase(),c)!=-1) {
                    Toast.makeText(ajout_climat.this, "Ajout avec succes !", Toast.LENGTH_SHORT).show();
                    eville.getText().clear();
                    eprc.getText().clear();
                    eprc.setText("0");
                    etemp.getText().clear();
                    etemp.setText("0");
                    eville.requestFocus();
                }
                else
                    Toast.makeText(ajout_climat.this, "Ajout echoue !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}