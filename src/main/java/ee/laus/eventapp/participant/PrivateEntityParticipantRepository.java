package ee.laus.eventapp.participant;

import ee.laus.eventapp.participant.entity.PrivateEntityParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrivateEntityParticipantRepository extends JpaRepository<PrivateEntityParticipant, UUID> {
}
