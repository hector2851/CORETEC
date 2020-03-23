package com.example.coretec.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coretec.R;
import com.example.coretec.ui.raee.Raee;
import com.example.coretec.ui.reciclar.RaeeAdapter;

import java.util.ArrayList;
import java.util.List;

public class ResiduosActivity extends AppCompatActivity {


    private List<String> residuo;
    private List<Integer> resiImage;
    private EditText numero;
    private Button mas;
    private Button menos;
    private Button agregar;
    Button boton;
    int contador = 0;
    Context context;
    GridView gridView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residuos);

        getSupportActionBar().setTitle("Residuos Reciclables");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        boton=findViewById(R.id.btn_grid);
        boton.setVisibility(View.INVISIBLE);

        gridView2=findViewById(R.id.gridView2);
        gridView2.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (visibleItemCount > totalItemCount) {
                    boton.setVisibility(View.VISIBLE);
                    Toast.makeText(ResiduosActivity.this, "¡LISTAS", Toast.LENGTH_SHORT).show();
                }else {
                   // Toast.makeText(ResiduosActivity.this, "¡ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });

        gridView2 = (GridView) findViewById(R.id.gridView2);
        residuo = new ArrayList<String>();
        residuo.add("Camara Fotografica");
        residuo.add("Cargador USB");
        residuo.add("Camara de Vigilancia");
        residuo.add("Celular");
        residuo.add("Consola de Videojuego");
        residuo.add("Control Remoto");
        residuo.add("CPU");
        residuo.add("Disco Duro");
        residuo.add("Fuente de Poder");
        residuo.add("Hervidor");
        residuo.add("Impresora");
        residuo.add("Juguetes Electronicos");
        residuo.add("Laptop");
        residuo.add("Lavadora");
        residuo.add("Licuadora");
        residuo.add("Microondas");
        residuo.add("Monitor LCD");
        residuo.add("Mouse");
        residuo.add("Plancha");
        residuo.add("Proyector");
        residuo.add("Reproductor DVD");
        residuo.add("Reproductor MP3/MP4");
        residuo.add("Reproductor VHS");
        residuo.add("Router / Modem");
        residuo.add("Scanner");
        residuo.add("Tablet/Ipad");
        residuo.add("Tarjeta Electronica");
        residuo.add("Teclado");
        residuo.add("Telefono");
        residuo.add("Tv / Smart Tv");
        residuo.add("Unidad de CD");
        residuo.add("Ventilador");


        resiImage = new ArrayList<Integer>();
        resiImage.add(R.drawable.ic_camara);
        resiImage.add(R.drawable.ic_cargador);
        resiImage.add(R.drawable.ic_cctv);
        resiImage.add(R.drawable.ic_celular);
        resiImage.add(R.drawable.ic_consola);
        resiImage.add(R.drawable.ic_control);
        resiImage.add(R.drawable.ic_cpu);
        resiImage.add(R.drawable.ic_disco);
        resiImage.add(R.drawable.ic_fuente);
        resiImage.add(R.drawable.ic_hervidor);
        resiImage.add(R.drawable.ic_impresora);
        resiImage.add(R.drawable.ic_juguete);
        resiImage.add(R.drawable.ic_laptop);
        resiImage.add(R.drawable.ic_lavadora);
        resiImage.add(R.drawable.ic_licuadora);
        resiImage.add(R.drawable.ic_microondas);
        resiImage.add(R.drawable.ic_monitor);
        resiImage.add(R.drawable.ic_mouse);
        resiImage.add(R.drawable.ic_plancha);
        resiImage.add(R.drawable.ic_proyector);
        resiImage.add(R.drawable.ic_dvd);
        resiImage.add(R.drawable.ic_mp3);
        resiImage.add(R.drawable.ic_vhs);
        resiImage.add(R.drawable.ic_router);
        resiImage.add(R.drawable.ic_scanner);
        resiImage.add(R.drawable.ic_tablet);
        resiImage.add(R.drawable.ic_tarjeta);
        resiImage.add(R.drawable.ic_teclado);
        resiImage.add(R.drawable.ic_telefono);
        resiImage.add(R.drawable.ic_tv);
        resiImage.add(R.drawable.ic_cd);
        resiImage.add(R.drawable.ic_ventilador);


        RaeeAdapter raeeAdapter = new RaeeAdapter(this, R.layout.raee_item, residuo, resiImage);
        gridView2.setAdapter(raeeAdapter);

        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            showDialog(view, position);
            }
        });

        numero = (EditText) findViewById(R.id.edit_Numero);
        mas = (Button) findViewById(R.id.btn_mas);
        menos = (Button) findViewById(R.id.btn_menos);
        agregar = (Button) findViewById(R.id.btn_agregar);

    }//ONCREATE

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_search, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Busca tu Residuo...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                   RaeeAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    //DIALOGO AL PRESIONAR UN ITEM = RESIDUO
    public  void showDialog(View view, int position){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.residuo_dialogo);
        dialog.getWindow().setBackgroundDrawable((new ColorDrawable(Color.TRANSPARENT)));
        TextView textView = dialog.findViewById(R.id.dialog_residuo);
        ImageView imageView = dialog.findViewById(R.id.dialog_residuoImg);
        final EditText numero = (EditText) dialog.findViewById(R.id.edit_Numero);
        Button mas = (Button) dialog.findViewById(R.id.btn_mas);
        Button menos = (Button) dialog.findViewById(R.id.btn_menos);
        Button agregar = (Button) dialog.findViewById(R.id.btn_agregar);
        imageView.setImageResource(resiImage.get(position));
        textView.setText(residuo.get(position));

        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            contador++;
            numero.setText(Integer.toString(contador));
            }
        });

        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contador--;
                numero.setText(Integer.toString(contador));
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(ResiduosActivity.this, Datos.class);
                //startActivity(intent);
            }
        });
        imageView.setImageResource(resiImage.get(position));
        textView.setText(residuo.get(position));
        dialog.show();
    }
}
