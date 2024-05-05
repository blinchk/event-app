package ee.laus.eventapp.participant;

import ee.laus.eventapp.common.exception.IllegalPersonalCodeException;
import ee.laus.eventapp.event.Event;
import ee.laus.eventapp.event.EventRepository;
import ee.laus.eventapp.participant.dto.LegalEntityParticipantDto;
import ee.laus.eventapp.participant.dto.PrivateEntityParticipantDto;
import ee.laus.eventapp.participant.entity.LegalEntityParticipant;
import ee.laus.eventapp.participant.entity.PrivateEntityParticipant;
import ee.laus.eventapp.participant.response.EventParticipantResponse;
import ee.laus.eventapp.payment.PaymentType;
import ee.laus.eventapp.payment.PaymentTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
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
                1L,
                null
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
        PaymentType paymentType = new PaymentType(1L, "CASH", "Sularaha");
        when(paymentTypeRepository.findById(1L)).thenReturn(Optional.of(paymentType));
        when(eventRepository.findById(eventUuid)).thenReturn(Optional.of(event));
        when(participantRepository.save(any(LegalEntityParticipant.class))).thenReturn(participant);
        EventParticipantResponse actual = participantService.addEventParticipant(eventUuid, dto);
        verify(eventRepository).findById(eventUuid);
        verify(paymentTypeRepository).findById(dto.paymentTypeId());
        verify(participantRepository).save(any(LegalEntityParticipant.class));
        assertEquals(name, actual.getName());
        assertEquals(code, actual.getCode());
    }

    @Test
    void addPrivateEntityEventParticipant() {
        UUID eventUuid = UUID.randomUUID();
        LocalDateTime time = LocalDateTime.now();
        String code = "50309030254";
        String name = "Nikolas Laus";
        PrivateEntityParticipantDto dto = new PrivateEntityParticipantDto(
                code,
                "Nikolas",
                "Laus",
                "",
                1L
        );
        PrivateEntityParticipant participant = new PrivateEntityParticipant(
                dto.personalCode(),
                dto.firstName(),
                dto.lastName()
        );
        Event event = new Event(
                eventUuid,
                "Event Name",
                time,
                "Tallinn, Estonia",
                "Lorem ipsum"
        );
        PaymentType paymentType = new PaymentType(1L, "CASH", "Sularaha");
        when(paymentTypeRepository.findById(1L)).thenReturn(Optional.of(paymentType));
        when(eventRepository.findById(eventUuid)).thenReturn(Optional.of(event));
        when(participantRepository.save(any(PrivateEntityParticipant.class))).thenReturn(participant);
        EventParticipantResponse actual = participantService.addEventParticipant(eventUuid, dto);
        verify(eventRepository).findById(eventUuid);
        verify(paymentTypeRepository).findById(dto.paymentTypeId());
        verify(participantRepository).save(any(PrivateEntityParticipant.class));
        assertEquals(name, actual.getName());
        assertEquals(code, actual.getCode());
    }

    @Test
    void addPrivateEntityParticipant_throwsIllegalPersonalCodeException() {
        UUID eventUuid = UUID.randomUUID();
        String illegalPersonalCode = "50303030111";
        PrivateEntityParticipantDto dto = new PrivateEntityParticipantDto(
               illegalPersonalCode,
               "First Name",
               "Last Name",
               "",
               1L
        );
        assertThrows(IllegalPersonalCodeException.class, () -> {
            participantService.addEventParticipant(eventUuid, dto);
        });
    }

    @Test
    void deleteParticipant() {
        UUID uuid = UUID.randomUUID();
        participantService.deleteParticipant(uuid);
        verify(participantRepository).deleteById(uuid);
    }

    @Test
    void deleteAllParticipantsByEventUuid() {
        UUID uuid = UUID.randomUUID();
        participantService.deleteAllParticipantsByEventUuid(uuid);
        verify(participantRepository).deleteAllByEventUuid(uuid);
    }
}