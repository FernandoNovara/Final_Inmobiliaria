package com.example.final_inmobiliaria.ui.Inquilino;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.final_inmobiliaria.modelo.Inmueble;
import com.example.final_inmobiliaria.request.ApiClient;

import java.util.ArrayList;

public class InquilinoViewModel extends AndroidViewModel
{
    private MutableLiveData<ArrayList<Inmueble>> inmuebleMutable;
    private Context context;

    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        this.context = getApplication();
    }


    public LiveData<ArrayList<Inmueble>> getInmuebleMutable()
    {
        if(inmuebleMutable == null)
        {
            inmuebleMutable = new MutableLiveData<>();
        }
        return inmuebleMutable;
    }

    public void cargarInmuebles()
    {
        ApiClient api = ApiClient.getApi();
        this.inmuebleMutable.setValue(api.obtenerPropiedadesAlquiladas());
    }
}