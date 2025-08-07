package pe.edu.upc.finance.amortization.plans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.finance.amortization.plans.entities.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}