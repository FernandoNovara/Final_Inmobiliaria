package com.example.final_inmobiliaria.ui.Inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.final_inmobiliaria.R;
import com.example.final_inmobiliaria.modelo.Inmueble;

public class DetalleInmuebleFragment extends Fragment {

    private DetalleInmuebleViewModel detalleInmuebleViewModel;
    private TextView tvCodigoDetalle,tvDireccionDetalle,tvUsoDetalle,tvTipoDetalle,tvAmbientesDetalle,tvPrecioDetalle;
    private CheckBox cbEstado;
    private ImageView ivInmuebleDetalle;

    public static DetalleInmuebleFragment newInstance() {
        return new DetalleInmuebleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_detalle_inmueble, container, false);
        Inicializar(view);
        return view;
    }

    private void Inicializar(View view)
    {
        tvCodigoDetalle = view.findViewById(R.id.tvCodigoDetalle);
        tvDireccionDetalle = view.findViewById(R.id.tvDireccionDetalle);
        tvUsoDetalle = view.findViewById(R.id.tvUsoDetalle);
        tvTipoDetalle = view.findViewById(R.id.tvTipoDetalle);
        tvAmbientesDetalle = view.findViewById(R.id.tvAmbientesDetalle);
        tvPrecioDetalle = view.findViewById(R.id.tvPrecioDetalle);
        cbEstado = view.findViewById(R.id.cbEstadoDetalle);
        ivInmuebleDetalle = view.findViewById(R.id.ivInmuebleDetalle);

        detalleInmuebleViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleInmuebleViewModel.class);
        detalleInmuebleViewModel.getInmuebleMutable().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
        @Override
        public void onChanged(Inmueble inmueble)
        {
            tvCodigoDetalle.setText(inmueble.getIdInmueble() + "");
            tvDireccionDetalle.setText(inmueble.getDireccion());
            tvUsoDetalle.setText(inmueble.getUso());
            tvTipoDetalle.setText(inmueble.getTipo());
            tvAmbientesDetalle.setText(inmueble.getAmbientes()+ "");
            tvPrecioDetalle.setText("$" +inmueble.getPrecio());
            cbEstado.setChecked(inmueble.isEstado());
            Glide.with(getContext())
                    .load(inmueble.getImagen())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivInmuebleDetalle);
        }
    });
        detalleInmuebleViewModel.cargarInmueble(getArguments());
    }


}