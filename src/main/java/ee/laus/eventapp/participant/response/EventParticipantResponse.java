package ee.laus.eventapp.participant.response;

import java.util.UUID;

public interface EventParticipantResponse {
    UUID getUuid();
    String getName();
    String getCode();
}