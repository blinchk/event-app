package ee.laus.eventapp.participant;

import ee.laus.eventapp.event.Event;
import ee.laus.eventapp.event.EventRepository;
import ee.laus.eventapp.participant.dto.LegalEntityParticipantDto;
import ee.laus.eventapp.participant.entity.LegalEntityParticipant;
import ee.laus.eventapp.payment.PaymentType;
import ee.laus.eventapp.payment.PaymentTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParticipantServiceTest {
    private ParticipantService participantService;
    @Mock
    private ParticipantRepository participantRepository;
    @Mock
    private PaymentTypeRepository paymentTypeRepository;
    @Mock
    private EventRepository eventRepository;

    @BeforeEach
    void setUp() {
        participantService = new ParticipantService(
                participantRepository,
                paymentTypeRepository,
                eventRepository
        );
    }

    @Test
    void getEventParticipants() {
        UUID eventUuid = UUID.randomUUID();
        participantService.getEventParticipants(eventUuid);
        verify(participantRepository).getEventParticipantsByEventUuid(eventUuid);
    }

    @Test
    void addLegalEntityEventParticipant() {
        UUID eventUuid = UUID.randomUUID();
        LocalDateTime time = LocalDateTime.now();
        String code = "16481444";
        String name = "Legal Entity OÜ";
        Integer personCount = 3;
        LegalEntityParticipantDto dto = new LegalEntityParticipantDto(
                code,
                personCount,
                name,
                1
        );
        LegalEntityParticipant participant = new LegalEntityParticipant(
                dto.registryCode(),
                dto.name(),
                personCount
        );
        Event event = new Event(
                eventUuid,
                "Event Name",
                time,
                "Tallinn, Estonia",
                "Lorem ipsum"
        );
        PaymentType paymentType = new PaymentType(1, "CASH", "Sularaha");
        when(paymentTypeRepository.findById(1)).thenReturn(Optional.of(paymentType));
        when(eventRepository.findById(eventUuid)).thenReturn(Optional.of(event));
        participantService.addEventParticipant(eventUuid, dto);
    }

    @Test
    void addPrivateEntityEventParticipant() {
    }
}