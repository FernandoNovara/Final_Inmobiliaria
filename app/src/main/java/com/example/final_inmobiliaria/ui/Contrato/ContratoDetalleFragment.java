package com.example.final_inmobiliaria.ui.Contrato;

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
import com.example.final_inmobiliaria.modelo.Contrato;

public class ContratoDetalleFragment extends Fragment {

    private ContratoDetalleViewModel contratoDetalleViewModel;
    private EditText etCodigoContrato,etFechaInicioContrato,etFechaFinalContrato,etMontoContrato,etInquilinoContrato,etInmuebleContrato;
    private Button btnPagos;

    public static ContratoDetalleFragment newInstance() {
        return new ContratoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contrato_detalle, container, false);
        Inicializar(view);
        return view;
    }

    private void Inicializar(View view)
    {
        etCodigoContrato = view.findViewById(R.id.etCodigoContrato);
        etFechaInicioContrato = view.findViewById(R.id.etFechaInicioContrato);
        etFechaFinalContrato = view.findViewById(R.id.etFechaFinalContrato);
        etMontoContrato = view.findViewById(R.id.etMontoContrato);
        etInquilinoContrato = view.findViewById(R.id.etInquilinoContrato);
        etInmuebleContrato = view.findViewById(R.id.etInmuebleContrato);
        btnPagos = view.findViewById(R.id.btnPagosContrato);

        contratoDetalleViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ContratoDetalleViewModel.class);
        contratoDetalleViewModel.getContratoMutable().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                etCodigoContrato.setText(contrato.getIdContrato()+"");
                etFechaInicioContrato.setText(contrato.getFechaInicio());
                etFechaFinalContrato.setText(contrato.getFechaFin());
                etMontoContrato.setText(contrato.getMontoAlquiler()+"");
                etInquilinoContrato.setText(contrato.getInquilino().getNombre());
                etInmuebleContrato.setText("Inmueble en " + contrato.getInmueble().getDireccion());
                btnPagos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("contrato",contrato);
                        Navigation.findNavController( getActivity(),R.id.nav_host_fragment_content_main).navigate(R.id.pagoFragment,bundle);
                    }
                });
            }
        });

        contratoDetalleViewModel.cargarContrato(getArguments());
    }


}