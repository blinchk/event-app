package ee.laus.eventapp.participant.dto;

public record LegalEntityParticipantDto(
        String registryCode,
        Integer personCount,
        String details,
        Integer paymentTypeId
) {
}
