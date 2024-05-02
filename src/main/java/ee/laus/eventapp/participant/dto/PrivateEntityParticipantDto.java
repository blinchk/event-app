package ee.laus.eventapp.participant.dto;

public record PrivateEntityParticipantDto(
        String personalCode,
        String firstName,
        String lastName,
        String details,
        Integer paymentTypeId
) implements ParticipantDto {
}
