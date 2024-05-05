package ee.laus.eventapp.participant;

import ee.laus.eventapp.participant.response.EventParticipantListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, UUID> {
    @Query(value = """
    select
        p.uuid as uuid,
        case
            when entity_type = 'legal' then p.name
            when entity_type = 'private' then concat(p.first_name, ' ', p.last_name)
        end name,
        case
            when entity_type = 'legal' then p.registry_code
            when entity_type = 'private' then p.personal_code
        end code
    from participant p
    left join event e on p.event_uuid = e.uuid
    where e.uuid = :event_uuid
    order by code desc
    """, nativeQuery = true)
    List<EventParticipantListItem> getEventParticipantsByEventUuid(@Param(value = "event_uuid") UUID eventUuid);
    void deleteAllByEventUuid(UUID eventUuid);
}
