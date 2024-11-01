package com.example.appnotas;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CuadriculaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cuadricula, container, false);

        // Busca todas las tarjetas en la vista
        final List<CardView> cardViews = new ArrayList<>();
        cardViews.add(view.findViewById(R.id.cuadricula1));
        cardViews.add(view.findViewById(R.id.cuadricula2));
        cardViews.add(view.findViewById(R.id.cuadricula3));
        cardViews.add(view.findViewById(R.id.cuadricula4));
        cardViews.add(view.findViewById(R.id.cuadricula5));
        cardViews.add(view.findViewById(R.id.cuadricula6));
        cardViews.add(view.findViewById(R.id.cuadricula7));
        cardViews.add(view.findViewById(R.id.cuadricula8));

        // Poner algunas notas de otro color (ejemplo)
        cardViews.get(1).setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.amarillo));
        cardViews.get(4).setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.amarillo));
        cardViews.get(7).setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.amarillo));

        // Contenido de cada nota
        String[] listaTitulos = {"Marta 5,4€", "Bolsa piscina", "🐨 animal crossing", "Cita dentista",
                "Receta bizcocho", "—— anime🎐 (para ver) ——", "💶 — gastos amsterdam", "Lista de la compra" };
        String[] listaTiempos = {"hace 3 días", "hace 2 min", "hace 5 días", "hace 13 días",
                "22 abr.", "13 mar.", "2 feb.", "30 ene." };
        int[] listaTextos = {R.string.texto1,R.string.texto2,R.string.texto3,R.string.texto4,
                R.string.texto5,R.string.texto6,R.string.texto7,R.string.texto8};
        int[] listaPreviews = {R.string.texto1_prev,R.string.texto2_prev,R.string.texto3_prev,R.string.texto4_prev,
                R.string.texto5_prev,R.string.texto6_prev,R.string.texto7_prev,R.string.texto8_prev};

        for (int i = 0; i < cardViews.size(); i++){
            CardView notaActual = cardViews.get(i);
            TextView titulo = notaActual.findViewById(R.id.cuadriculaTitulo);
            TextView texto = notaActual.findViewById(R.id.cuadriculaTexto);
            TextView tiempo = notaActual.findViewById(R.id.cuadriculaTiempo);

            if (i == 0){
                ImageView fijado = notaActual.findViewById(R.id.fijadoONo_cuadricula);
                fijado.setVisibility(View.VISIBLE);
            }

            titulo.setText(listaTitulos[i]);
            texto.setText(listaPreviews[i]);
            tiempo.setText(listaTiempos[i]);
        }

        FrameLayout layout = view.findViewById(R.id.notasCuadricula);
        layout.setClickable(true);

        /* Asigna un click de escucha a cada tarjeta.
            >> Hecho manualmente para cada una porque no funcionaba correctamente con bucles. */
        cardViews.get(0).setOnClickListener(view1 -> trasladarNota(listaTitulos,listaTiempos,listaTextos,0));
        cardViews.get(1).setOnClickListener(view2 -> trasladarNota(listaTitulos,listaTiempos,listaTextos,1));
        cardViews.get(2).setOnClickListener(view3 -> trasladarNota(listaTitulos,listaTiempos,listaTextos,2));
        cardViews.get(3).setOnClickListener(view4 -> trasladarNota(listaTitulos,listaTiempos,listaTextos,3));
        cardViews.get(4).setOnClickListener(view5 -> trasladarNota(listaTitulos,listaTiempos,listaTextos,4));
        cardViews.get(5).setOnClickListener(view6 -> trasladarNota(listaTitulos,listaTiempos,listaTextos,5));
        cardViews.get(6).setOnClickListener(view7 -> trasladarNota(listaTitulos,listaTiempos,listaTextos,6));
        cardViews.get(7).setOnClickListener(view8 -> trasladarNota(listaTitulos,listaTiempos,listaTextos,7));

        return view;
    }

    /**
     * Traslada el contenido de la nota que ha sido pulsada a una nueva actividad de Nota.
     *
     * @param l1 lista de títulos
     * @param l2 lista de tiempos
     * @param l3 lista de textos
     * @param indice índice de la nota que se quiere trasladar
     */
    private void trasladarNota(String[] l1, String[] l2, int[] l3, int indice){
        Intent intent = new Intent(getContext(), NotaActivity.class);
        intent.putExtra("titulo", l1[indice]);
        intent.putExtra("tiempo", l2[indice]);
        intent.putExtra("texto", l3[indice]);
        startActivity(intent);
    }
}