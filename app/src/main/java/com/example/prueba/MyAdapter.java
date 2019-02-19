package com.example.prueba;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    RecyclerView recyclerView;
    Context context;
    List<model> model;
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.modelo,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
TextView amb,ins, fic, fec, hi, hf;

        public MyViewHolder(View v) {
            super(v);
            amb=v.findViewById(R.id.AmbM);
            ins=v.findViewById(R.id.instructorM);
            fic=v.findViewById(R.id.FichaM);
            fec=v.findViewById(R.id.FechaM);
            hi=v.findViewById(R.id.HoraIM);
            hf=v.findViewById(R.id.HoraFM);
        }
    }
}
