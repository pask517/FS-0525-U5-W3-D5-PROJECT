package andreapascarella.u5d15project.payloads;

import jakarta.validation.constraints.NotBlank;

public record ReservationDTO(
        @NotBlank(message = "L' id dell' organizzatore é un campo obbligatorio")
        String userId,
        @NotBlank(message = "L' id dell' evento é un campo obbligatorio")
        String eventId
) {
}
