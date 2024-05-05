package ee.laus.eventapp.participant;

import ee.laus.eventapp.event.Event;
import ee.laus.eventapp.payment.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorColumn(name = "entity_type")
public abstract class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @ManyToOne
    private PaymentType paymentType;
    @ManyToOne
    private Event event;
    @Column(length = 5000)
    private String details;

    public abstract String getCode();
    public abstract String getName();
    public abstract String getEntityType();
}
