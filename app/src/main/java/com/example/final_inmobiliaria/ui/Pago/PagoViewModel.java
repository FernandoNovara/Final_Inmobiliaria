package com.example.final_inmobiliaria.ui.Pago;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.final_inmobiliaria.modelo.Contrato;
import com.example.final_inmobiliaria.modelo.Pago;
import com.example.final_inmobiliaria.request.ApiClient;

import java.util.ArrayList;

public class PagoViewModel extends AndroidViewModel
{
    private MutableLiveData<ArrayList<Pago>> PagoMutable;

    public PagoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Pago>> getPagoMutable()
    {
        if (PagoMutable == null)
        {
            PagoMutable = new MutableLiveData<>();
        }
        return PagoMutable;
    }

    public void cargarPagos(Bundle bundle)
    {
        ApiClient api = ApiClient.getApi();
        Contrato contrato = (Contrato) bundle.getSerializable("contrato");
        this.PagoMutable.setValue(api.obtenerPagos(contrato));
    }
}