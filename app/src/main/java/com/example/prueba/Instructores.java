package com.example.prueba;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Instructores extends AppCompatActivity {
    ListView lv1;
    ArrayList<String> lista= new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructores);
        lv1=findViewById(R.id.lv1);

        AdminSQLiteOpenHelper juego = new AdminSQLiteOpenHelper(getBaseContext());
        SQLiteDatabase db = juego.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor=db.rawQuery("SELECT * FROM Instructores",null);

        if (cursor.moveToFirst()){

            do {
                lista.add(cursor.getString(1));
            }while (cursor.moveToNext()) ;

        }cursor.close();

        adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista);
        lv1.setAdapter(adapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             /*   String selectedItem = (String) adapterView.getItemAtPosition(i);
                Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                intent.putExtra("sel",selectedItem);
                startActivity(intent);

                Toast.makeText(getApplicationContext(),selectedItem,Toast.LENGTH_SHORT).show();*/
            }
        });
    }

    public void registro(View view) {
        Intent intent=new Intent(this, Reg_ins.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this, Inicio.class);
        startActivity(intent);
        super.onBackPressed();
    }
}