package pe.edu.upc.finance.amortization.plans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.finance.amortization.plans.entities.ResultadosFinancieros;

public interface ResultadosFinancierosRepository extends JpaRepository<ResultadosFinancieros, Integer>  {
}