package pe.edu.upc.finance.amortization.plans.dto;

import java.time.LocalDate;

public class PagoResponseDTO {
    private LocalDate fechaPago;
    private Integer numeroCuota;
    private Double cuota;
    private Double interes;
    private Double amortizacion;
    private Double saldo;

    // Getters
    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public Double getCuota() {
        return cuota;
    }

    public Double getInteres() {
        return interes;
    }

    public Double getAmortizacion() {
        return amortizacion;
    }

    public Double getSaldo() {
        return saldo;
    }

    public Integer getNumeroCuota() {return numeroCuota; }

    // Setters
    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public void setCuota(Double cuota) {
        this.cuota = cuota;
    }

    public void setInteres(Double interes) {
        this.interes = interes;
    }

    public void setAmortizacion(Double amortizacion) {
        this.amortizacion = amortizacion;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void setNumeroCuota(Integer numeroCuota) {this.numeroCuota = numeroCuota; }
}