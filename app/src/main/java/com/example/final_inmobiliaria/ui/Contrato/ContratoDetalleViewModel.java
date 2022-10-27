package com.example.final_inmobiliaria.ui.Contrato;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.final_inmobiliaria.modelo.Contrato;

public class ContratoDetalleViewModel extends AndroidViewModel
{
    private MutableLiveData<Contrato> contratoMutable;

    public ContratoDetalleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Contrato> getContratoMutable()
    {
        if (contratoMutable == null)
        {
            contratoMutable = new MutableLiveData<>();
        }
        return contratoMutable;
    }

    public void cargarContrato(Bundle bundle)
    {
        Contrato contrato = (Contrato) bundle.get("contrato");
        contratoMutable.setValue(contrato);
    }
}