package ee.laus.eventapp.participant;

import com.github.vladislavgoltjajev.personalcode.locale.estonia.EstonianPersonalCodeValidator;
import ee.laus.eventapp.common.exception.IllegalPersonalCodeException;
import ee.laus.eventapp.event.EventRepository;
import ee.laus.eventapp.common.exception.InvalidEntityIdException;
import ee.laus.eventapp.participant.dto.LegalEntityParticipantDto;
import ee.laus.eventapp.participant.dto.ParticipantDto;
import ee.laus.eventapp.participant.dto.PrivateEntityParticipantDto;
import ee.laus.eventapp.participant.entity.LegalEntityParticipant;
import ee.laus.eventapp.participant.entity.PrivateEntityParticipant;
import ee.laus.eventapp.participant.response.EventParticipantResponse;
import ee.laus.eventapp.payment.PaymentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final PaymentTypeRepository paymentTypeRepository;
    private final EventRepository eventRepository;
    private final EstonianPersonalCodeValidator personalCodeValidator = new EstonianPersonalCodeValidator();

    public List<EventParticipantResponse> getEventParticipants(UUID eventUuid) {
        return participantRepository.getEventParticipantsByEventUuid(eventUuid);
    }

    public EventParticipantResponse addEventParticipant(UUID eventUuid, LegalEntityParticipantDto request) {
        LegalEntityParticipant participant = new LegalEntityParticipant(
                request.registryCode(),
                request.name(),
                request.personCount()
        );
        participant.setPaymentType(
                paymentTypeRepository.findById(request.paymentTypeId())
                        .orElseThrow(() -> new InvalidEntityIdException("Invalid payment type ID"))
        );
        participant.setEvent(
                eventRepository.findById(eventUuid)
                        .orElseThrow(() -> new InvalidEntityIdException("Invalid event UUID"))
        );
        participant = participantRepository.save(participant);
        return new EventParticipantResponse(participant.getName(), participant.getCode());
    }

    public EventParticipantResponse addEventParticipant(UUID eventUuid, PrivateEntityParticipantDto request) {
        if (!personalCodeValidator.isValid(request.personalCode())) {
            throw new IllegalPersonalCodeException();
        }
        PrivateEntityParticipant participant = new PrivateEntityParticipant(
                request.personalCode(),
                request.firstName(),
                request.lastName()
        );
        participant.setPaymentType(
                paymentTypeRepository.findById(request.paymentTypeId())
                        .orElseThrow(() -> new InvalidEntityIdException("Invalid payment type ID"))
        );
        participant.setEvent(
                eventRepository.findById(eventUuid)
                        .orElseThrow(() -> new InvalidEntityIdException("Invalid event UUID"))
        );
        participant = participantRepository.save(participant);
        return new EventParticipantResponse(participant.getName(), participant.getCode());
    }
}
