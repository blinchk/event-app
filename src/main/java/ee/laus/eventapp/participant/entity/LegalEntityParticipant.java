package ee.laus.eventapp.participant.entity;

import ee.laus.eventapp.participant.Participant;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor()
@DiscriminatorValue(value = "legal")
public class LegalEntityParticipant extends Participant {
    private String registryCode;
    private String name;
    private Integer personCount;

    @Override
    public String getCode() {
        return getRegistryCode();
    }

    @Override
    public String getEntityType() {
        return "legal";
    }
}
