package com.example.final_inmobiliaria.modelo;

import java.io.Serializable;

public class Inquilino implements Serializable {

    private int idInquilino;
    private Long dni;
    private String nombre;
    private String lugarTrabajo;
    private String email;
    private String telefono;

    public Inquilino() {}

    public Inquilino(int idInquilino, Long dni, String nombre, String lugarTrabajo, String email, String telefono) {
        this.idInquilino = idInquilino;
        this.dni = dni;
        this.nombre = nombre;
        this.lugarTrabajo = lugarTrabajo;
        this.email = email;
        this.telefono = telefono;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
