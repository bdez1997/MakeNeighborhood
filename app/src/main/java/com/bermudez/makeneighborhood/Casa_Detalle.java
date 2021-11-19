package com.bermudez.makeneighborhood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Casa_Detalle extends AppCompatActivity {


    TextView txtsStreet;
    TextView txtNumber;
    TextView txtSuperficie;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casa_detalle);

        txtsStreet= findViewById(R.id.txtStreet);
        txtNumber = findViewById(R.id.txtHouseNumber);
        txtSuperficie = findViewById(R.id.txtDimension);



    }

    public void mostrarMensaje(String mensaje){
        Toast.makeText(this,mensaje, Toast.LENGTH_LONG).show();
    }

    private void limpiarCuadros() {
        txtsStreet.setText("");
        txtNumber.setText("");
    }

    public void insertarProducto(View view){

        String txtsStreetName=txtsStreet.getText().toString();
        String strNum=txtNumber.getText().toString();
        String strDimension=txtSuperficie.getText().toString();

        if(txtsStreetName.equals("")||strNum.equals("")){
            mostrarMensaje("Todos los campos son obligatorios ");
        }else{
            DB_SQLite db = new DB_SQLite(this);
            SQLiteDatabase conn = db.getWritableDatabase();

            ContentValues content = new ContentValues();
            content.put("CALLE",txtsStreetName);
            content.put("NUMERO",strNum);
            content.put("SUPERFICIE",strDimension);

            conn.insert("VECINDARIO",null,content);



            mostrarMensaje("Se ha añadidio a la base de datos");

            conn.close();
            finish();
        }


    }
}
