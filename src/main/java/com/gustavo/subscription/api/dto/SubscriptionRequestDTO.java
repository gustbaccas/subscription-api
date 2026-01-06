package com.gustavo.subscription.api.dto;

import com.gustavo.subscription.api.entity.SubscriptionEntity;
import com.gustavo.subscription.api.enums.PlanType;

public class SubscriptionRequestDTO {

    private String customerName;
    private PlanType plan;
    private Boolean autoRenew;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public PlanType getPlan() {
        return plan;
    }

    public void setPlan(PlanType plan) {
        this.plan = plan;
    }

    public Boolean getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(Boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    public SubscriptionEntity toEntity(){
        SubscriptionEntity subscription = new SubscriptionEntity();

        subscription.setCustomerName(this.customerName);
        subscription.setPlan(this.plan);
        subscription.setAutoRenew(this.autoRenew);

        return subscription;
    }
}
