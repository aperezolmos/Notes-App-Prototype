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

import com.google.android.material.navigation.NavigationView;

public class PapeleraActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papelera);

        // Barra de herramientas superior
        Toolbar toolbar = findViewById(R.id.toolbar_papelera);
        setSupportActionBar(toolbar);

        // Vista de papelera llena por defecto
        getSupportFragmentManager().beginTransaction().replace(R.id.pantalla_papelera, new PapeleraLlenaFragment()).commit();

        // Menú barra herramientas - Izquierda
        drawerLayout = findViewById(R.id.drawer_layout_papelera);
        NavigationView navigationView = findViewById(R.id.nav_view_papelera);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,
                R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            recreate();
            navigationView.setCheckedItem(R.id.nav_papelera);
        }
    }

    // Menú barra heramientas - Izquierda
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_todo:
                redirectActivity(PapeleraActivity.this,MainActivity.class);
                break;
            case R.id.nav_etiquetas:
                redirectActivity(PapeleraActivity.this,EtiquetasActivity.class);
                break;
            case R.id.nav_compartidas:
                redirectActivity(PapeleraActivity.this,CompartidasActivity.class);
                break;
            case R.id.nav_papelera:
                recreate();
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
        redirectActivity(PapeleraActivity.this,AjustesActivity.class);
    }

    // Menú barra herramientas - Derecha
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_papelera_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.papelera_restaurar){
            PapeleraRestaurarDialog dialogo = new PapeleraRestaurarDialog();
            dialogo.show(getSupportFragmentManager(),"restaurar notas");
        }
        if (id == R.id.papelera_vaciar){
            PapeleraBorrarDialog dialogo = new PapeleraBorrarDialog();
            dialogo.show(getSupportFragmentManager(),"vaciar papelera");
        }
        return true;
    }

}