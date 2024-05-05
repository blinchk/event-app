package ee.laus.eventapp.participant;

import ee.laus.eventapp.participant.dto.LegalEntityParticipantDto;
import ee.laus.eventapp.participant.dto.PrivateEntityParticipantDto;
import ee.laus.eventapp.participant.response.ParticipantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/participant")
@RequiredArgsConstructor
public class ParticipantController {
    private final ParticipantService participantService;

    @GetMapping("/{uuid}")
    public ParticipantResponse getParticipant(@PathVariable UUID uuid) {
        return participantService.getParticipant(uuid);
    }

    @PostMapping("/{uuid}/legal-entity")
    public ParticipantResponse saveLegalEntityParticipant(@PathVariable UUID uuid, @RequestBody LegalEntityParticipantDto dto) {
        return participantService.saveParticipant(uuid, dto);
    }

    @PostMapping("/{uuid}/private-entity")
    public ParticipantResponse savePrivateEntityParticipant(@PathVariable UUID uuid, @RequestBody  PrivateEntityParticipantDto dto) {
        return participantService.saveParticipant(uuid, dto);
    }

    @DeleteMapping("/{uuid}")
    public void deleteParticipant(@PathVariable UUID uuid) {
        participantService.deleteParticipant(uuid);
    }
}
