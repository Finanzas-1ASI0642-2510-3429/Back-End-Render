package pe.edu.upc.finance.amortization.plans.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResultadosFinancieros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tcea;
    private String trea;
    private String duracion;
    private String duracionModificada;
    private String convexidad;
    private String flujoTotal;

    @OneToOne
    @JoinColumn(name = "bono_id")
    private Bono bono;

}