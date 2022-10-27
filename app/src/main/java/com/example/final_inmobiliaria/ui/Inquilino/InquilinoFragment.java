package com.example.final_inmobiliaria.ui.Inquilino;

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
import com.example.final_inmobiliaria.modelo.Contrato;

import java.util.ArrayList;

public class InquilinoFragment extends Fragment {

    private InquilinoViewModel inquilinoViewModel;
    private RecyclerView rvInquilino;
    private InquilinoAdapter inquilinoAdapter;
    private Context context;

    public static InquilinoFragment newInstance()
    {
        return new InquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inquilino, container, false);
        context = view.getContext();
        Inicializar(view);
        return view;
    }

    private void Inicializar(View view)
    {
        rvInquilino = view.findViewById(R.id.rvRecyclerInquilino);
        inquilinoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InquilinoViewModel.class);
        inquilinoViewModel.getContratoMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Contrato>>() {
            @Override
            public void onChanged(ArrayList<Contrato> contratos) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false);
                rvInquilino.setLayoutManager(gridLayoutManager);
                inquilinoAdapter = new InquilinoAdapter(context,contratos,getLayoutInflater());
                rvInquilino.setAdapter(inquilinoAdapter);
            }
        });

        inquilinoViewModel.cargarInmuebles();
    }

}