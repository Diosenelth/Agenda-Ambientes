package com.example.prueba;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class reg_amb extends AppCompatActivity {
    EditText et1, et2, et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_amb);
        et1=findViewById(R.id.id);
        et2=findViewById(R.id.nombre);
        et3=findViewById(R.id.area);
    }

    public void registrar(View view) {
        String id = et1.getText().toString();
        String nombre = et2.getText().toString();
        String area = et3.getText().toString();
        if (id.equals("")) {
            Toast.makeText(this, "Campo Cod Vacio", Toast.LENGTH_SHORT).show();
        } else if (nombre.equals("")) {
            Toast.makeText(this, "Campo Nombre Vacio", Toast.LENGTH_SHORT).show();
        } else if (area.equals("")) {
            Toast.makeText(this, "Campo de Capacidad maxima Vacio", Toast.LENGTH_SHORT).show();
        } else {
            AdminSQLiteOpenHelper juego = new AdminSQLiteOpenHelper(getBaseContext());
            SQLiteDatabase bd = juego.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("Cod", id);
            registro.put("Nombre", nombre);
            registro.put("CaM", area);
            bd.insert("Ambientes", null, registro);
            bd.close();
            et1.setText("");
            et2.setText("");
            et3.setText("");
            Toast.makeText(this, "Ambiente Registrado", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this, ambientes.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
