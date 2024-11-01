package com.example.appnotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

/**
 * En esta pantalla se visualizan las notas que el usuario comparte con otros usuarios.
 * Funcionalidades:
 *  >> A la izquierda está el Navigation Drawer, para cambiar de actividad.
 *  >> Si se pulsa sobre una de las notas, se visualizan más detalles.
 */
public class CompartidasActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartidas);

        // Barra de herramientas superior
        Toolbar toolbar = findViewById(R.id.toolbar_compartidas);
        setSupportActionBar(toolbar);

        RelativeLayout layout = findViewById(R.id.compartidaslayout);
        layout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        // Busca todas las tarjetas en la vista
        final List<CardView> cardViews = new ArrayList<>();
        cardViews.add(findViewById(R.id.compartidasNota1));
        cardViews.add(findViewById(R.id.compartidasNota2));
        cardViews.add(findViewById(R.id.compartidasNota3));

        // Dejar la 3a nota abierta (ejemplo)
        cardViews.get(2).findViewById(R.id.compartidasDetalles).setVisibility(View.VISIBLE);

        // Asigna un clic de escucha a cada tarjeta
        for (final CardView cardView : cardViews) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RelativeLayout detalles = cardView.findViewById(R.id.compartidasDetalles);
                    boolean isVisible = (detalles.getVisibility() == View.VISIBLE);
                    TransitionManager.beginDelayedTransition((ViewGroup) cardView, new Slide());
                    // Tipos: autotransition, slide o fade.
                    detalles.setVisibility(isVisible ? View.GONE : View.VISIBLE);
                }
            });
        }

        // Configuración de cada tarjeta
        for (int i = 0; i < cardViews.size(); i++) {
            CardView cardView = cardViews.get(i);
            TextView titulo = cardView.findViewById(R.id.compartidasTitulo);
            TextView texto = cardView.findViewById(R.id.compartidasTexto);
            TextView tiempo = cardView.findViewById(R.id.compartidasTiempo);
            TextView usuarios = cardView.findViewById(R.id.compartidasUsuarios);
            TextView creacion = cardView.findViewById(R.id.compartidasCreacion);

            // Configura el texto de cada tarjeta
            titulo.setText("Título de la nota " + (i+1));
            texto.setText(R.string.loremIpsumCorto);
            tiempo.setText("hace " + (i+2)*3 + " días");
            usuarios.setText("Usuario 1, Usuario 2");
            creacion.setText( ((i+1)*4) + " abril 202" + i );
        }

        // Menú barra herramientas - Izquierda
        drawerLayout = findViewById(R.id.drawer_layout_compartidas);
        NavigationView navigationView = findViewById(R.id.nav_view_compartidas);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            recreate();
            navigationView.setCheckedItem(R.id.nav_compartidas);
        }
    }

    // Menú barra heramientas - Izquierda
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_todo:
                redirectActivity(CompartidasActivity.this,MainActivity.class);
                break;
            case R.id.nav_etiquetas:
                redirectActivity(CompartidasActivity.this,EtiquetasActivity.class);
                break;
            case R.id.nav_compartidas:
                recreate();
                break;
            case R.id.nav_papelera:
                redirectActivity(CompartidasActivity.this,PapeleraActivity.class);
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
        redirectActivity(CompartidasActivity.this,AjustesActivity.class);
    }
}