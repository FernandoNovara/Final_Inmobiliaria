package com.example.final_inmobiliaria.ui.Contrato;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_inmobiliaria.R;
import com.example.final_inmobiliaria.modelo.Inmueble;

import java.util.ArrayList;

public class ContratoFragment extends Fragment {

    private ContratoViewModel contratoViewModel;
    private RecyclerView rvRecyclerContrato;
    private ContratoAdapter contratoAdapter;
    private Context context;

    public static ContratoFragment newInstance() {
        return new ContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contrato, container, false);
        context = view.getContext();
        Inicializar(view);
        return view;
    }

    private void Inicializar(View view)
    {
        rvRecyclerContrato = view.findViewById(R.id.rvRecyclerContrato);
        contratoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ContratoViewModel.class);
        contratoViewModel.getInmuebleMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false);
                rvRecyclerContrato.setLayoutManager(gridLayoutManager);
                contratoAdapter = new ContratoAdapter(context,inmuebles,getLayoutInflater());
                rvRecyclerContrato.setAdapter(contratoAdapter);
            }
        });

        contratoViewModel.cargarInmuebles();
    }
}