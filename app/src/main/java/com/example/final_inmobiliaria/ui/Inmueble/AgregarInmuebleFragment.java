package com.example.final_inmobiliaria.ui.Inmueble;

import static android.Manifest.permission.CAMERA;
import static androidx.core.content.PermissionChecker.checkSelfPermission;

import android.Manifest;
import android.content.Intent;
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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.final_inmobiliaria.R;
import com.example.final_inmobiliaria.modelo.Inmueble;
import com.google.android.gms.maps.model.LatLng;

import java.io.ByteArrayOutputStream;
import java.util.Base64;


public class AgregarInmuebleFragment extends Fragment {

    private AgregarInmuebleViewModel AgregarViewModel;
    private TextView tvUbicacionAgregar;
    private EditText etDireccionAgregar,etAmbientesAgregar,etPrecioAgregar;
    private Spinner spTipoAgregar,spUsoAgregar;
    private CheckBox cbEstadoAgregar;
    private Button btnAgregar,btnMarcarUbicacion;
    private ImageView ivInmuebleAgregar;
    private LatLng ubicacion;
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
                byte [] b = baos.toByteArray();

                encoded = Base64.getEncoder().encodeToString(b);
            }
        });

        AgregarViewModel.getLatLngMutable().observe(getViewLifecycleOwner(), new Observer<LatLng>() {
            @Override
            public void onChanged(LatLng latLng) {
                String dato = "Lat/Log: "+latLng.latitude + "," + latLng.longitude;
                tvUbicacionAgregar.setText(dato);
                ubicacion = latLng;
            }
        });

        Inicializar(view);
        return view;
    }

    private void Inicializar(View view)
    {

        etDireccionAgregar = view.findViewById(R.id.etDireccionAgregar);
        etAmbientesAgregar = view.findViewById(R.id.etAmbientesAgregar);
        etPrecioAgregar = view.findViewById(R.id.etPrecioAgregar);
        spUsoAgregar = view.findViewById(R.id.spUsoAgregar);
        spTipoAgregar = view.findViewById(R.id.spTipoAgregar);
        cbEstadoAgregar = view.findViewById(R.id.cbEstadoAgregar);
        btnAgregar = view.findViewById(R.id.btnAgregar);
        ivInmuebleAgregar = view.findViewById(R.id.ivInmuebleAgregar);
        tvUbicacionAgregar = view.findViewById(R.id.tvUbicacionAgregar);
        btnMarcarUbicacion = view.findViewById(R.id.btnMarcarUbicacion);

        String[] opcionesUso = {"Residencial","Comercial"};
        String[] opcionesTipo = {"Local","Deposito","Casa","Departamento"};
        CargarSpinner(spUsoAgregar,opcionesUso);
        CargarSpinner(spTipoAgregar,opcionesTipo);

        ivInmuebleAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(getContext(),CAMERA) != PermissionChecker.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},1000);
                }
                else
                {
                    AbrirCamara(view);
                }
            }
        });

        btnMarcarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarViewModel.CargarUbicacion();
            }
        });


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Inmueble inmueble = new Inmueble();
                inmueble.setDireccion(etDireccionAgregar.getText().toString());
                inmueble.setUso(spUsoAgregar.getSelectedItem().toString());
                inmueble.setTipo(spTipoAgregar.getSelectedItem().toString());
                inmueble.setAmbientes(Integer.parseInt(etAmbientesAgregar.getText().toString()));
                inmueble.setPrecio(Double.parseDouble(etPrecioAgregar.getText().toString()));
                inmueble.setImagen(encoded);
                inmueble.setLatitud(ubicacion.latitude + "");
                inmueble.setLongitud(ubicacion.longitude + "");
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

    public void AbrirCamara(View view) {
        Intent tomarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (tomarFoto.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(tomarFoto, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        AgregarViewModel.respuetaDeCamara(requestCode,resultCode,data,REQUEST_IMAGE_CAPTURE);

    }

}