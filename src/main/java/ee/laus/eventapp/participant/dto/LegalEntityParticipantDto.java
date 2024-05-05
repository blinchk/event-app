package ee.laus.eventapp.participant.dto;

public record LegalEntityParticipantDto(
        String registryCode,
        Integer personCount,
        String name,
        Long paymentTypeId,
        String details
) implements ParticipantDto {
}
