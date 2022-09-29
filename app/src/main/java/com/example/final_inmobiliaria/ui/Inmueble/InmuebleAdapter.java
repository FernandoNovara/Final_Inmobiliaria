package com.example.final_inmobiliaria.ui.Inmueble;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.final_inmobiliaria.R;
import com.example.final_inmobiliaria.modelo.Inmueble;

import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.ViewHolder> {

    private Context context;
    private List<Inmueble> inmuebles;
    private LayoutInflater inflater;

    public InmuebleAdapter( Context contexto, List<Inmueble> lista, LayoutInflater layout)
    {
        context = contexto;
        inmuebles = lista;
        inflater = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.items_inmuebles,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvDireccion.setText(inmuebles.get(position).getDireccion());
        holder.tvPrecio.setText( "$ " + inmuebles.get(position).getPrecio());
        Glide.with(context)
                .load(inmuebles.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivImagenInmueble);
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    /*
    ----------------------------------
    Clase ViewHolder interna
    ----------------------------------
     */

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvPrecio,tvDireccion;
        ImageView ivImagenInmueble;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImagenInmueble = itemView.findViewById(R.id.ivImagenInmueble);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    Inmueble inmueble = inmuebles.get(getAdapterPosition());
                    bundle.putSerializable("inmueble",inmueble);
                    Navigation.findNavController((Activity) context,R.id.nav_host_fragment_content_main).navigate(R.id.detalleInmuebleFragment,bundle);
                }
            });

        }
    }
}



