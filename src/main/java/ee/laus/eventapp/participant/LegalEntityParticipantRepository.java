package ee.laus.eventapp.participant;

import ee.laus.eventapp.participant.entity.LegalEntityParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface LegalEntityParticipantRepository extends JpaRepository<LegalEntityParticipant, UUID> {
}
