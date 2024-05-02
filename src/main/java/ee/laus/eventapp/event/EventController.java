package ee.laus.eventapp.event;

import ee.laus.eventapp.event.response.EventListItem;
import ee.laus.eventapp.event.response.EventResponse;
import ee.laus.eventapp.event.search.EventSearchParams;
import ee.laus.eventapp.participant.ParticipantService;
import ee.laus.eventapp.participant.dto.LegalEntityParticipantDto;
import ee.laus.eventapp.participant.dto.PrivateEntityParticipantDto;
import ee.laus.eventapp.participant.response.EventParticipantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final ParticipantService participantService;
    private final EventService eventService;

    @GetMapping
    public List<EventListItem> getEvents(EventSearchParams searchParams) {
        return eventService.getEvents(searchParams);
    }

    @GetMapping("/{uuid}")
    public EventResponse getEvent(@PathVariable UUID uuid) {
        return eventService.getEvent(uuid);
    }

    @PostMapping("/{uuid}/participant/legal-entity")
    public EventParticipantResponse addParticipant(@PathVariable UUID uuid, @RequestBody LegalEntityParticipantDto eventParticipant) {
        return participantService.addEventParticipant(uuid, eventParticipant);
    }

    @PostMapping("/{uuid}/participant/private-entity")
    public EventParticipantResponse addParticipant(@PathVariable UUID uuid, @RequestBody PrivateEntityParticipantDto eventParticipant) {
        return participantService.addEventParticipant(uuid, eventParticipant);
    }
}
