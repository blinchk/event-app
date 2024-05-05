package ee.laus.eventapp.payment;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentTypeControllerTest {
    private PaymentTypeController paymentTypeController;
    @Mock
    private PaymentTypeRepository paymentTypeRepository;

    @BeforeEach
    void setUp() {
        paymentTypeController = new PaymentTypeController(paymentTypeRepository);
    }

    @Test
    void getPaymentTypes() {
        when(paymentTypeRepository.findAll()).thenReturn(
                List.of(new PaymentType(1L, "CASH", "Sularaha"))
        );
        paymentTypeController.getPaymentTypes();
        verify(paymentTypeRepository).findAll();
    }
}