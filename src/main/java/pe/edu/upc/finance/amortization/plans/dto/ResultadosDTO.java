package pe.edu.upc.finance.amortization.plans.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResultadosDTO {
    private String tcea;
    private String trea;
    private String duracion;
    private String duracionModificada;
    private String convexidad;
    private String flujoTotal;
}