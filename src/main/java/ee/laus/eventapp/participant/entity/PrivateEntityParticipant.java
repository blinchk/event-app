package ee.laus.eventapp.participant.entity;

import ee.laus.eventapp.participant.Participant;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(value = "private")
public class PrivateEntityParticipant extends Participant {
    @Column(length = 11)
    @Length(min = 11, max = 11)
    private String personalCode;
    private String firstName;
    private String lastName;

    @Override
    public String getCode() {
        return getPersonalCode();
    }

    @Override
    public String getName() {
        return String.format("%s %s", getFirstName(), getLastName());
    }

    @Override
    public String getEntityType() {
        return "private";
    }
}
