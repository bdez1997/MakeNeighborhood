package com.bermudez.makeneighborhood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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
        listarProducto();

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

    public void mostrarMensaje(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("Range")
    public void listarProducto(){

        DB_SQLite db = new DB_SQLite(this);
        SQLiteDatabase conn = db.getWritableDatabase();

        String sqlTableName="VECINDARIO";
        String[] sqlFields = {"CALLE","NUMERO","SUPERFICIE"};
        String sqlWhere = "";
        String sqlGroupBy="" ;
        String sqlHaving="" ;
        String sqlOrderBy ="CALLE ASC";


        Cursor cursor = conn.query(sqlTableName,sqlFields,sqlWhere,null,sqlGroupBy,sqlHaving,sqlOrderBy);

        if(cursor.getCount()==0){
            mostrarMensaje("La tabla está vacía ");

        }else{
            cursor.moveToFirst();
            do{

                String dataNombreProducto = cursor.getString(cursor.getColumnIndex("CALLE"));
                int dataCantidadProducto = cursor.getInt(cursor.getColumnIndex("NUMERO"));
                double dataSelectedProducto = cursor.getDouble(cursor.getColumnIndex("SUPERFICIE"));

                Neightborhood.lstCasas.add(new Casa(dataNombreProducto,dataCantidadProducto,dataSelectedProducto));

            }while(cursor.moveToNext());

        }
        conn.close();
    }

}