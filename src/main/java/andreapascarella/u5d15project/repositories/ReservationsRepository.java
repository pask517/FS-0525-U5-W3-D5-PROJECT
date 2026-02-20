package andreapascarella.u5d15project.repositories;

import andreapascarella.u5d15project.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationsRepository extends JpaRepository<Reservation, UUID> {
}
