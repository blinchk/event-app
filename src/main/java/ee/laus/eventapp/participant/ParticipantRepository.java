package ee.laus.eventapp.participant;

import ee.laus.eventapp.participant.response.EventParticipantResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, UUID> {
    @Query(value =
    """
    select
        case
            when entity_type = 'LEGAL_ENTITY' then p.name
            when entity_type = 'PRIVATE_ENTITY' then concat(p.first_name, ' ', p.last_name)
        end name,
        case
            when entity_type = 'LEGAL_ENTITY' then p.registry_code
            when entity_type = 'PRIVATE_ENTITY' then p.personal_code
        end code
    from participant p
    left join event e on p.event_uuid = e.uuid
    where e.uuid = :event_uuid
    order by code desc
    """, nativeQuery = true)
    List<EventParticipantResponse> getEventParticipantsByEventUuid(@Param(value = "event_uuid") UUID eventUuid);
}
