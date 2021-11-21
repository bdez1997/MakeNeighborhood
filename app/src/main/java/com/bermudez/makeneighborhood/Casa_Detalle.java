package com.bermudez.makeneighborhood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Casa_Detalle extends AppCompatActivity {

    Button btnGuardar;
    Button btnBorrar;
    TextView txtsStreet;
    TextView txtNumber;
    TextView txtSuperficie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casa_detalle);

        btnBorrar = findViewById(R.id.btnBorrar);
        txtsStreet = findViewById(R.id.txtStreet);
        txtNumber = findViewById(R.id.txtHouseNumber);
        txtSuperficie = findViewById(R.id.txtDimension);
        btnGuardar = findViewById(R.id.btnGuardar);

        if (MainActivity.boTypeSelection) {

            btnGuardar.setOnClickListener(e -> {
                ActualizarProducto();
                finish();
            });

            txtsStreet.setText(Neightborhood.lstCasas.get(Neightborhood.iCasaSelected).getsCalle());
            txtNumber.setText(String.valueOf(Neightborhood.lstCasas.get(Neightborhood.iCasaSelected).getiNumero()));
            txtSuperficie.setText(String.valueOf(Neightborhood.lstCasas.get(Neightborhood.iCasaSelected).getDoSperficie()));

        } else {
            btnBorrar.setVisibility(View.GONE);
            btnGuardar.setOnClickListener(e -> insertarProducto());
            txtsStreet.setText("");
            txtNumber.setText("");
            txtSuperficie.setText("");
        }
    }

    public void ActualizarProducto() {

        String id_Vecindario = String.valueOf(Neightborhood.iCasaSelected);
        String strStreet = txtsStreet.getText().toString();
        String strNumber = txtNumber.getText().toString();
        String strSuperficie = txtSuperficie.getText().toString();

        if (strStreet.isEmpty() || strNumber.isEmpty() || strSuperficie.isEmpty()) {

            mostrarMensaje("Todos los campos son obligatorios");

        } else {
            DB_SQLite db = new DB_SQLite(this);
            SQLiteDatabase conn = db.getWritableDatabase();

            ContentValues content = new ContentValues();
            content.put("CALLE", strStreet);
            content.put("NUMERO", strNumber);
            content.put("SUPERFICIE", strSuperficie);

            String condicion = "id_Vecindario LIKE '" + id_Vecindario + "'";
            conn.update("VECINDARIO", content, condicion, null);
            conn.close();
            mostrarMensaje("La casa " + strStreet + " " + strNumber + " " + strSuperficie +" ha sido actualizado");
            limpiarCuadros();

        }
    }

    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    private void limpiarCuadros() {
        txtsStreet.setText("");
        txtNumber.setText("");
    }

    public void eliminarProducto(View view) {

        String strNombre = txtsStreet.getText().toString();
        String strNum = txtNumber.getText().toString();


        if (strNombre.equals("") || strNum.equals("")) {
            mostrarMensaje("Debes indicar el nombre y el número");
        } else {
            DB_SQLite db = new DB_SQLite(this);
            SQLiteDatabase conn = db.getWritableDatabase();

            // DELETE FROM PRODUCTO WHERE nombre LIKE 'xxxxxxx'
            String condicion = "CALLE LIKE '" + strNombre + "'" + " AND NUMERO LIKE " + strNum;
            int filasBorradas = conn.delete("VECINDARIO", condicion, null);

            conn.close();
            mostrarMensaje("Se han eliminado " + filasBorradas + "del producto" + strNombre);
            limpiarCuadros();
            finish();
        }
    }

    public void insertarProducto() {

        String txtsStreetName = txtsStreet.getText().toString();
        String strNum = txtNumber.getText().toString();
        String strDimension = txtSuperficie.getText().toString();

        if (txtsStreetName.equals("") || strNum.equals("")) {
            mostrarMensaje("Todos los campos son obligatorios ");
        } else {
            DB_SQLite db = new DB_SQLite(this);
            SQLiteDatabase conn = db.getWritableDatabase();

            ContentValues content = new ContentValues();
            content.put("CALLE", txtsStreetName);
            content.put("NUMERO", strNum);
            content.put("SUPERFICIE", strDimension);

            conn.insert("VECINDARIO", null, content);


            mostrarMensaje("Se ha añadidio a la base de datos");

            conn.close();
            finish();
        }
    }
}
