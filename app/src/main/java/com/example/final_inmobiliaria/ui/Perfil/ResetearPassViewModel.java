package com.example.final_inmobiliaria.ui.Perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.final_inmobiliaria.modelo.Propietario;
import com.example.final_inmobiliaria.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetearPassViewModel extends AndroidViewModel {

    private Context contexto;

    public ResetearPassViewModel(@NonNull Application application) {
        super(application);
        this.contexto = application.getApplicationContext();
    }

    public void CambiarContraseña(String pass)
    {
        try
        {
            SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(contexto);
            Call<Propietario> dato = ApiRetrofit.getServiceInmobiliaria().ResetearPass(sp.getString("token","-1"),pass);
            dato.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if (response.isSuccessful())
                    {
                        Toast.makeText(contexto,"Sea cambiado correctamente la contraseña",Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        Toast.makeText(contexto,"No se pudo cambiar la contraseña",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Propietario> call, Throwable t) {

                }
            });
        }
        catch (Exception ex)
        {
            ex.getMessage();
        }

    }
}