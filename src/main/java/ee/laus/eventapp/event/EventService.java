package ee.laus.eventapp.event;

import ee.laus.eventapp.event.response.EventListItem;
import ee.laus.eventapp.event.response.EventResponse;
import ee.laus.eventapp.event.search.EventSearchParams;
import ee.laus.eventapp.exception.EntityNotFoundException;
import ee.laus.eventapp.participant.ParticipantService;
import ee.laus.eventapp.participant.response.EventParticipantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final ParticipantService participantService;

    public EventResponse getEvent(UUID uuid) {
        Event event = eventRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        List<EventParticipantResponse> participants = participantService.getEventParticipants(uuid);
        return new EventResponse(
                event.getUuid(),
                event.getName(),
                event.getTime(),
                event.getLocation(),
                event.getDescription(),
                participants
        );
    }

    public List<EventListItem> getEvents(EventSearchParams searchParams) {
        return eventRepository.getEvents(searchParams);
    }
}
