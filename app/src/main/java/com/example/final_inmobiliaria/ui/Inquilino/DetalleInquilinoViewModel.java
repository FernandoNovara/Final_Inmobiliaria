package com.example.final_inmobiliaria.ui.Inquilino;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.final_inmobiliaria.modelo.Inquilino;

public class DetalleInquilinoViewModel extends AndroidViewModel
{
    private MutableLiveData<Inquilino> InquilinoMutable;

    public DetalleInquilinoViewModel(@NonNull Application application)
    {
        super(application);
    }

    public LiveData<Inquilino> getInquilinoMutable()
    {
        if (InquilinoMutable == null)
        {
            InquilinoMutable = new MutableLiveData<>();
        }
        return InquilinoMutable;
    }

    public void cargarInquilino(Bundle bundle)
    {
        Inquilino inquilino = (Inquilino) bundle.get("inquilino");
        InquilinoMutable.setValue(inquilino);
    }

}