package com.example.final_inmobiliaria.ui.Perfil;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.final_inmobiliaria.modelo.Propietario;
import com.example.final_inmobiliaria.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PedirEmailActivityViewModel extends AndroidViewModel {

    private Context contexto;
    private MutableLiveData<Boolean> bandera;

    public PedirEmailActivityViewModel(@NonNull Application application) {
        super(application);
        this.contexto = application.getApplicationContext();
    }

    public LiveData<Boolean> getBandera()
    {
        if(bandera == null)
        {
            bandera = new MutableLiveData<>();
        }
        return bandera;
    }

    public void EnviarPedido(String email) {
        Call<Propietario> pedido = ApiRetrofit.getServiceInmobiliaria().ObtenerEmail(email);
        pedido.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(contexto, "Sea enviado correctamente,Revisa tu correo.", Toast.LENGTH_LONG).show();
                    bandera.postValue(true);
                } else {
                    Toast.makeText(contexto, "No sea podido enviar el mail", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {

            }
        });



    }
}