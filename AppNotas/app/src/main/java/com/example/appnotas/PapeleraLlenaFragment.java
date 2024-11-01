package com.example.appnotas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class PapeleraLlenaFragment extends Fragment {

    private ArrayList<ListData> listaBorradas = new ArrayList<>();
    private ListData listData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_papelera_llena, container, false);
        ListView papeleraListView = view.findViewById(R.id.papelera_listView);

        String[] listaTitulos = {"Titulo 1", "Titulo 2", "Titulo 3", "Titulo 4", "Titulo 5", "Titulo 6", "Titulo 7", "Titulo 8" };
        String[] listaTiempos = {"hace 2 dias", "hace 3 dias", "hace 40 dias", "hace 2 años", "hace 2 dias",
                "hace 3 dias", "hace 40 dias", "hace 2 años" };
        int[] listaTextos = new int[8];

        for (int i = 0; i < listaTitulos.length; i++){
            listaTextos[i] = R.string.loremIpsumLargo;
            listData = new ListData(listaTitulos[i], listaTextos[i], listaTiempos[i]);
            listaBorradas.add(listData);
        }

        ListAdapter listAdapter = new ListAdapter(getActivity(), listaBorradas);
        papeleraListView.setAdapter(listAdapter);

        return view;
    }
}