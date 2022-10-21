package com.example.final_inmobiliaria.ui.Perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.final_inmobiliaria.modelo.Propietario;
import com.example.final_inmobiliaria.request.ApiClient;
import com.example.final_inmobiliaria.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(context);
        Call<Propietario> propietario = ApiRetrofit.getServiceInmobiliaria().obtenerPerfil(sp.getString("token","-1"));
        propietario.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful())
                {
                    propietarioMutable.postValue(response.body());
                    Log.d("Salida",response.body().toString());
                }
                else
                {
                    Log.d("Salida","no mando nada");
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {

            }
        });

        //propietarioMutable.setValue(api.obtenerUsuarioActual());
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
            SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(context);
            Call<Propietario> propietarioCall = ApiRetrofit.getServiceInmobiliaria().actualizarPerfil(sp.getString("token","-1"),p);
            propietarioCall.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if(response.isSuccessful())
                    {
                        Toast.makeText(context,"Usuario actualizado correctamente",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(context,"Usuario no actualizado",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Propietario> call, Throwable t) {

                }
            });
        }
    }

}