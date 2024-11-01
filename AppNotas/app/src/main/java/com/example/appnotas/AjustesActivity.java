package com.example.appnotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class AjustesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        // Barra de herramientas superior
        Toolbar toolbar = findViewById(R.id.toolbar_ajustes);
        setSupportActionBar(toolbar);

        // Volver atrás si se pulsa la flecha de la barra de herramientas
        toolbar.setNavigationOnClickListener(v -> {
            Intent intent1 = new Intent(AjustesActivity.this, MainActivity.class);
            startActivity(intent1);
        });

        // Switches
        SwitchCompat switch1 = findViewById(R.id.switch_ajustes_1);
        switch1.setOnClickListener(view -> {
            if (switch1.isChecked()){
                Toast.makeText(AjustesActivity.this, "Sincronizando con la nube", Toast.LENGTH_SHORT).show();
            }
        });

        SwitchCompat switch2 = findViewById(R.id.switch_ajustes_2);
        switch2.setChecked(true);
        switch2.setOnClickListener(view -> {
            if (switch2.isChecked()){
                Toast.makeText(AjustesActivity.this, "Las notas se guardarán automáticamente", Toast.LENGTH_SHORT).show();
            }
        });
    }
}