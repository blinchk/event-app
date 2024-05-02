package ee.laus.eventapp.event;

import ee.laus.eventapp.event.response.EventListItem;
import ee.laus.eventapp.event.search.EventSearchParams;

import java.util.List;

public interface EventRepositoryCustom {
    List<EventListItem> getEvents(EventSearchParams eventSearchParams);
}
