package com.example.final_inmobiliaria;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.final_inmobiliaria.modelo.Propietario;
import com.example.final_inmobiliaria.request.ApiClient;

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
        ApiClient api = ApiClient.getApi();
        Propietario p = api.login(usuario, contraseña);
        if (p != null)
        {
            Intent i = new Intent(context,MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);

            Toast.makeText(context, "Bienvenido "+p.getNombre()+" "+p.getApellido(), Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(context,"Usuario y/o Contraseña Incorrecta.", Toast.LENGTH_LONG).show();
        }
    }
}
