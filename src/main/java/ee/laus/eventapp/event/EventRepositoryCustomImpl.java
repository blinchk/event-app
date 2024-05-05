package ee.laus.eventapp.event;

import ee.laus.eventapp.event.response.EventListItem;
import ee.laus.eventapp.event.search.EventSearchParams;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EventRepositoryCustomImpl implements EventRepositoryCustom {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<EventListItem> getEvents(EventSearchParams eventSearchParams) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EventListItem> query = criteriaBuilder.createQuery(EventListItem.class);
        Root<Event> root = query.from(Event.class);
        query = query.multiselect(
                root.get("uuid"),
                root.get("name"),
                root.get("time").as(LocalDate.class)
        );
        if (eventSearchParams.startDate() != null) {
            query = query.where(
                    criteriaBuilder.greaterThan(root.get("time"), eventSearchParams.startDate())
            );
        }
        if (eventSearchParams.endDate() != null) {
            query = query.where(
                    criteriaBuilder.lessThan(root.get("time"), eventSearchParams.endDate())
            );
        }
        return entityManager.createQuery(query).getResultList();
    }
}
