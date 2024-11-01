package com.example.appnotas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class NotaBorrarDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.MyAlertDialogStyle);

        builder.setTitle("Eliminar nota")
                .setMessage("¿Está seguro de que desea eliminar esta nota de forma permanente?")
                .setPositiveButton("No", (dialogInterface, i) -> { })
                .setNegativeButton("Eliminar nota", (dialogInterface, i) -> {
                    // Redirigir actividad a la vista inicial del listado de notas
                    Toast.makeText(getContext(), "Nota eliminada", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                });
        return builder.create();
    }
}