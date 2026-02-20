package andreapascarella.u5d15project.payloads;

import andreapascarella.u5d15project.enums.Role;
import jakarta.validation.constraints.*;

public record UserDTO(

        @NotBlank(message = "Il nome proprio è un campo obbligatorio")
        @Size(min = 3, max = 30, message = "Il nome proprio deve essere tra i 3 e i 30 caratteri")
        String name,

        @NotBlank(message = "Il cognome è un campo obbligatorio")
        @Size(min = 2, max = 30, message = "Il cognome deve essere tra i 2 e i 30 caratteri")
        String surname,

        @NotBlank(message = "L'email è obbligatoria")
        @Email(message = "L'indirizzo email inserito non è nel formato corretto!")
        String email,

        @NotBlank(message = "La password è obbligatoria")
        @Size(min = 4, message = "La password deve avere almeno 4 caratteri")
        @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{4,}$", message = "La password deve contenere una maiuscola, una minuscola, un numero e un carattere speciale")
        String password,

        @NotNull(message = "Il ruolo é un campo obbligatorio")
        Role role) {
}
