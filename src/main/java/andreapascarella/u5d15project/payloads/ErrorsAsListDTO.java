package andreapascarella.u5d15project.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsAsListDTO(String message, LocalDateTime timestamp, List<String> errors) {
}
