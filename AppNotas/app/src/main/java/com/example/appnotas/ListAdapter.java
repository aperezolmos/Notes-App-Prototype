package com.example.appnotas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<ListData> {

    public ListAdapter(@NonNull Context context, ArrayList<ListData> dataArrayList) {
        super(context, R.layout.list_item, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        ListData listData = getItem(position);

        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView tituloNota = view.findViewById(R.id.notaTitulo);
        TextView tiempoNota = view.findViewById(R.id.notaTiempo);
        TextView textoNota = view.findViewById(R.id.notaTexto);
        ImageView fijado = view.findViewById(R.id.fijadoONo_lista);

        // Hacemos que el primer elemento sea una nota fijada (ejemplo) -> solo en la lista del main
        if (position == 0 && getContext().getClass().getSimpleName().equals("MainActivity")){
            fijado.setVisibility(View.VISIBLE);
        }
        tituloNota.setText(listData.titulo);
        tiempoNota.setText(listData.tiempo);
        textoNota.setText(listData.texto);

        return view;
    }
}
