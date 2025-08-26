package br.com.mariojp.solid.ocp;

import org.junit.jupiter.api.Test;

import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class DiscountCalculatorTest {

    @Test
    void partner_gets_12_percent() {
        DiscountCalculator calculator = new DiscountCalculator(Map.of(
            CustomerType.PARTNER, new PartnerPolicy()

        ));
        double result = calculator.apply(100.0, CustomerType.PARTNER);
        assertEquals(88.0, calculator.apply(100.0, CustomerType.PARTNER), 0.0001,
            "PARTNER deveria ter 12% de desconto");
    }
    @Test
    void calculatorAcceptsInhectedPolicies(){
    DiscountCalculator calculator = new DiscountCalculator(Map.of(
        CustomerType.REGULAR, new RegularPolicy(),
        CustomerType.PREMIUM, new PremiumPolicy()

    ));

    assertEquals(95.0, calculator.apply(100.0, CustomerType.REGULAR), 0.0001);
    assertEquals(90.0, calculator.apply(100.0, CustomerType.PREMIUM), 0.0001);
}

@Test
void unknowTypeUsesNoDiscountByDeFault(){
    DiscountCalculator calculator = new DiscountCalculator(Map.of(
        CustomerType.REGULAR, new RegularPolicy()
        ));
        assertEquals(100.0, calculator.apply(100.0, CustomerType.PREMIUM), 0.0001);

}

@Test
void supportsCustomPolicyViaLambda(){
    DiscountCalculator calculator = new DiscountCalculator(Map.of(
        CustomerType.REGULAR, (amount) -> amount*0.5
        ));
        assertEquals(50.0, calculator.apply(100.0, CustomerType.REGULAR), 0.0001);

}
}