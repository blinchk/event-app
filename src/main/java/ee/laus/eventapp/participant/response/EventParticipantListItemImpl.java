package ee.laus.eventapp.participant.response;

import java.util.UUID;

public record EventParticipantListItemImpl(
        UUID uuid,
        String name,
        String code
) implements EventParticipantListItem {
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
