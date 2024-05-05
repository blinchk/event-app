package ee.laus.eventapp.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.laus.eventapp.common.exception.EntityNotFoundException;
import ee.laus.eventapp.event.dto.EventDto;
import ee.laus.eventapp.event.response.EventResponse;
import ee.laus.eventapp.event.search.EventSearchParams;
import ee.laus.eventapp.participant.ParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {
    private EventService eventService;
    @Mock
    private EventRepository eventRepository;
    @Mock
    private ParticipantService participantService;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        eventService = new EventService(eventRepository, participantService, mapper);
    }

    @Test
    void getEvent() {
        UUID uuid = UUID.randomUUID();
        LocalDateTime time = LocalDateTime.now();
        Event event = new Event(uuid, "", time, "", "");
        when(eventRepository.findById(uuid)).thenReturn(Optional.of(event));
        when(participantService.getEventParticipants(uuid)).thenReturn(Collections.emptyList());
        EventResponse excepted = new EventResponse(uuid, "", time, "", "", Collections.emptyList());
        EventResponse actual = eventService.getEvent(uuid);
        assertEquals(excepted, actual);
    }

    @Test
    void getEvent_throwsEntityNotFoundException() {
        UUID uuid = UUID.randomUUID();
        when(eventRepository.findById(uuid)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> eventService.getEvent(uuid));}

    @Test
    void getEvents() {
        EventSearchParams searchParams = new EventSearchParams(null, null);
        eventService.getEvents(searchParams);
        verify(eventRepository).getEvents(searchParams);
    }

    @Test
    void addEvent() {
        String name = "Event Name";
        String location = "Tallinn, Estonia";
        String description = "Lorem ipsum";
        EventDto dto = new EventDto(name, location, null, description);
        Event event = mapper.convertValue(dto, Event.class);
        when(eventRepository.save(any(Event.class))).thenReturn(event);
        EventResponse actual = eventService.addEvent(dto);
        verify(eventRepository).save(any(Event.class));
        assertEquals(name, actual.name());
        assertEquals(location, actual.location());
        assertEquals(description, actual.description());
    }

    @Test
    void removeEvent() {
        UUID uuid = UUID.randomUUID();
        eventService.deleteEvent(uuid);
        verify(eventRepository).deleteById(uuid);
    }
}