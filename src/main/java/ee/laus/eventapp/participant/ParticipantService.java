package ee.laus.eventapp.participant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantService participantService;
}
