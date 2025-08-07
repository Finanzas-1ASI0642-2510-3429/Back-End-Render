package pe.edu.upc.finance.amortization.plans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.finance.amortization.plans.entities.Bono;

import java.util.List;
import java.util.Optional;

public interface BonoRepository extends JpaRepository<Bono, Long> {

    @Query("SELECT b FROM Bono b LEFT JOIN FETCH b.pagos WHERE b.id = :id")
    Optional<Bono> buscarPorIdConPagos(@Param("id") Long id);

//    @Query("SELECT new com.practica.upc.backendsmartbonder.dto.BonoPagoProjectionDTO(c.id_Comentario, c.contenido, c.calificacion, c.fecha, u.nombre_Universtario) " +
//            "FROM Comentario c JOIN c.universitario u JOIN c.inmueble i " +
//            "WHERE i.id_Inmueble = :inmuebleId")
//    List<BonoPagoProjectionDTO> findComentariosByInmueble(Long inmuebleId);

    @Query("SELECT b FROM Bono b LEFT JOIN FETCH b.pagos")
    List<Bono> obtenerBonosConPagos();

    @Query("SELECT b.nombre FROM Bono b")
    List<String> obtenerNombresDeBonos();

    @Query("SELECT b FROM Bono b LEFT JOIN FETCH b.pagos WHERE b.nombre = :nombre")
    Optional<Bono> buscarPorNombreConPagos(@Param("nombre") String nombre);

    List<Bono> findAllByNombreCliente(String nombreCliente);


}