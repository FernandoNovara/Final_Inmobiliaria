package com.example.final_inmobiliaria.ui.Contrato;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.final_inmobiliaria.modelo.Inmueble;
import com.example.final_inmobiliaria.request.ApiClient;

import java.util.ArrayList;

public class ContratoViewModel extends AndroidViewModel
{
    private MutableLiveData<ArrayList<Inmueble>> InmuebleMutable;

    public ContratoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Inmueble>> getInmuebleMutable()
    {
        if (InmuebleMutable == null)
        {
            InmuebleMutable = new MutableLiveData<>();
        }
        return InmuebleMutable;
    }

    public void cargarInmuebles()
    {
        ApiClient api = ApiClient.getApi();
        InmuebleMutable.setValue(api.obtenerPropiedadesAlquiladas());
    }
}