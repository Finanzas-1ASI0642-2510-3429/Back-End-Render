package pe.edu.upc.finance.amortization.plans.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaPago;
    private Integer numeroCuota;
    private Double cuota;
    private Double interes;
    private Double amortizacion;
    private Double saldo;

    @ManyToOne
    @JoinColumn(name = "bono_id")
    @JsonBackReference
    private Bono bono;

    // Getters
    public Long getId() {
        return id;
    }

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

    public Bono getBono() {
        return bono;
    }

    public Integer getNumeroCuota() {return numeroCuota;    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

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

    public void setBono(Bono bono) {
        this.bono = bono;
    }

    public void setNumeroCuota(Integer numeroCuota) {this.numeroCuota = numeroCuota; }
}