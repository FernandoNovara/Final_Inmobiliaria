package com.example.final_inmobiliaria.ui.Pago;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.final_inmobiliaria.modelo.Contrato;
import com.example.final_inmobiliaria.modelo.Pago;
import com.example.final_inmobiliaria.request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagoViewModel extends AndroidViewModel
{
    private MutableLiveData<ArrayList<Pago>> PagoMutable;
    private Context contexto;

    public PagoViewModel(@NonNull Application application) {
        super(application);
        this.contexto = application.getApplicationContext();
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
        SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(contexto);
        Contrato contrato = (Contrato) bundle.getSerializable("contrato");
        Call<ArrayList<Pago>> lista = ApiRetrofit.getServiceInmobiliaria().ObtenerPagos(sp.getString("token","-1"),contrato.getIdContrato());
        lista.enqueue(new Callback<ArrayList<Pago>>() {
            @Override
            public void onResponse(Call<ArrayList<Pago>> call, Response<ArrayList<Pago>> response) {
                if (response.isSuccessful())
                {
                    PagoMutable.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pago>> call, Throwable t) {

            }
        });


    }
}