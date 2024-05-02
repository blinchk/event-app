package ee.laus.eventapp.event.response;

import java.time.LocalDate;
import java.util.UUID;

public record EventListItem(
        UUID uuid,
        String name,
        LocalDate date
) {
}
