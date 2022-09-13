package com.example.final_inmobiliaria.ui.Perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.final_inmobiliaria.R;
import com.example.final_inmobiliaria.modelo.Propietario;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private EditText etCodigo,etDni,etNombre,etApellido,etEmail,etContraseña,etTelefono;
    private Button btnEditar;

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
                etCodigo.setText(propietario.getId()+"");
                etDni.setText(propietario.getDni()+"");
                etNombre.setText(propietario.getNombre());
                etApellido.setText(propietario.getApellido());
                etEmail.setText(propietario.getEmail());
                etContraseña.setText(propietario.getContraseña()+"");
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
                etContraseña.setEnabled(aBoolean);
                etTelefono.setEnabled(aBoolean);
            }
        });

        Inicializar(view);

        return view;
    }

    private void Inicializar(View view)
    {
        etCodigo = view.findViewById(R.id.etCodigo);
        etDni = view.findViewById(R.id.etDni);
        etNombre = view.findViewById(R.id.etNombre);
        etApellido = view.findViewById(R.id.etApellido);
        etEmail = view.findViewById(R.id.etEmail);
        etContraseña = view.findViewById(R.id.etContraseñaPerfil );
        etTelefono = view.findViewById(R.id.etTelefono);
        btnEditar = view.findViewById(R.id.btnEditar);

        perfilViewModel.mostrarDatos();



        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Propietario p = new Propietario();
                p.setId(Integer.parseInt(etCodigo.getText().toString()));
                p.setDni(Long.parseLong(etDni.getText().toString()));
                p.setApellido(etApellido.getText().toString());
                p.setNombre(etNombre.getText().toString());
                p.setEmail(etEmail.getText().toString());
                p.setContraseña(etContraseña.getText().toString());
                p.setTelefono(etTelefono.getText().toString());

                perfilViewModel.accionar(btnEditar.getText().toString(),p);
            }
        });



    }


}