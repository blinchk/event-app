package ee.laus.eventapp.participant;

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
    void deleteParticipant() {
        UUID uuid = UUID.randomUUID();
        participantController.deleteParticipant(uuid);
        verify(participantService).deleteParticipant(uuid);
    }
}