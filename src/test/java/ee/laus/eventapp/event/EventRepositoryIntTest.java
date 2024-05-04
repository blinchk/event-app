package ee.laus.eventapp.event;

import ee.laus.eventapp.EventApplication;
import ee.laus.eventapp.LocalPostgreSQLContainer;
import ee.laus.eventapp.event.response.EventListItem;
import ee.laus.eventapp.event.search.EventSearchParams;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(classes = { EventApplication.class })
public class EventRepositoryIntTest {
    @ClassRule
    public static PostgreSQLContainer<LocalPostgreSQLContainer> postgreSQLContainer = LocalPostgreSQLContainer.getInstance();
    @Autowired
    private EventRepository eventRepository;

    @BeforeAll
    static void beforeAll() {
        postgreSQLContainer.start();
    }

    @BeforeEach
    void setUp() {
        eventRepository.deleteAll();
    }

    @Test
    void getEventsUntilEndDate() {
        Event event = new Event(
                UUID.randomUUID(),
                "Event Name",
                LocalDateTime.now().plusDays(3L),
                "Location",
                "Description"
        );
        eventRepository.save(event);
        EventSearchParams searchParams = new EventSearchParams(null, LocalDate.now().plusDays(4L));
        List<EventListItem> events = eventRepository.getEvents(searchParams);
        Long expectedCount = 1L;
        assertEquals(expectedCount, events.size());
    }

    @Test
    void getEventsAfterStartDate() {
        Event event = new Event(
                UUID.randomUUID(),
                "Event Name",
                LocalDateTime.now().plusDays(5L),
                "Location",
                "Description"
        );
        eventRepository.save(event);
        EventSearchParams searchParams = new EventSearchParams(LocalDate.now().plusDays(4L), null);
        List<EventListItem> events = eventRepository.getEvents(searchParams);
        Long expectedCount = 1L;
        assertEquals(expectedCount, events.size());
    }
}
