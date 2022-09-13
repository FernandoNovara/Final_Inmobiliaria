package com.example.final_inmobiliaria.ui.Perfil;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.final_inmobiliaria.modelo.Propietario;
import com.example.final_inmobiliaria.request.ApiClient;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Propietario> propietarioMutable;
    private MutableLiveData<String> nombreBotonMutable;
    private MutableLiveData<Boolean> editarMutable;
    private Context context;
    private ApiClient api;


    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        api = ApiClient.getApi();
    }

    public LiveData<Propietario> getPropietarioMutable()
    {
        if (propietarioMutable == null)
        {
            propietarioMutable = new MutableLiveData<>();
        }
        return propietarioMutable;
    }

    public LiveData<String> getNombreBotonMutable()
    {
        if (nombreBotonMutable == null)
        {
            nombreBotonMutable = new MutableLiveData<>();
        }
        return nombreBotonMutable;
    }

    public LiveData<Boolean> getEditarMutable()
    {
        if (editarMutable == null)
        {
            editarMutable = new MutableLiveData<>();
        }
        return editarMutable;
    }

    public void mostrarDatos()
    {
        propietarioMutable.setValue(api.obtenerUsuarioActual());
    }

    public void accionar(String nombre,Propietario p)
    {
        if (nombre.equals("Editar"))
        {
            nombreBotonMutable.setValue("Actualizar");
            editarMutable.setValue(true);
        }
        else
        {
            nombreBotonMutable.setValue("Editar");
            editarMutable.setValue(false);
            api.actualizarPerfil(p);
        }
    }

}