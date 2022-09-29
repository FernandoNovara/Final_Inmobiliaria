package com.example.final_inmobiliaria.ui.Pago;

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
import com.example.final_inmobiliaria.modelo.Pago;

import java.util.ArrayList;

public class PagoFragment extends Fragment {

    private PagoViewModel pagoViewModel;
    private RecyclerView rvPagos;
    private PagoAdapter pagoAdapter;
    private Context context;

    public static PagoFragment newInstance() {
        return new PagoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pago, container, false);
        context = view.getContext();
        Inicializar(view);
        return view;
    }

    private void Inicializar(View view)
    {
        rvPagos = view.findViewById(R.id.rvPagos);
        pagoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PagoViewModel.class);
        pagoViewModel.getPagoMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Pago>>() {
            @Override
            public void onChanged(ArrayList<Pago> pagos)
            {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false);
                rvPagos.setLayoutManager(gridLayoutManager);
                pagoAdapter = new PagoAdapter(context,pagos,getLayoutInflater());
                rvPagos.setAdapter(pagoAdapter);
            }
        });

        pagoViewModel.cargarPagos(getArguments());

    }


}