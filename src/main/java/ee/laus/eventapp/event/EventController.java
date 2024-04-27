package ee.laus.eventapp.event;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    public void getEvent() {

    }
}
