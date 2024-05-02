package ee.laus.eventapp.payment;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment-type")
@AllArgsConstructor
public class PaymentTypeController {
    private final PaymentTypeRepository paymentTypeRepository;

    @GetMapping
    public List<PaymentType> getPaymentTypes() {
        return paymentTypeRepository.findAll();
    }
}
