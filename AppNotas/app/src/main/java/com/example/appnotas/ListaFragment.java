package com.example.appnotas;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaFragment extends Fragment {

    private ArrayList<ListData> dataArrayList = new ArrayList<>();
    private ListData listData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista, container, false);
        ListView listView = view.findViewById(R.id.listView);

        String[] listaTitulos = {"Marta 5,4€", "Bolsa piscina", "🐨 animal crossing", "Cita dentista",
                "Receta bizcocho", "—— anime🎐 (para ver) ——", "💶 — gastos amsterdam", "Lista de la compra" };
        String[] listaTiempos = {"hace 3 días", "hace 2 min", "hace 5 días", "hace 13 días",
                "22 abr.", "13 mar.", "2 feb.", "30 ene." };
        int[] listaTextos = {R.string.texto1,R.string.texto2,R.string.texto3,R.string.texto4,
                R.string.texto5,R.string.texto6,R.string.texto7,R.string.texto8};

        for (int i = 0; i < listaTitulos.length; i++){
            listData = new ListData(listaTitulos[i], listaTextos[i], listaTiempos[i]);
            dataArrayList.add(listData);
        }

        ListAdapter listAdapter = new ListAdapter(getActivity(), dataArrayList);
        listView.setAdapter(listAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),NotaActivity.class);
                intent.putExtra("titulo",listaTitulos[i]);
                intent.putExtra("tiempo",listaTiempos[i]);
                intent.putExtra("texto",listaTextos[i]);
                startActivity(intent);
            }
        });

        return view;
    }
}