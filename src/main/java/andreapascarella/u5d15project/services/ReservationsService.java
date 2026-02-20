package andreapascarella.u5d15project.services;

import andreapascarella.u5d15project.entities.Reservation;
import andreapascarella.u5d15project.exceptions.BadRequestException;
import andreapascarella.u5d15project.payloads.ReservationDTO;
import andreapascarella.u5d15project.repositories.ReservationsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ReservationsService {

    private final UsersService usersService;
    private final ReservationsRepository reservationsRepository;
    private final EventsService eventsService;

    @Autowired
    public ReservationsService(UsersService usersService, ReservationsRepository reservationsRepository, EventsService eventsService) {
        this.usersService = usersService;
        this.reservationsRepository = reservationsRepository;
        this.eventsService = eventsService;
    }

    public Reservation saveReservation(ReservationDTO payload) {
        this.reservationsRepository.findByUserUserIdAndEventEventId(UUID.fromString(payload.userId()), UUID.fromString(payload.eventId())).ifPresent(reservation -> {
            throw new BadRequestException("Hai giá una prenotazione per questo evento");
        });

        Reservation newReservation = new Reservation(usersService.findById(UUID.fromString(payload.userId())), eventsService.findById(UUID.fromString(payload.eventId())));

        Reservation savedReservation = this.reservationsRepository.save(newReservation);

        log.info("La prenotazione con id " + savedReservation.getReservationId() + " é stata salvata correttamente");

        return savedReservation;
    }
}
