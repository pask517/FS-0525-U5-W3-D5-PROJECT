package andreapascarella.u5d15project.services;

import andreapascarella.u5d15project.entities.Event;
import andreapascarella.u5d15project.exceptions.BadRequestException;
import andreapascarella.u5d15project.payloads.EventDTO;
import andreapascarella.u5d15project.repositories.EventsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class EventsService {

    private final EventsRepository eventsRepository;
    private final UsersService usersService;

    @Autowired
    public EventsService(EventsRepository eventsRepository, UsersService usersService) {
        this.eventsRepository = eventsRepository;
        this.usersService = usersService;
    }

    public Event saveEvent(EventDTO payload) {
        this.eventsRepository.findByLocationAndEventDate(payload.location(), payload.eventDate()).ifPresent(event -> {
            throw new BadRequestException("L'evento con location " + event.getLocation() + " in data " + event.getEventDate() + " é giá in programma!");
        });

        Event newEvent = new Event(usersService.findById(UUID.fromString(payload.userId())), payload.title(), payload.description(), payload.eventDate(), payload.location(), payload.maxOccupancy());

        Event savedEvent = this.eventsRepository.save(newEvent);

        log.info("L'evento con id " + savedEvent.getEventId() + " é stato salvato correttamente");

        return savedEvent;
    }
}
