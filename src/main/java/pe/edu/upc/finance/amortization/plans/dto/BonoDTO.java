package pe.edu.upc.finance.amortization.plans.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BonoDTO {
    private Integer id;
    private String nombre;
    private String montoNominal;
    private Integer plazoAnios;
    private String frecuenciaPago;
    private String tasaNominalAnual;
    private String tipoTasa;
    private String capitalizacion;
    private String tipoMoneda;
    private Integer periodoGracia;
    private String tipoGracia;
}