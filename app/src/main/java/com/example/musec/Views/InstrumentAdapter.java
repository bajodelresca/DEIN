package com.example.musec.Views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musec.Models.InstrumentEntity;
import com.example.musec.R;

import java.util.ArrayList;

public class InstrumentAdapter extends RecyclerView.Adapter<InstrumentAdapter.InstrumentViewHolder> implements View.OnClickListener  {
    private ArrayList<InstrumentEntity> items;
    private View.OnClickListener listener;

    // Clase interna:
    // Se implementa el ViewHolder que se encargará
    // de almacenar la vista del elemento y sus datos
    public static class InstrumentViewHolder
            extends RecyclerView.ViewHolder {
        private ImageView ImageView_profile;
        private TextView TextView_name;
        private TextView TextView_description;

        public InstrumentViewHolder(View itemView) {
            super(itemView);
            ImageView_profile=(ImageView) itemView.findViewById(R.id.ImageViewid);
            TextView_name = (TextView) itemView.findViewById(R.id.TextView_name);
            TextView_description = (TextView) itemView.findViewById(R.id.TextView_description);
        }

        public void InstrumentBind(InstrumentEntity item) {
            byte[] decodedString = Base64.decode(item.getImage(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            ImageView_profile.setImageBitmap(decodedByte);
            TextView_name.setText(item.getName());
            TextView_description.setText(item.getDescription());
        }
    }

    // Contruye el objeto adaptador recibiendo la lista de datos
    public InstrumentAdapter(@NonNull ArrayList<InstrumentEntity> items) {
        this.items = items;
    }

    // Se encarga de crear los nuevos objetos ViewHolder necesarios
    // para los elementos de la colección.
    // Infla la vista del layout, crea y devuelve el objeto ViewHolder
    @Override
    public InstrumentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.instrumentos_row, parent, false);
        row.setOnClickListener(this);

        InstrumentViewHolder avh = new InstrumentViewHolder(row);
        return avh;
    }

    // Se encarga de actualizar los datos de un ViewHolder ya existente.
    @Override
    public void onBindViewHolder(InstrumentViewHolder viewHolder, int position) {
        InstrumentEntity item = items.get(position);
        viewHolder.InstrumentBind(item);
    }

    // Indica el número de elementos de la colección de datos.
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Asigna un listener al elemento
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }
}
