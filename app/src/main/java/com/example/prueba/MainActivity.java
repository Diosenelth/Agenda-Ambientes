package com.example.prueba;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText usuario, password;
    String usu, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdminSQLiteOpenHelper adminSQLiteOpenHelper =new AdminSQLiteOpenHelper(getBaseContext());
        SQLiteDatabase db = adminSQLiteOpenHelper.getWritableDatabase();

        usuario=findViewById(R.id.usuario);
        password=findViewById(R.id.password);
    }

    public void iniciar(View view) {
        usu=usuario.getText().toString();
        pass=password.getText().toString();


        if (usu.equals("admin")&&pass.equals("123456")){
            Intent intent=new Intent(this,Inicio.class);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(this,"Usuario incorrecto", Toast.LENGTH_SHORT).show();
        }
    }

    public void cons(View view) {
        Intent intent=new Intent(this, Consulta_amb.class);
        startActivity(intent);
    }
}
