package com.example.final_inmobiliaria.ui.Inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.final_inmobiliaria.R;
import com.example.final_inmobiliaria.modelo.Inmueble;

public class AgregarInmuebleFragment extends Fragment {

    private AgregarInmuebleViewModel AgregarViewModel;
    private EditText etDireccionAgregar,etAmbientesAgregar,etPrecioAgregar;
    private Spinner spTipoAgregar,spUsoAgregar;
    private CheckBox cbEstadoAgregar;
    private Button btnAgregar;

    public static AgregarInmuebleFragment newInstance() {
        return new AgregarInmuebleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_agregar_inmueble, container, false);
        Inicializar(view);
        return view;
    }

    private void Inicializar(View view)
    {
        AgregarViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(AgregarInmuebleViewModel.class);

        etDireccionAgregar = view.findViewById(R.id.etDireccionAgregar);
        etAmbientesAgregar = view.findViewById(R.id.etAmbientesAgregar);
        etPrecioAgregar = view.findViewById(R.id.etPrecioAgregar);
        spUsoAgregar = view.findViewById(R.id.spUsoAgregar);
        spTipoAgregar = view.findViewById(R.id.spTipoAgregar);
        cbEstadoAgregar = view.findViewById(R.id.cbEstadoAgregar);
        btnAgregar = view.findViewById(R.id.btnAgregar);

        String[] opcionesUso = {"Residencial","Comercial"};
        String[] opcionesTipo = {"Local","Deposito","Casa","Departamento"};
        CargarSpinner(spUsoAgregar,opcionesUso);
        CargarSpinner(spTipoAgregar,opcionesTipo);


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Inmueble inmueble = new Inmueble();
                inmueble.setDireccion(etDireccionAgregar.getText().toString());
                inmueble.setUso(spUsoAgregar.getSelectedItem().toString());
                inmueble.setTipo(spTipoAgregar.getSelectedItem().toString());
                inmueble.setAmbientes(Integer.parseInt(etAmbientesAgregar.getText().toString()));
                inmueble.setPrecio(Double.parseDouble(etPrecioAgregar.getText().toString()));

                if (cbEstadoAgregar.isChecked()) {
                    inmueble.setEstado(true);
                }

                AgregarViewModel.AgregarInmueble(inmueble);
            }
        });
    }

    public void CargarSpinner(Spinner spinner, String[] opciones)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item,opciones);
        spinner.setAdapter(adapter);
    }

}