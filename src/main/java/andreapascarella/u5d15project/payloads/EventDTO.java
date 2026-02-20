package andreapascarella.u5d15project.payloads;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record EventDTO(
        @NotBlank(message = "L' id dell' organizzatore é un campo obbligatorio")
        String userId,
        @NotBlank(message = "Il titolo é un campo obbligatorio")
        String title,
        @NotBlank(message = "La descrizione é un campo obbligatorio")
        String description,
        @NotBlank(message = "La data é un campo obbligatorio")
        LocalDate eventDate,
        @NotBlank(message = "La location é un campo obbligatorio")
        String location,
        @NotBlank(message = "Il numero massimo di persone ammesse é un campo obbligatorio")
        int maxOccupancy
) {
}
