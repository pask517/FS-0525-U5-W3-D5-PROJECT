package andreapascarella.u5d15project.controllers;

import andreapascarella.u5d15project.entities.Reservation;
import andreapascarella.u5d15project.payloads.ReservationDTO;
import andreapascarella.u5d15project.services.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    private final ReservationsService reservationsService;

    @Autowired
    public ReservationsController(ReservationsService reservationsService) {
        this.reservationsService = reservationsService;
    }

    @PostMapping
    public Reservation createReservation(@RequestBody ReservationDTO payload) {
        return reservationsService.saveReservation(payload);
    }
}
