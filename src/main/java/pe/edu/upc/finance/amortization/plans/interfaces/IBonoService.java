package pe.edu.upc.finance.amortization.plans.interfaces;

import pe.edu.upc.finance.amortization.plans.dto.BonoRequestDTO;
import pe.edu.upc.finance.amortization.plans.entities.Bono;

public interface IBonoService {
    public Bono crearDesdeDTO(BonoRequestDTO dto);
    public Bono guardar(Bono bono);

}