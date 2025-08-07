package pe.edu.upc.finance.amortization.plans.dto;

public class ResultadosFinancierosDTO {
    private double tcea;
    private double trea;
    private double duracion;
    private double duracionModificada;
    private double convexidad;

    // Getters y setters
    public double getTcea() { return tcea; }
    public void setTcea(double tcea) { this.tcea = tcea; }

    public double getTrea() { return trea; }
    public void setTrea(double trea) { this.trea = trea; }

    public double getDuracion() { return duracion; }
    public void setDuracion(double duracion) { this.duracion = duracion; }

    public double getDuracionModificada() { return duracionModificada; }
    public void setDuracionModificada(double duracionModificada) { this.duracionModificada = duracionModificada; }

    public double getConvexidad() { return convexidad; }
    public void setConvexidad(double convexidad) { this.convexidad = convexidad; }
}