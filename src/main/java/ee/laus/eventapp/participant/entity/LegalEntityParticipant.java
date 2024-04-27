package ee.laus.eventapp.participant.entity;

import ee.laus.eventapp.participant.Participant;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LegalEntityParticipant {
    @Id
    private String registryCode;
    @OneToOne
    private Participant participant;
    private Integer personCount;
    @Length(max = 5000)
    private String details;
}
