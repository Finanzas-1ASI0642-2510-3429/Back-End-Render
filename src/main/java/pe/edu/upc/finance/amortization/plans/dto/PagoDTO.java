package pe.edu.upc.finance.amortization.plans.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PagoDTO {
    private Long id;
    private Integer periodo;
    private String saldoInicial;
    private String interes;
    private String amortizacion;
    private String cuota;
    private String saldoFinal;
    private String tasaEfectivaPeriodo;
    private LocalDate fechaPago; // En formato "YYYY-MM-DD"
}