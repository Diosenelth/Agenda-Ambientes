package com.example.prueba;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView ambientes,instructor,ficha,fecha,horai,horaf;
       // ImageView personPhoto;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cav);
            ambientes=itemView.findViewById(R.id.AmbM);
            instructor=itemView.findViewById(R.id.instructorM);
            ficha=itemView.findViewById(R.id.FichaM);
            fecha=itemView.findViewById(R.id.FechaM);
            horai=itemView.findViewById(R.id.HoraIM);
            horaf=itemView.findViewById(R.id.HoraFM);
        }
    }

    private List<model> model;

    RVAdapter(List<model> model){
        this.model = model;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.modelo, viewGroup, false);
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder personViewHolder, int i) {
        personViewHolder.ambientes.setText(model.get(i).ambiente);
        personViewHolder.instructor.setText(model.get(i).instructor);
        personViewHolder.ficha.setText(model.get(i).ficha);
        personViewHolder.fecha.setText(model.get(i).fecha);
        personViewHolder.horai.setText(model.get(i).horai);
        personViewHolder.horaf.setText(model.get(i).horaf);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }
}
