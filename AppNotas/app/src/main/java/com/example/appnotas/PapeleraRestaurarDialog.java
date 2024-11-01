package com.example.appnotas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class PapeleraRestaurarDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.MyAlertDialogStyle);

        builder.setTitle("Restaurar notas")
                .setMessage("¿Está seguro de que desea restaurar todas las notas?")
                .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Confirmar", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.pantalla_papelera, new PapeleraVaciaFragment()).commit();
                        Toast.makeText(getContext(), "Notas restauradas", Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }
}
