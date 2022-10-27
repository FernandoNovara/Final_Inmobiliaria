package com.example.final_inmobiliaria.modelo;

import java.io.Serializable;

public class Pago implements Serializable {

    private int idPago;
    private Contrato contrato;
    private double importe;
    private String fechaEmision;

    public Pago() {}

    public Pago(int idPago, Contrato contrato, double importe, String fechaEmision) {
        this.idPago = idPago;
        this.contrato = contrato;
        this.importe = importe;
        this.fechaEmision = fechaEmision;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getFechaDePago() {
        return fechaEmision;
    }

    public void setFechaDePago(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
}
