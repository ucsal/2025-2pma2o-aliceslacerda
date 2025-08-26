package br.com.mariojp.solid.ocp;
import java.util.Map;
import java.util.Objects;

public class DiscountCalculator {
    private final Map<CustomerType, DiscountPolicy> policies;
    private static final DiscountPolicy NO_DISCOUNT = amount -> amount;

    public DiscountCalculator(Map<CustomerType, DiscountPolicy> policies){
        this.policies = Objects.requireNonNull(policies, "policies must not be null");
    }
        public double apply (double amount, CustomerType type){
            DiscountPolicy policy = policies.getOrDefault(type, NO_DISCOUNT);
        return policy.apply(amount);
    
    }
}
