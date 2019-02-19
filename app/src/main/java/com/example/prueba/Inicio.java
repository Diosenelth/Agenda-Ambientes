package com.example.prueba;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Inicio extends AppCompatActivity {
    ListView lv;
    ArrayList<String> lista= new ArrayList<>();
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        lv=findViewById(R.id.lista);
        //lista=AdminSQLiteOpenHelper.llenar_lv();

        AdminSQLiteOpenHelper juego = new AdminSQLiteOpenHelper(getBaseContext());
        SQLiteDatabase db = juego.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor=db.rawQuery("SELECT * FROM Ambientes",null);

        if (cursor.moveToFirst()){

            do {
                lista.add(cursor.getString(1));
            }while (cursor.moveToNext()) ;

        }cursor.close();

        adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = (String) adapterView.getItemAtPosition(i);
                Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                intent.putExtra("sel",selectedItem);
                startActivity(intent);

                Toast.makeText(getApplicationContext(),selectedItem,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.Instructores){
            Intent intent=new Intent(this, Instructores.class);
            startActivity(intent);
            finish();
        }
        if (id==R.id.Ambientes){
            Intent intent=new Intent(this, ambientes.class);
            startActivity(intent);
            finish();
        }
        if (id==R.id.Fichas){
            Intent intent=new Intent(this, Fichas.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}