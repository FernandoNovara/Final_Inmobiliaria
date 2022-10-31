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
import com.example.final_inmobiliaria.modelo.Contrato;

import java.util.List;

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.ViewHolder>
{
    private Context context;
    private List<Contrato> contratos;
    private LayoutInflater inflater;

    public ContratoAdapter(Context contexto, List<Contrato> lista, LayoutInflater layout)
    {
        context = contexto;
        contratos = lista;
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
        holder.tvDireccionContrato.setText(contratos.get(position).getInmueble().getDireccion());
        Glide.with(context)
                .load(contratos.get(position).getInmueble().getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivImagenInmuebleContrato);
    }

    @Override
    public int getItemCount() {
        return contratos.size();
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
                    Bundle bundle = new Bundle();
                    Contrato contrato = contratos.get(getAdapterPosition());
                    bundle.putSerializable("contrato",contrato);
                    Navigation.findNavController((Activity) context,R.id.nav_host_fragment_content_main).navigate(R.id.contratoDetalleFragment,bundle);
                }
            });
        }
    }
}
