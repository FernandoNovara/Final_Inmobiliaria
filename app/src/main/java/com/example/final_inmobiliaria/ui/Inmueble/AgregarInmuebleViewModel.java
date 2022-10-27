package com.example.final_inmobiliaria.ui.Inmueble;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.final_inmobiliaria.modelo.Inmueble;
import com.example.final_inmobiliaria.request.ApiRetrofit;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarInmuebleViewModel extends AndroidViewModel
{
    private Context contexto;
    private MutableLiveData<Bitmap> fotoMutable;

    public AgregarInmuebleViewModel(@NonNull Application application)
    {
        super(application);
        this.contexto = application.getApplicationContext();
    }

    public LiveData<Bitmap> getFotoMutable()
    {
        if(fotoMutable == null)
        {
            fotoMutable = new MutableLiveData<>();
        }
        return fotoMutable;
    }

    public void AgregarInmueble(Inmueble inmueble)
    {
        SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(contexto);
        Call<Inmueble> dato = ApiRetrofit.getServiceInmobiliaria().AltaInmueble(sp.getString("token","-1"),inmueble);
        dato.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful())
                {
                    Toast.makeText(contexto,"Se agrego correctamente.",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(contexto,"No se ha podido agregar.",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {

            }
        });

        Log.d("Salida","Entro al Agregar");
    }

    public void respuetaDeCamara(int requestCode, int resultCode,Intent data, int REQUEST_IMAGE_CAPTURE){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //Recupero la respuesta de la camara.
            Bundle extras = data.getExtras();

            //paso a bitmap los datos de la camara
            Bitmap foto = (Bitmap) extras.get("data");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            foto.compress(Bitmap.CompressFormat.JPEG,100,baos);
            byte[] b = baos.toByteArray();
            fotoMutable.postValue(foto);
        }
    }


}