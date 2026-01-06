package com.gustavo.subscription.api.service;

import com.gustavo.subscription.api.entity.SubscriptionEntity;
import com.gustavo.subscription.api.enums.SubscriptionStatus;
import com.gustavo.subscription.api.exception.BusinessException;
import com.gustavo.subscription.api.exception.ResourceNotFoundException;
import com.gustavo.subscription.api.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    private SubscriptionRepository repository;

    public SubscriptionService(SubscriptionRepository repository) {
        this.repository = repository;
    }

    public SubscriptionEntity create(SubscriptionEntity subscription) {
        subscription.setStartDate(LocalDate.now());
        subscription.setStatus(SubscriptionStatus.ACTIVE);

        if (subscription.getAutoRenew() == null) {
            subscription.setAutoRenew(false);
        }

        return repository.save(subscription);
    }

    public List<SubscriptionEntity> findAll(Optional<SubscriptionStatus> status) {
        SubscriptionStatus statusToSearch =
                status.orElse(SubscriptionStatus.ACTIVE);

        return repository.findByStatus(statusToSearch);
    }

    public SubscriptionEntity findById(Long id) {
        SubscriptionEntity subscription = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found"));

        return subscription;
    }

    public SubscriptionEntity cancelSubscription(Long id) {
        SubscriptionEntity subscription = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found"));

        if (!SubscriptionStatus.ACTIVE.equals(subscription.getStatus())) {
            throw new BusinessException("Only ACTIVE subscriptions can be canceled");
        }

        subscription.setStatus(SubscriptionStatus.CANCELED);
        subscription.setAutoRenew(false);

        return repository.save(subscription);
    }
}
