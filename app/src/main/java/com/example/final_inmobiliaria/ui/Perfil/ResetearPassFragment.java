package com.example.final_inmobiliaria.ui.Perfil;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.final_inmobiliaria.R;

public class ResetearPassFragment extends Fragment {

    private ResetearPassViewModel resetearViewModel;
    private EditText edContraseñaNueva,edConfirmarContraseña;
    private Button btnCambiarContraseña;


    public static ResetearPassFragment newInstance() {
        return new ResetearPassFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        resetearViewModel = new ViewModelProvider(this).get(ResetearPassViewModel.class);
        View view = inflater.inflate(R.layout.fragment_resetear_pass, container, false);
        Inicializar(view);
        return view;

    }

    private void Inicializar(View view)
    {
        edContraseñaNueva = view.findViewById(R.id.edContraseñaNueva);
        edConfirmarContraseña = view.findViewById(R.id.edConfirmarContraseña);
        btnCambiarContraseña = view.findViewById(R.id.btnCambiarContraseña);

        btnCambiarContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edContraseñaNueva.getText().toString().equals(edConfirmarContraseña.getText().toString()))
                {
                    resetearViewModel.CambiarContraseña(edContraseñaNueva.getText().toString());
                    Navigation.findNavController(((Activity) getContext()), R.id.nav_host_fragment_content_main).navigate(R.id.perfilFragment);
                }
                else
                {
                    Toast.makeText(getContext(),"Las contraseñas no coinciden",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}