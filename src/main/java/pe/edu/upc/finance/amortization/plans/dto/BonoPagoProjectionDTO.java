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
public class BonoPagoProjectionDTO {
    private Long bonoId;
    private Integer montoNominal;
    private Integer plazoAnios;

    private Long pagoId;
    private LocalDate fechaPago;
    private Double cuota;
    private Double interes;
    private Double amortizacion;
    private Double saldo;

    // Getters (puedes omitir setters si es solo de lectura)
    // ...
}