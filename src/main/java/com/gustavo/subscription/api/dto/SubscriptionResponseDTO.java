package com.gustavo.subscription.api.dto;

import com.gustavo.subscription.api.entity.SubscriptionEntity;
import com.gustavo.subscription.api.enums.PlanType;
import com.gustavo.subscription.api.enums.SubscriptionStatus;

import java.time.LocalDate;

public class SubscriptionResponseDTO {

    private Long id;
    private String customerName;
    private PlanType plan;
    private SubscriptionStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean autoRenew;

    public SubscriptionResponseDTO(SubscriptionEntity subscription) {
        this.id = subscription.getId();
        this.customerName = subscription.getCustomerName();
        this.plan = subscription.getPlan();
        this.status = subscription.getStatus();
        this.startDate = subscription.getStartDate();
        this.endDate = subscription.getEndDate();
        this.autoRenew = subscription.getAutoRenew();

        if (subscription.getStatus() == SubscriptionStatus.ACTIVE
                && subscription.getEndDate() != null
                && subscription.getEndDate().isBefore(LocalDate.now())) {

            this.status = SubscriptionStatus.EXPIRED;
        } else {
            this.status = subscription.getStatus();
        }
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public PlanType getPlan() {
        return plan;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Boolean getAutoRenew() {
        return autoRenew;
    }
}
