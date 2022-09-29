package com.example.final_inmobiliaria.ui.Contrato;

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
import com.example.final_inmobiliaria.request.ApiClient;

import java.util.List;

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.ViewHolder>
{
    private Context context;
    private List<Inmueble> inmuebles;
    private LayoutInflater inflater;

    public ContratoAdapter(Context contexto, List<Inmueble> lista, LayoutInflater layout)
    {
        context = contexto;
        inmuebles = lista;
        inflater = layout;
    }


    @NonNull
    @Override
    public ContratoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.items_contrato,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContratoAdapter.ViewHolder holder, int position)
    {
        holder.tvDireccionContrato.setText(inmuebles.get(position).getDireccion());
        Glide.with(context)
                .load(inmuebles.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivImagenInmuebleContrato);
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
        TextView tvDireccionContrato;
        ImageView ivImagenInmuebleContrato;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDireccionContrato = itemView.findViewById(R.id.tvDireccionContrato);
            ivImagenInmuebleContrato = itemView.findViewById(R.id.ivImagenInmuebleContrato);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ApiClient api = ApiClient.getApi();
                    Bundle bundle = new Bundle();
                    Inmueble inmueble = inmuebles.get(getAdapterPosition());
                    bundle.putSerializable("inmueble",inmueble);
                    Navigation.findNavController((Activity) context,R.id.nav_host_fragment_content_main).navigate(R.id.contratoDetalleFragment,bundle);
                }
            });
        }
    }
}
