package ee.laus.eventapp.event.response;

import ee.laus.eventapp.participant.response.EventParticipantResponse;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record EventResponse(
        UUID uuid,
        String name,
        LocalDateTime time,
        String location,
        String description,
        @Setter
        List<EventParticipantResponse> participants
) {
}
