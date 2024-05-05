package ee.laus.eventapp.participant.response;

import java.util.UUID;

public interface EventParticipantListItem {
    UUID getUuid();
    String getName();
    String getCode();
}