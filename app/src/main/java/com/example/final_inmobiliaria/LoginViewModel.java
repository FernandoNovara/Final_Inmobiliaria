package com.example.final_inmobiliaria;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.final_inmobiliaria.modelo.User;
import com.example.final_inmobiliaria.request.ApiRetrofit;
import com.example.final_inmobiliaria.ui.Perfil.PedirEmailActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel
{
    public Context context;

    public LoginViewModel(@NonNull Application application)
    {
        super(application);
        context = application.getApplicationContext();
    }

    public void iniciarSesion(String usuario, String contraseña)
    {
        User user = new User(usuario,contraseña);
        Call<String> tokenPromesa = ApiRetrofit.getServiceInmobiliaria().login(user);
        tokenPromesa.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful())
                {
                    Log.d("Salida",response.body());
                    SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(context);
                    SharedPreferences.Editor editor = sp.edit();
                    String token ="Bearer "+response.body();
                    editor.putString("token",token);
                    editor.commit();

                    Intent i = new Intent(context,MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);


                }
                else
                {
                    Log.d("Salida","sin respuesta");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t)
            {
                Log.d("Salida",t.getMessage());
            }
        });

    }

    public void Olvidado()
    {
        Intent i = new Intent(context, PedirEmailActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
