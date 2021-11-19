package com.bermudez.makeneighborhood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingAddHouse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingAddHouse=findViewById(R.id.floatingAddHouse);

        floatingAddHouse.setOnClickListener(e->{
            Intent i = new Intent(this,Casa_Detalle.class);
            startActivity(i);
        });

        mostrarData();


    }


    private void mostrarData(){

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Casa_Adapter adaptadorAlumno = new Casa_Adapter(this);

        recyclerView.setAdapter(adaptadorAlumno);

        adaptadorAlumno.setOnClickListener(view ->{
            Neightborhood.iCasaSelected = recyclerView.getChildAdapterPosition(view);
            Toast.makeText(this,"Item"+Neightborhood.lstCasas.get(Neightborhood.iCasaSelected).toString(),Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,Casa_Detalle.class);
            startActivity(i);
        });


    }

}