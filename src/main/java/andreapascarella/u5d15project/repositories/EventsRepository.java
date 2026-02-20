package andreapascarella.u5d15project.repositories;

import andreapascarella.u5d15project.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventsRepository extends JpaRepository<Event, UUID> {
}
