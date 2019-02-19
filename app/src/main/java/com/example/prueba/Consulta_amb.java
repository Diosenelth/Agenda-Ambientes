package com.example.prueba;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Consulta_amb extends AppCompatActivity {
    RecyclerView rv;
    private List<model> modelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_amb);


        rv=findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        modelo = new ArrayList<>();
        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        //modelo.add(new model("a","n","n","i","k","o"));

        AdminSQLiteOpenHelper juego = new AdminSQLiteOpenHelper(getBaseContext());
        SQLiteDatabase db = juego.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor=db.rawQuery("SELECT * FROM Agenda",null);

        if (cursor.moveToFirst()){

            do {
                String a=cursor.getString(0);
                String b=cursor.getString(1);
                String c=cursor.getString(2);
                String d=cursor.getString(3);
                String e=cursor.getString(4);
                String f=cursor.getString(5);
                modelo.add(new model(a,b,c,d,e,f));
            }while (cursor.moveToNext()) ;

        }cursor.close();
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(modelo);
        rv.setAdapter(adapter);
    }
}
