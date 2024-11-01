package com.example.appnotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;  // Menú barra herramientas - Izquierda

    private boolean hayVistaDeLista = true; // Flag para cambiar a vista de lista o de cuadrícula.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Barra de herramientas superior
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        // Por defecto hay vista de lista
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new ListaFragment()).commit();

        // Diálogo - Chip Privadas
        Chip chipPrivadas = findViewById(R.id.chipPrivadas);
        chipPrivadas.setOnClickListener(view -> openPrivadasDialog());

        // Diálogo - Chip Filtrar
        Chip chipFiltrar = findViewById(R.id.chipFiltrar);
        chipFiltrar.setOnClickListener(view -> openFiltrarDialog());

        // Menú barra herramientas - Izquierda
        drawerLayout = findViewById(R.id.drawer_layout_main);
        NavigationView navigationView = findViewById(R.id.nav_view_main);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,
                R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            recreate();
            navigationView.setCheckedItem(R.id.nav_todo);
        }
    }

    // Diálogo - Chip Privadas
    private void openPrivadasDialog(){
        PrivadasDialog privadasDialog = new PrivadasDialog();
        privadasDialog.show(getSupportFragmentManager(), "privadas dialog");
    }

    // Diálogo - Chip Filtrar
    private void openFiltrarDialog(){
        FiltrarDialog filtrarDialog = new FiltrarDialog();
        filtrarDialog.show(getSupportFragmentManager(), "filtrar dialog");
    }

    public void activarFiltroFecha (View v){
        Switch switchFecha = (Switch) v;
        if (switchFecha.isChecked()) {
            Toast.makeText(this, "Filtrar por fecha: Activado", Toast.LENGTH_SHORT).show();
        }
    }

    public void activarFiltroPalabras (View v){
        Switch switchPalabra = (Switch) v;
        if (switchPalabra.isChecked()) {
            Toast.makeText(this, "Filtrar por palabras clave: Activado", Toast.LENGTH_SHORT).show();
        }
    }

    // Pulsar el botón '+' -> abrir nueva nota
    public void abrirNuevaNota (View view){
        redirectActivity(MainActivity.this,NotaActivity.class);
        Toast.makeText(this, "Nueva nota", Toast.LENGTH_SHORT).show();
    }

    // Menú barra heramientas - Izquierda
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_todo:
                recreate();
                break;
            case R.id.nav_etiquetas:
                redirectActivity(MainActivity.this,EtiquetasActivity.class);
                break;
            case R.id.nav_compartidas:
                redirectActivity(MainActivity.this,CompartidasActivity.class);
                break;
            case R.id.nav_papelera:
                redirectActivity(MainActivity.this,PapeleraActivity.class);
                break;
            case R.id.nav_logout:
                confirmarCerrarSesion();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    public static void redirectActivity (Activity activity, Class secondActivity){
        Intent intent = new Intent(activity,secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    // Pulsar 'Cerrar sesión' en el menú izquierdo
    private void confirmarCerrarSesion(){
        CerrarSesionDialog cerrarSesionDialog = new CerrarSesionDialog();
        cerrarSesionDialog.show(getSupportFragmentManager(),"cerrar sesion");
    }

    // Pulsar icono ajustes en el menú izquierdo
    public void abrirAjustes(View view) {
        redirectActivity(MainActivity.this,AjustesActivity.class);
    }

    // Menú barra herramientas - Derecha
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.editar){
            Toast.makeText(this, "Seleccionado: Editar", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.ordenar){
            Toast.makeText(this, "Seleccionado: Ordenar", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.ver){
            pulsarVer();
        }
        return true;
    }

    private void pulsarVer(){

        if (hayVistaDeLista){
            getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new CuadriculaFragment()).commit();
            hayVistaDeLista = false;
            Toast.makeText(this, "Vista de cuadrícula", Toast.LENGTH_SHORT).show();
        }
        else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new ListaFragment()).commit();
            hayVistaDeLista = true;
            Toast.makeText(this, "Vista de lista", Toast.LENGTH_SHORT).show();
        }
    }

}