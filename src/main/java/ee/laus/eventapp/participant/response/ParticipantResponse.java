package ee.laus.eventapp.participant.response;

import lombok.With;

import java.util.UUID;

public record ParticipantResponse(
    UUID uuid,
    String entityType,
    String details,
    String name,
    String firstName,
    String lastName,
    String personalCode,
    String registryCode,
    @With
    Long paymentTypeId,
    @With
    UUID eventUuid,
    Integer personCount
) {

}
