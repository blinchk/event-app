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
public class PrivateEntityParticipant {
    @Id
    private String legalEntity;
    private String firstName;
    private String lastName;
    @OneToOne
    private Participant participant;
    @Length(max = 1500)
    private String details;
}
