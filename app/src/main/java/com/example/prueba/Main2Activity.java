package com.example.prueba;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    TextView tv;
    EditText fecha,horai,horaf;
    Spinner spinner,spinner2;

    ArrayList<String> lista= new ArrayList<>();
    ArrayList<String> lista1= new ArrayList<>();
    String va,feh,hoi,hof;


    private static final String CERO = "0";
    private static final String BARRA = "/";

    private static final String DOS_PUNTOS = ":";


    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();
    SQLiteDatabase db;

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        fecha=findViewById(R.id.edate);
        horai=findViewById(R.id.etime);
        horaf=findViewById(R.id.etimef);
        spinner=findViewById(R.id.spinner);
        spinner2=findViewById(R.id.spinner2);
        fecha.setOnClickListener(this);
        horai.setOnClickListener(this);
        horaf.setOnClickListener(this);
        tv=findViewById(R.id.tv1);
        va=getIntent().getStringExtra("sel");
        tv.setText(va);




        AdminSQLiteOpenHelper juego = new AdminSQLiteOpenHelper(getBaseContext());
        db = juego.getWritableDatabase();
        spinins();
        spinfic();
    }

    public void spinins(){
        @SuppressLint("Recycle") Cursor cursor=db.rawQuery("SELECT * FROM Instructores",null);
        if (cursor.moveToFirst()){

            do {
                lista.add(cursor.getString(1));
            }while (cursor.moveToNext()) ;
        }cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lista);
        spinner.setAdapter(adapter);
    }

    public void spinfic(){
        @SuppressLint("Recycle") Cursor cursor1=db.rawQuery("SELECT * FROM Fichas",null);
        if (cursor1.moveToFirst()){

            do {
                lista1.add(cursor1.getString(1));
            }while (cursor1.moveToNext()) ;
        }cursor1.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lista1);
        spinner2.setAdapter(adapter);

    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this, Inicio.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edate:
                obtenerFecha();
                break;
            case R.id.etime:
                obtenerHorainicial();
                break;
            case R.id.etimef:
                obtenerHorafinal();
                break;
        }
    }


    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;

                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);

                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);

                //Muestro la fecha con el formato deseado
                fecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                feh=dayOfMonth+"/"+month+"/"+year;
            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            //También puede cargar los valores que usted desee

        },anio, mes, dia);
        recogerFecha.getDatePicker().setMinDate(System.currentTimeMillis());
        //Muestro el widget
        recogerFecha.show();
    }

    private void obtenerHorainicial(){
        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10

                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10

                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                //Muestro la hora con el formato deseado
                horai.setText(horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM);
                hoi=hourOfDay+":"+minute;
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);
        recogerHora.show();
    }

    private void obtenerHorafinal(){
        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                //Muestro la hora con el formato deseado
                horaf.setText(horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM);
                hof=hourOfDay+":"+minute;
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }

    public void ingresar(View view) {
        String fechab, horab, horafb, ins, fi;
        fechab=String.valueOf(fecha);
        horab=String.valueOf(horai);
        horafb=String.valueOf(horaf);
        ins=spinner.getSelectedItem().toString();
        fi=spinner2.getSelectedItem().toString();

        if (fechab.equals("")) {
            Toast.makeText(this, "ingresar fecha", Toast.LENGTH_SHORT).show();
        } else if (horab.equals("")) {
            Toast.makeText(this, "Ingresar hora inicial", Toast.LENGTH_SHORT).show();
        } else if (horafb.equals("")) {
            Toast.makeText(this, "Ingresar hora final", Toast.LENGTH_SHORT).show();
        } else if(ins.equals("")) {
            Toast.makeText(this, "Ingresar Instructor", Toast.LENGTH_SHORT).show();
        }else if(fi.equals("")) {
            Toast.makeText(this, "Ingresar Ficha", Toast.LENGTH_SHORT).show();
        } else {
            AdminSQLiteOpenHelper juego = new AdminSQLiteOpenHelper(getBaseContext());
            SQLiteDatabase bd = juego.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("Ambiente", va);
            registro.put("Instructor", ins);
            registro.put("Ficha", fi);
            registro.put("fecha", feh);
            registro.put("horai", hoi);
            registro.put("horaf", hof);
            bd.insert("Agenda", null, registro);
            bd.close();
            Toast.makeText(this, "Registrado a la agenda", Toast.LENGTH_SHORT).show();
        }

    }
}
