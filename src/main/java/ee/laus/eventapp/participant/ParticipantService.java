package ee.laus.eventapp.participant;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vladislavgoltjajev.personalcode.locale.estonia.EstonianPersonalCodeValidator;
import ee.laus.eventapp.common.exception.EntityNotFoundException;
import ee.laus.eventapp.common.exception.IllegalPersonalCodeException;
import ee.laus.eventapp.event.EventRepository;
import ee.laus.eventapp.common.exception.InvalidEntityIdException;
import ee.laus.eventapp.participant.dto.LegalEntityParticipantDto;
import ee.laus.eventapp.participant.dto.ParticipantDto;
import ee.laus.eventapp.participant.dto.PrivateEntityParticipantDto;
import ee.laus.eventapp.participant.entity.LegalEntityParticipant;
import ee.laus.eventapp.participant.entity.PrivateEntityParticipant;
import ee.laus.eventapp.participant.response.EventParticipantListItem;
import ee.laus.eventapp.participant.response.EventParticipantListItemImpl;
import ee.laus.eventapp.participant.response.ParticipantResponse;
import ee.laus.eventapp.payment.PaymentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final LegalEntityParticipantRepository legalEntityParticipantRepository;
    private final PrivateEntityParticipantRepository privateEntityParticipantRepository;
    private final PaymentTypeRepository paymentTypeRepository;
    private final EventRepository eventRepository;
    private final ObjectMapper mapper;
    private final EstonianPersonalCodeValidator personalCodeValidator = new EstonianPersonalCodeValidator();

    public List<EventParticipantListItem> getEventParticipants(UUID eventUuid) {
        return participantRepository.getEventParticipantsByEventUuid(eventUuid);
    }

    public EventParticipantListItem addEventParticipant(UUID eventUuid, LegalEntityParticipantDto request) {
        LegalEntityParticipant participant = new LegalEntityParticipant(
                request.registryCode(),
                request.name(),
                request.personCount()
        );
        return addEventParticipant(eventUuid, participant, request);
    }

    public EventParticipantListItem addEventParticipant(UUID eventUuid, PrivateEntityParticipantDto request) {
        if (!personalCodeValidator.isValid(request.personalCode())) {
            throw new IllegalPersonalCodeException("Invalid personal code");
        }
        PrivateEntityParticipant participant = new PrivateEntityParticipant(
                request.personalCode(),
                request.firstName(),
                request.lastName()
        );
        return addEventParticipant(eventUuid, participant, request);
    }

    private EventParticipantListItem addEventParticipant(UUID eventUuid, Participant participant, ParticipantDto request) {
        participant.setDetails(request.details());
        participant.setPaymentType(
                paymentTypeRepository.findById(request.paymentTypeId())
                        .orElseThrow(() -> new InvalidEntityIdException("Invalid payment type ID"))
        );
        participant.setEvent(
                eventRepository.findById(eventUuid)
                        .orElseThrow(() -> new InvalidEntityIdException("Invalid event UUID"))
        );
        participant = participantRepository.save(participant);
        return new EventParticipantListItemImpl(participant.getUuid(), participant.getName(), participant.getCode());
    }

    public void deleteParticipant(UUID uuid) {
        participantRepository.deleteById(uuid);
    }

    public void deleteAllParticipantsByEventUuid(UUID uuid) {
        participantRepository.deleteAllByEventUuid(uuid);
    }

    public ParticipantResponse getParticipant(UUID uuid) {
        Participant participant = participantRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        return mapper.convertValue(participant, ParticipantResponse.class)
                .withEventUuid(participant.getEvent().getUuid())
                .withPaymentTypeId(participant.getPaymentType().getId());
    }

    public ParticipantResponse saveParticipant(UUID uuid, LegalEntityParticipantDto request) {
        LegalEntityParticipant participant = legalEntityParticipantRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        participant.setName(request.name());
        participant.setRegistryCode(request.registryCode());
        participant.setPersonCount(request.personCount());
        participant.setUuid(uuid);
        return saveParticipant(participant, request);
    }

    public ParticipantResponse saveParticipant(UUID uuid, PrivateEntityParticipantDto request) {
        PrivateEntityParticipant participant = privateEntityParticipantRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        participant.setFirstName(request.firstName());
        participant.setLastName(request.lastName());
        if (!personalCodeValidator.isValid(request.personalCode())) {
            throw new IllegalPersonalCodeException("Invalid personal code");
        }
        participant.setPersonalCode(request.personalCode());
        return saveParticipant(participant, request);
    }

    private ParticipantResponse saveParticipant(Participant participant, ParticipantDto request) {
        participant.setDetails(request.details());
        participant.setPaymentType(
                paymentTypeRepository.findById(request.paymentTypeId())
                        .orElseThrow(() -> new InvalidEntityIdException("Invalid payment type ID"))
        );
        participant = participantRepository.save(participant);
        return mapper.convertValue(participant, ParticipantResponse.class)
                .withEventUuid(participant.getEvent().getUuid());
    }
}
