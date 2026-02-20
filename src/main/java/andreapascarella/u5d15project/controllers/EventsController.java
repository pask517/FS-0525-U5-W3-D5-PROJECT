package andreapascarella.u5d15project.controllers;

import andreapascarella.u5d15project.entities.Event;
import andreapascarella.u5d15project.payloads.EventDTO;
import andreapascarella.u5d15project.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventsController {
    private final EventsService eventsService;

    @Autowired
    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @PostMapping
    @PreAuthorize("hasRole('EVENT_ORGANIZER')")
    public Event createEvent(@RequestBody EventDTO payload) {
        return eventsService.saveEvent(payload);
    }

    @GetMapping
    public Page<Event> findAllEvents(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "eventDate") String orderBy,
                                     @RequestParam(defaultValue = "asc") String sortCriteria) {

        return this.eventsService.findAllEvents(page, size, orderBy, sortCriteria);
    }
}
