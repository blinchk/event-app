package ee.laus.eventapp.event;

import ee.laus.eventapp.event.dto.EventDto;
import ee.laus.eventapp.event.search.EventSearchParams;
import ee.laus.eventapp.participant.ParticipantService;
import ee.laus.eventapp.participant.dto.LegalEntityParticipantDto;
import ee.laus.eventapp.participant.dto.PrivateEntityParticipantDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EventControllerTest {
    private EventController eventController;
    @Mock
    private ParticipantService participantService;
    @Mock
    private EventService eventService;

    @BeforeEach
    void setUp() {
        eventController = new EventController(participantService, eventService);
    }

    @Test
    void getEvents() {
        EventSearchParams searchParams = new EventSearchParams(null, null);
        eventController.getEvents(searchParams);
        verify(eventService).getEvents(searchParams);
    }

    @Test
    void getEvent() {
        UUID uuid = UUID.randomUUID();
        eventController.getEvent(uuid);
        verify(eventService).getEvent(uuid);
    }

    @Test
    void removeEvent() {
        UUID uuid = UUID.randomUUID();
        eventController.deleteEvent(uuid);
        verify(eventService).deleteEvent(uuid);
    }

    @Test
    void addEvent() {
        EventDto event = new EventDto(
                "",
                "",
                LocalDateTime.MIN,
                ""
        );
        eventController.addEvent(event);
        verify(eventService).addEvent(event);
    }

    @Test
    void addLegalEntityParticipant() {
        UUID eventUuid = UUID.randomUUID();
        LegalEntityParticipantDto eventParticipant = new LegalEntityParticipantDto(
                "16481444",
                3,
                "zzz",
                1L,
                null
        );
        eventController.addParticipant(eventUuid, eventParticipant);
        verify(participantService).addEventParticipant(eventUuid, eventParticipant);
    }

    @Test
    void addPrivateEntityParticipant() {
        UUID eventUuid = UUID.randomUUID();
        PrivateEntityParticipantDto eventParticipant = new PrivateEntityParticipantDto(
                "60503040881",
                "Nikolas",
                "Laus",
                null,
                1L

        );
        eventController.addParticipant(eventUuid, eventParticipant);
        verify(participantService).addEventParticipant(eventUuid, eventParticipant);
    }
}