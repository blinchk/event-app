package ee.laus.eventapp.event.dto;

import jakarta.validation.constraints.Future;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record EventDto(
        String name,
        String location,
        @Future
        LocalDateTime time,
        @Length(max = 1000)
        String description
) {
}
