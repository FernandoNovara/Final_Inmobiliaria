package com.example.final_inmobiliaria.ui.Inmueble;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.final_inmobiliaria.modelo.Inmueble;

public class DetalleInmuebleViewModel extends AndroidViewModel
{
    private MutableLiveData<Inmueble> InmuebleMutable;

    public DetalleInmuebleViewModel(@NonNull Application application)
    {
        super(application);
    }

    public LiveData<Inmueble> getInmuebleMutable()
    {
        if (InmuebleMutable == null)
        {
            InmuebleMutable = new MutableLiveData<>();
        }
        return InmuebleMutable;
    }

    public void cargarInmueble(Bundle bundle)
    {
        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
        this.InmuebleMutable.setValue(inmueble);
    }

}