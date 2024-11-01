package com.example.appnotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.appnotas.databinding.ActivityNotaBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class NotaActivity extends AppCompatActivity {

    ActivityNotaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        // Barra de herramientas superior. Contiene el título de la nota
        Toolbar toolbar = findViewById(R.id.toolbar_nota);
        setSupportActionBar(toolbar);
        toolbar.setTitle(intent.getStringExtra("titulo"));

        // Volver atrás si se pulsa la flecha de la barra de herramientas
        toolbar.setNavigationOnClickListener(v -> {
            Intent intent1 = new Intent(NotaActivity.this, MainActivity.class);
            startActivity(intent1);
            Toast.makeText(this, "Nota guardada", Toast.LENGTH_SHORT).show();
        });

        // Mostrar en qué modo de la nota se está (escribir, dibujar, ...)
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_menu_escribir:
                    Toast.makeText(NotaActivity.this, "Escribir", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.bottom_menu_dibujar:
                    Toast.makeText(NotaActivity.this, "Dibujar", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.bottom_menu_imagen:
                    Toast.makeText(NotaActivity.this, "Insertar imagen", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.bottom_menu_lista:
                    Toast.makeText(NotaActivity.this, "Insertar listado", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        });

        // Colocar contenido en la nota
        if (intent != null){
            String titulo = intent.getStringExtra("titulo");
            String tiempo = intent.getStringExtra("tiempo");
            int texto = intent.getIntExtra("texto", R.string.loremIpsumLargo);

            if (titulo == null || tiempo == null){
                toolbar.setTitle("Nueva nota");
                binding.notaTitulo.setText("Escriba el título de la nota");
                binding.notaTiempo.setText("ahora");
                binding.notaTexto.setText("Escriba el texto de la nota...");
            }
            else {
                binding.notaTitulo.setText(titulo);
                binding.notaTiempo.setText(tiempo);
                binding.notaTexto.setText(texto);
            }
        }
    }

    // Pulsar hacia atrás
    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(NotaActivity.this, MainActivity.class);
        startActivity(intent1);
        Toast.makeText(this, "Nota guardada", Toast.LENGTH_SHORT).show();
    }

    // Menú barra herramientas - Derecha
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_nota_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.toolbar_nota_compartir){
            Toast.makeText(this, "Seleccionado: Compartir", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.toolbar_nota_privada){
            Toast.makeText(this, "Seleccionado: Hacer privada", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.toolbar_nota_etiquetas){
            Toast.makeText(this, "Seleccionado: Añadir etiquetas", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.toolbar_nota_recordatorios){
            Toast.makeText(this, "Seleccionado: Añadir recordatorio", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.toolbar_nota_anclar){
            Toast.makeText(this, "Seleccionado: Anclar a inicio", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.toolbar_nota_eliminar){
            NotaBorrarDialog notaBorrarDialog = new NotaBorrarDialog();
            notaBorrarDialog.show(getSupportFragmentManager(),"eliminar nota");
        }
        return true;
    }
}