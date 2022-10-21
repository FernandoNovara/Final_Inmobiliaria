package com.example.final_inmobiliaria.ui.Inmueble;

import android.app.Activity;
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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_inmobiliaria.R;
import com.example.final_inmobiliaria.modelo.Inmueble;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class InmueblesFragment extends Fragment {

    private RecyclerView rvRecyclerInmuebles;
    private InmueblesViewModel inmueblesViewModel;
    private InmuebleAdapter inmuebleAdapter;
    private FloatingActionButton fbAgregar;
    private Context context;

    public static InmueblesFragment newInstance() {
        return new InmueblesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_inmueble, container, false);
        context = view.getContext();
        Inicializar(view);
        return view;
    }

    private void Inicializar(View view)
    {
        rvRecyclerInmuebles = view.findViewById(R.id.rvRecyclerInmuebles);
        fbAgregar = view.findViewById(R.id.fbAgregar);
        fbAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController((Activity) context,R.id.nav_host_fragment_content_main).navigate(R.id.agregarInmuebleFragment);
            }
        });

        inmueblesViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InmueblesViewModel.class);
        inmueblesViewModel.getInmuebleMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false);
                rvRecyclerInmuebles.setLayoutManager(gridLayoutManager);
                inmuebleAdapter = new InmuebleAdapter(context,inmuebles,getLayoutInflater());
                rvRecyclerInmuebles.setAdapter(inmuebleAdapter);
            }
        });

        inmueblesViewModel.cargarInmuebles();
    }


}