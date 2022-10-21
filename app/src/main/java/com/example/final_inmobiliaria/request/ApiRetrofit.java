package com.example.final_inmobiliaria.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.final_inmobiliaria.modelo.Inmueble;
import com.example.final_inmobiliaria.modelo.Propietario;
import com.example.final_inmobiliaria.modelo.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public class ApiRetrofit
{

        private static final String PATH="http://192.168.1.108:5000/api/";
        private static ServiceInmobiliaria serviceInmobiliaria;
        private static SharedPreferences sp;

        //con este metodo se obtiene el sharedpreferences de forma staticas y todo manejada por una clase

        public static SharedPreferences obtenerSharedPreferences(Context context)
        {
            if (sp == null)
            {
                sp = context.getSharedPreferences("Configuracion",0);
            }
            return sp;
        }


        public static ServiceInmobiliaria getServiceInmobiliaria()
        {
            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(PATH)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            serviceInmobiliaria = retrofit.create(ServiceInmobiliaria.class);
            return serviceInmobiliaria;
        }

        public interface ServiceInmobiliaria
        {
            //Url base:"http://192.168.1.108:5000/api/"

            @POST("Propietario/Login")
            Call<String> login(@Body User user);

            @GET("Propietario")
            Call<Propietario> obtenerPerfil(@Header("Authorization") String token);

            @POST("Propietario/Actualizar")
            Call<Propietario> actualizarPerfil(@Header("Authorization") String token,@Body Propietario entidad);

            @GET("Inmueble")
            Call<ArrayList<Inmueble>> obtenerInmuebles(@Header("Authorization") String token);

            @POST("Inmueble/AltaInmueble")
            Call<Inmueble> AltaInmueble(@Header("Authorization") String token,@Body Inmueble inmueble);

            @FormUrlEncoded
            @POST("Inmueble/CambiarEstado")
            Call<Boolean> CambiarEstado(@Header("Authorization") String token,@Field("id") int id);
        }
}
