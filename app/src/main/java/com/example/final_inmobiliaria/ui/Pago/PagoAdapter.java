package com.example.final_inmobiliaria.ui.Pago;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_inmobiliaria.R;
import com.example.final_inmobiliaria.modelo.Pago;

import java.util.List;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.ViewHolder>
{
    private Context context;
    private List<Pago> pagos;
    private LayoutInflater inflater;

    public PagoAdapter(Context contexto, List<Pago> lista, LayoutInflater layout)
    {
        context = contexto;
        pagos = lista;
        inflater = layout;
    }

    @NonNull
    @Override
    public PagoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.items_pagos,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCodigoPago.setText("Codigo pago: "+pagos.get(position).getIdPago());
        holder.tvCodigoContratoPago.setText("Codigo de Contrato: "+pagos.get(position).getContrato().getIdContrato());
        holder.tvImportePago.setText("Importe: "+pagos.get(position).getImporte());
        holder.tvFechaPago.setText("Fecha de pago: "+pagos.get(position).getFechaDePago());
    }

    @Override
    public int getItemCount() {
        return pagos.size();
    }


        /*
    ----------------------------------
    Clase ViewHolder interna
    ----------------------------------
    */

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tvCodigoPago,tvNumeroPago,tvCodigoContratoPago,tvImportePago,tvFechaPago;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            tvCodigoPago = itemView.findViewById(R.id.tvCodigoPago);
            tvCodigoContratoPago = itemView.findViewById(R.id.tvCodigoContratoPago);
            tvImportePago = itemView.findViewById(R.id.tvImportePago);
            tvFechaPago = itemView.findViewById(R.id.tvFechaPago);
        }
    }
}
