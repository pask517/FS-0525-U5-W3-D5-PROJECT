package andreapascarella.u5d15project.repositories;

import andreapascarella.u5d15project.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventsRepository extends JpaRepository<Event, UUID> {
}
