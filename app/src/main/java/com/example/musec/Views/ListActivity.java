package com.example.musec.Views;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;

import com.example.musec.Interfaces.ListInterface;
import com.example.musec.Models.InstrumentEntity;
import com.example.musec.Presenters.ListPresenter;
import com.example.musec.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class ListActivity extends AppCompatActivity implements ListInterface.View {
    String TAG = "Musec/ListActivity";
    private ArrayList<InstrumentEntity> items;
    private ListInterface.Presenter presenter;
    InstrumentAdapter recyclerAdapter;
    RecyclerView recyclerView;
    TextView size;
    // Crea una lista con los elementos a mostrar


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter=new ListPresenter(this);
         items=new ArrayList<InstrumentEntity>();
        items=presenter.getAllSummarize();
        Log.d("prueba", ""+presenter.getAllSummarize());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickFloatingButton();

            }
        });




        // Inicializa el RecyclerView
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Crea el Adaptador con los datos de la lista anterior
        recyclerAdapter = new InstrumentAdapter(items);

        // Asocia el elemento de la lista con una acción al ser pulsado
        recyclerAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al pulsar el elemento
                int position = recyclerView.getChildAdapterPosition(v);
               /* Toast.makeText(ListActivity.this, "Posición: " + String.valueOf(position) + " Id: " + items.get(position).getName() + " Nombre: " + items.get(position).getDescription(), Toast.LENGTH_SHORT)
                        .show();*/
                presenter.onClickRecyclerViewItem(items.get(position).getId());

            }
        });

        // Asocia el Adaptador al RecyclerView
        recyclerView.setAdapter(recyclerAdapter);

        // Muestra el RecyclerView en vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    InstrumentEntity deletedInstrument = null;

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {

            final int position = viewHolder.getAdapterPosition();

            switch (direction) {
                case ItemTouchHelper.LEFT:
                    deletedInstrument = items.get(position);


                    if(items.remove(position)==deletedInstrument){
                        recyclerAdapter.notifyItemRemoved(position);
                        size=(TextView) findViewById(R.id.textView);
                        size.setText(items.size()+" Resultados");
                        Toast.makeText(getApplicationContext(),presenter.getError(1),Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),presenter.getError(2),Toast.LENGTH_LONG).show();
                    }

                    break;

            }
        }
        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

            new RecyclerViewSwipeDecorator.Builder(ListActivity.this, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(ListActivity.this, R.color.colorAccent))
                    .addSwipeLeftActionIcon(R.drawable.ic_delete_black_24dp)
                    .setActionIconTint(ContextCompat.getColor(recyclerView.getContext(), android.R.color.white))
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_search) {
            presenter.onClickSearchButton();
            return true;
        }
        if (id == R.id.action_about) {
            presenter.onClickAboutButton();
            return true;
        }
        if (id == R.id.action_help) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void StartFormActivity() {
        Intent intent = new Intent(getApplicationContext(), FormActivity.class);
        startActivity(intent);

    }

    @Override
    public void StartAboutActivity() {
        Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(intent);

    }

    @Override
    public void StartSearchActivity() {
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);

    }

    @Override
    public void StartFormActivity(String id) {
        Intent intent = new Intent(getApplicationContext(), FormActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        Log.d(TAG, "Starting onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "Starting onResume");
        super.onResume();
        items.clear();
        items.addAll(presenter.getAllSummarize());
        Log.d("prueba", ""+presenter.getAllSummarize());
        size=(TextView) findViewById(R.id.textView);
        size.setText(items.size()+" Resultados");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "Starting onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Starting onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "Starting onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "Starting onDestroy");
        super.onDestroy();
    }
}