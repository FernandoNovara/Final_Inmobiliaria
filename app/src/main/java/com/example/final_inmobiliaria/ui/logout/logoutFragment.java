package com.example.final_inmobiliaria.ui.logout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.final_inmobiliaria.R;

public class logoutFragment extends Fragment {

    private TextView tvlogout;

    public static logoutFragment newInstance() {
        return new logoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logout, container, false);
        mostrarDialog();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    public void mostrarDialog()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.getContext())
                .setTitle("Cerrar sesion")
                .setMessage("Desea cerrar la app?")
                .setPositiveButton (R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Navigation.findNavController(getActivity(),R.id.nav_host_fragment_content_main).navigate(R.id.inicioFragment);
                    }
                });
        alertDialog.show();
    }

}