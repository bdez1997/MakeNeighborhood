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
    public static boolean boTypeSelection = false;

    FloatingActionButton floatingAddHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        floatingAddHouse = findViewById(R.id.floatingAddHouse);

        floatingAddHouse.setOnClickListener(e -> {
            boTypeSelection=false;
            Intent i = new Intent(this, Casa_Detalle.class);
            startActivity(i);
        });
        mostrarData();

    }
    @Override
    public void onResume(){
        super.onResume();
        Neightborhood.lstCasas.clear();
        mostrarData();

    }

    private void mostrarData() {
        listarProducto();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Casa_Adapter casaAdapter = new Casa_Adapter(this);

        recyclerView.setAdapter(casaAdapter);

        casaAdapter.setOnClickListener(view -> {
            boTypeSelection=true;
            Neightborhood.iCasaSelected = recyclerView.getChildAdapterPosition(view);
            Toast.makeText(this, "Item" + Neightborhood.lstCasas.get(Neightborhood.iCasaSelected).toString(), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Casa_Detalle.class);
            startActivity(i);
        });

    }

    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("Range")
    public void listarProducto() {

        DB_SQLite db = new DB_SQLite(this);
        SQLiteDatabase conn = db.getWritableDatabase();

        String sqlTableName = "VECINDARIO";
        String[] sqlFields = {"id_Vecindario","CALLE", "NUMERO", "SUPERFICIE"};
        String sqlWhere = "";
        String sqlGroupBy = "";
        String sqlHaving = "";
        String sqlOrderBy = "CALLE ASC";


        Cursor cursor = conn.query(sqlTableName, sqlFields, sqlWhere, null, sqlGroupBy, sqlHaving, sqlOrderBy);

        if (cursor.getCount() == 0) {
            mostrarMensaje("La tabla está vacía ");

        } else {
            cursor.moveToFirst();
            do {
                Integer id_vecindario = cursor.getInt(cursor.getColumnIndex("id_Vecindario"));
                String dataNombreCasa = cursor.getString(cursor.getColumnIndex("CALLE"));
                int iNumCasa = cursor.getInt(cursor.getColumnIndex("NUMERO"));
                double doSuperficie = cursor.getDouble(cursor.getColumnIndex("SUPERFICIE"));

                Neightborhood.lstCasas.add(new Casa(id_vecindario,dataNombreCasa, iNumCasa, doSuperficie));

            } while (cursor.moveToNext());

        }
        conn.close();
    }

}