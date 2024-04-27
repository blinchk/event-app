package ee.laus.eventapp.participant;

import ee.laus.eventapp.event.Event;
import ee.laus.eventapp.participant.entity.EntityType;
import ee.laus.eventapp.participant.payment.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private EntityType entityType;
    private PaymentType paymentType;
    @ManyToOne
    private Event event;
}
