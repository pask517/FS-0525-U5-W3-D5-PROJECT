package andreapascarella.u5d15project.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "events")
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID eventId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User eventOrganizer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate eventDate;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int maxOccupancy;

    public Event(User eventOrganizer, String title, String description, LocalDate eventDate, String location, int maxOccupancy) {
        this.eventOrganizer = eventOrganizer;
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.location = location;
        this.maxOccupancy = maxOccupancy;
    }
}
