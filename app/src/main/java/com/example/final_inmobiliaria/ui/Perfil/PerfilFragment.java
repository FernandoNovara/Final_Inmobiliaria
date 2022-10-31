package com.example.final_inmobiliaria.ui.Perfil;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.final_inmobiliaria.R;
import com.example.final_inmobiliaria.modelo.Propietario;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private EditText etDni,etNombre,etApellido,etEmail,etContraseña,etTelefono;
    private Button btnEditar,btnCambiar;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        perfilViewModel.getPropietarioMutable().observe(getViewLifecycleOwner(),new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                etDni.setText(propietario.getDni()+"");
                etNombre.setText(propietario.getNombre());
                etApellido.setText(propietario.getApellido());
                etEmail.setText(propietario.getEmail());
                etTelefono.setText(propietario.getTelefono());
            }
        });

        perfilViewModel.getNombreBotonMutable().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                btnEditar.setText(s);
            }
        });

        perfilViewModel.getEditarMutable().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                etDni.setEnabled(aBoolean);
                etNombre.setEnabled(aBoolean);
                etApellido.setEnabled(aBoolean);
                etEmail.setEnabled(aBoolean);
                etTelefono.setEnabled(aBoolean);
            }
        });

        Inicializar(view);

        return view;
    }

    private void Inicializar(View view)
    {
        etDni = view.findViewById(R.id.etDni);
        etNombre = view.findViewById(R.id.etNombre);
        etApellido = view.findViewById(R.id.etApellido);
        etEmail = view.findViewById(R.id.etEmail);
        etTelefono = view.findViewById(R.id.etTelefono);
        btnEditar = view.findViewById(R.id.btnEditar);
        btnCambiar = view.findViewById(R.id.btnCambiar);

        perfilViewModel.mostrarDatos();



        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Propietario p = new Propietario();
                p.setDni(etDni.getText().toString());
                p.setApellido(etApellido.getText().toString());
                p.setNombre(etNombre.getText().toString());
                p.setEmail(etEmail.getText().toString());
                p.setContraseña("");
                p.setTelefono(etTelefono.getText().toString());

                perfilViewModel.accionar(btnEditar.getText().toString(),p);
            }
        });


        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController((Activity) getContext(),R.id.nav_host_fragment_content_main).navigate(R.id.resetearPassFragment);
            }
        });



    }


}