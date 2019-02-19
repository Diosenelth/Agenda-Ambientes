package com.example.prueba;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    private static String name="Db.db";
    private static int version = 1;
    private static SQLiteDatabase.CursorFactory factory = null;

    AdminSQLiteOpenHelper(@Nullable Context context) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(this.getClass().toString(), "Creando base de datos");

        db.execSQL("CREATE TABLE IF NOT EXISTS 'Ambientes'('Cod' INTEGER NOT NULL PRIMARY KEY,'Nombre' TEXT, 'CaM' TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS 'Instructores' ('Id'	INTEGER NOT NULL UNIQUE  PRIMARY KEY, 'Nombre'	TEXT, 'area' TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS 'Fichas' ('Cod_p' INTEGER NOT NULL PRIMARY KEY, Nombre text,'F_I' TEXT, 'F_T' TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS 'Agenda' (Ambiente TEXT, Instructor text,Ficha TEXT,fecha  TEXT, horaI TEXT, horaF TEXT);");


        db.execSQL("insert into Ambientes values (1,'Aula Convencional', 30)");
        db.execSQL("insert into Ambientes values (2,'Taller',20 )");
        db.execSQL("insert into Ambientes values (3,'Aula Tics',20 )");
        db.execSQL("insert into Ambientes values (4,'Unidad Agropecuaria', 40)");
        db.execSQL("insert into Ambientes values (5,'Ambiente Simulado', 15)");
        db.execSQL("insert into Ambientes values (6,'Laboratorio', 30)");
        db.execSQL("insert into Ambientes values (7,'Aula Bilingüismo', 15)");
        db.execSQL("insert into Ambientes values (8,'Aula Tics', 20)");
        db.execSQL("insert into Ambientes values (9,'Unidad Agropecuaria', 40)");
        db.execSQL("insert into Ambientes values (10,'Ambiente Simulado', 15)");

        db.execSQL("insert into Instructores values (10,'a', 15)");
        db.execSQL("insert into Instructores values (11,'b', 15)");

        db.execSQL("insert into Fichas values (10,'123', 'ficha', 67)");
        db.execSQL("insert into Fichas values (11,'1234', 'ficha1', 67)");

        db.execSQL("insert into Agenda values ('A','34', 'ficha1', 'df','sdf','df')");
        db.execSQL("insert into Agenda values ('b','314', 'ficha2', 'ddf','sdaf','ssf')");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
