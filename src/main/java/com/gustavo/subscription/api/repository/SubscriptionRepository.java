package com.gustavo.subscription.api.repository;

import com.gustavo.subscription.api.entity.SubscriptionEntity;
import com.gustavo.subscription.api.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {

    List<SubscriptionEntity> findByStatus(SubscriptionStatus status);
}
