package com.example.final_inmobiliaria.ui.Inmueble;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.final_inmobiliaria.R;
import com.example.final_inmobiliaria.modelo.Inmueble;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class AgregarInmuebleFragment extends Fragment {

    private AgregarInmuebleViewModel AgregarViewModel;
    private EditText etDireccionAgregar,etAmbientesAgregar,etPrecioAgregar;
    private Spinner spTipoAgregar,spUsoAgregar;
    private CheckBox cbEstadoAgregar;
    private Button btnAgregar;
    private ImageView ivInmuebleAgregar;
    private Inmueble inmueble;
    private String encoded;
    private int REQUEST_IMAGE_CAPTURE = 1;

    public static AgregarInmuebleFragment newInstance() {
        return new AgregarInmuebleFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        AgregarViewModel = new ViewModelProvider(this).get(AgregarInmuebleViewModel.class);
        View view = inflater.inflate(R.layout.fragment_agregar_inmueble, container, false);

        AgregarViewModel.getFotoMutable().observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                ivInmuebleAgregar.setImageBitmap(bitmap);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                byte[] b = baos.toByteArray();

                encoded = Base64.getEncoder().encodeToString(b);
            }
        });

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
        ivInmuebleAgregar = view.findViewById(R.id.ivInmuebleAgregar);

        String[] opcionesUso = {"Residencial","Comercial"};
        String[] opcionesTipo = {"Local","Deposito","Casa","Departamento"};
        CargarSpinner(spUsoAgregar,opcionesUso);
        CargarSpinner(spTipoAgregar,opcionesTipo);

        ivInmuebleAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if (ActivityCompat.checkSelfPermission( getActivity().getApplicationContext(), Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED)
                    {
                        AbrirCamara();
                    }
                    else
                    {
                        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},REQUEST_IMAGE_CAPTURE);
                    }
                }
                else
                {
                    AbrirCamara();
                }
            }
        });


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inmueble.setDireccion(etDireccionAgregar.getText().toString());
                inmueble.setUso(spUsoAgregar.getSelectedItem().toString());
                inmueble.setTipo(spTipoAgregar.getSelectedItem().toString());
                inmueble.setAmbientes(Integer.parseInt(etAmbientesAgregar.getText().toString()));
                inmueble.setPrecio(Double.parseDouble(etPrecioAgregar.getText().toString()));
                inmueble.setImagen(encoded);
                if (cbEstadoAgregar.isChecked()) {
                    inmueble.setEstado(true);
                }

                AgregarViewModel.AgregarInmueble(inmueble);
            }
        });


    }

    public void CargarSpinner (Spinner spinner, String[] opciones)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, opciones);
        spinner.setAdapter(adapter);
    }

    public void AbrirCamara() {
        Intent tomarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (tomarFoto.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(tomarFoto, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        AgregarInmuebleFragment.super.onActivityResult(requestCode,resultCode,data);
        AgregarViewModel.respuetaDeCamara(requestCode,resultCode,data,REQUEST_IMAGE_CAPTURE);
    }



    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_IMAGE_CAPTURE)
        {
            if(permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                AbrirCamara();
            }
            else
            {
                Toast.makeText(this.getContext(),"Se nesesita permiso",Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }*/

}