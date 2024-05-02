package ee.laus.eventapp.participant.entity;

import ee.laus.eventapp.participant.Participant;
import ee.laus.eventapp.payment.PaymentType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor()
@DiscriminatorValue(value = "LEGAL_ENTITY")
public class LegalEntityParticipant extends Participant {
    private String registryCode;
    private String name;
    private Integer personCount;

    @Override
    public String getCode() {
        return getRegistryCode();
    }
}
