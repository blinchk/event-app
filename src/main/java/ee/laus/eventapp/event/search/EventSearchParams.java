package ee.laus.eventapp.event.search;

import java.time.LocalDate;

public record EventSearchParams(
        LocalDate startDate,
        LocalDate endDate
) {
}
