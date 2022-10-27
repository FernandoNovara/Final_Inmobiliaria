package com.example.final_inmobiliaria.ui.Inquilino;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.final_inmobiliaria.modelo.Contrato;
import com.example.final_inmobiliaria.request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends AndroidViewModel
{
    private MutableLiveData<ArrayList<Contrato>> contratoMutable;
    private Context contexto;

    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        this.contexto = getApplication();
    }


    public LiveData<ArrayList<Contrato>> getContratoMutable()
    {
        if(contratoMutable == null)
        {
            contratoMutable = new MutableLiveData<>();
        }
        return contratoMutable;
    }

    public void cargarInmuebles()
    {
        SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(contexto);
        Call<ArrayList<Contrato>> contratos = ApiRetrofit.getServiceInmobiliaria().ObtenerContratosVigentes(sp.getString("token","-1"));
        contratos.enqueue(new Callback<ArrayList<Contrato>>() {
            @Override
            public void onResponse(Call<ArrayList<Contrato>> call, Response<ArrayList<Contrato>> response) {
                if ( response.isSuccessful())
                {
                    ArrayList<Contrato> lista = response.body();
                    contratoMutable.setValue(lista);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Contrato>> call, Throwable t) {

            }
        });
    }
}