package ee.laus.eventapp.participant;

import ee.laus.eventapp.participant.dto.LegalEntityParticipantDto;
import ee.laus.eventapp.participant.dto.PrivateEntityParticipantDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ParticipantControllerTest {
    ParticipantController participantController;
    @Mock
    ParticipantService participantService;

    @BeforeEach
    void setUp() {
        participantController = new ParticipantController(participantService);
    }

    @Test
    void getParticipant() {
        UUID uuid = UUID.randomUUID();
        participantController.getParticipant(uuid);
        verify(participantService).getParticipant(uuid);
    }

    @Test
    void saveLegalEntityParticipant() {
        UUID uuid = UUID.randomUUID();
        LegalEntityParticipantDto dto = new LegalEntityParticipantDto(
                "16481444",
                3,
                "Name OÜ",
                1L,
                null
        );
        participantController.saveLegalEntityParticipant(uuid, dto);
        verify(participantService).saveParticipant(uuid, dto);
    }

    @Test
    void savePrivateEntityParticipant() {
        UUID uuid = UUID.randomUUID();
        PrivateEntityParticipantDto dto = new PrivateEntityParticipantDto(
                "50309030254",
                "First Name",
                "Last Name",
                null,
                1L
        );
        participantController.savePrivateEntityParticipant(uuid, dto);
        verify(participantService).saveParticipant(uuid, dto);
    }

    @Test
    void deleteParticipant() {
        UUID uuid = UUID.randomUUID();
        participantController.deleteParticipant(uuid);
        verify(participantService).deleteParticipant(uuid);
    }
}