package com.example.final_inmobiliaria.ui.Inquilino;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.final_inmobiliaria.R;
import com.example.final_inmobiliaria.modelo.Inquilino;

public class DetalleInquilinoFragment extends Fragment {

    private DetalleInquilinoViewModel detalleInquilinoViewModel;
    private TextView tvCodigoDetalleInquilino,tvNombreDetalleInquilino,tvApellidoDetalleInquilino,tvDniDetalleInquilino,tvEmailDetalleInquilino,tvTelefonoDetalleInquilino;

    public static DetalleInquilinoFragment newInstance() {
        return new DetalleInquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_detalle_inquilino, container, false);
        Inicializar(view);
        return view;
    }

    private void Inicializar(View view)
    {
        tvCodigoDetalleInquilino = view.findViewById(R.id.tvCodigoDetalleInquilino);
        tvNombreDetalleInquilino = view.findViewById(R.id.tvNombreDetalleInquilino);
        tvApellidoDetalleInquilino = view.findViewById(R.id.tvLugarTrabajoDetalleInquilino);
        tvDniDetalleInquilino = view.findViewById(R.id.tvDniDetalleInquilino);
        tvEmailDetalleInquilino = view.findViewById(R.id.tvEmailDetalleInquilino);
        tvTelefonoDetalleInquilino = view.findViewById(R.id.tvTelefonoDetalleInquilino);

        detalleInquilinoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleInquilinoViewModel.class);
        detalleInquilinoViewModel.getInquilinoMutable().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino)
            {
                tvCodigoDetalleInquilino.setText(inquilino.getIdInquilino()+"");
                tvNombreDetalleInquilino.setText(inquilino.getNombre());
                tvApellidoDetalleInquilino.setText(inquilino.getLugarTrabajo());
                tvDniDetalleInquilino.setText(inquilino.getDni()+" ");
                tvEmailDetalleInquilino.setText(inquilino.getEmail());
                tvTelefonoDetalleInquilino.setText(inquilino.getTelefono());
            }
        });

        detalleInquilinoViewModel.cargarInquilino(getArguments());
    }

}