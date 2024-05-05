package ee.laus.eventapp.participant.response;

import java.util.UUID;

public record EventParticipantResponseImpl(
        UUID uuid,
        String name,
        String code
) implements EventParticipantResponse {
    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCode() {
        return code;
    }
}
