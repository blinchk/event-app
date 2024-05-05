package ee.laus.eventapp.participant;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/participant")
@RequiredArgsConstructor
public class ParticipantController {
    private final ParticipantService participantService;

    @DeleteMapping("/{uuid}")
    public void deleteParticipant(@PathVariable UUID uuid) {
        participantService.deleteParticipant(uuid);
    }
}
