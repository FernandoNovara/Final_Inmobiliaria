package com.example.final_inmobiliaria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class LoginActivity extends AppCompatActivity
{
    private Button btnLogin,btnOlvidado;
    private EditText etUsuario,etContraseña;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        loginViewModel= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);

        InicializarVista();

    }

    public void InicializarVista()
    {
        btnLogin=findViewById(R.id.btnLogin);
        etUsuario=findViewById(R.id.etUsuario);
        etContraseña=findViewById(R.id.etContraseña);
        btnOlvidado = findViewById(R.id.btnOlvidado);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.iniciarSesion(etUsuario.getText().toString(),etContraseña.getText().toString());
            }
        });

        btnOlvidado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.Olvidado();
            }
        });
    }


}
