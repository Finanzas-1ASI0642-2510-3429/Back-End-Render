package pe.edu.upc.finance.amortization.plans.dto;

public class BonoResumenDTO {
    private Long id;
    private String nombre;
    private double montoNominal;
    private int plazoAnios;
    private String tipoMoneda;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMontoNominal() {
        return montoNominal;
    }

    public void setMontoNominal(double montoNominal) {
        this.montoNominal = montoNominal;
    }

    public int getPlazoAnios() {
        return plazoAnios;
    }

    public void setPlazoAnios(int plazoAnios) {
        this.plazoAnios = plazoAnios;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }
}