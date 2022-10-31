package com.example.final_inmobiliaria.ui.Inicio;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static androidx.core.content.PermissionChecker.checkSelfPermission;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.final_inmobiliaria.R;
import com.example.final_inmobiliaria.databinding.FragmentInicioBinding;
import com.google.android.gms.maps.SupportMapFragment;

public class InicioFragment extends Fragment {

    private FragmentInicioBinding binding;
    private InicioViewModel inicioViewModel;

    public static InicioFragment newInstance() {
        return new InicioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState)
    {
        inicioViewModel = new ViewModelProvider(this).get(InicioViewModel.class);
        binding = FragmentInicioBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        validarPermisos();



        inicioViewModel.getLeerMapaMutable().observe(getViewLifecycleOwner(), new Observer<LeerMapa>() {
            @Override
            public void onChanged(LeerMapa leerMapa) {

                SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(leerMapa);
            }
        });

            inicioViewModel.obtenerMapa();
        return view;
    }

    public void validarPermisos()
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (checkSelfPermission(getContext(),ACCESS_FINE_LOCATION) != PermissionChecker.PERMISSION_GRANTED )
            {
                requestPermissions(new String[]{ACCESS_FINE_LOCATION},1000);
            }
            if (checkSelfPermission(getContext(),ACCESS_COARSE_LOCATION) != PermissionChecker.PERMISSION_GRANTED )
            {
                requestPermissions(new String[]{ACCESS_COARSE_LOCATION},1000);
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}