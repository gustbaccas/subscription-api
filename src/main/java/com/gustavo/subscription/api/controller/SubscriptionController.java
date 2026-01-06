package com.gustavo.subscription.api.controller;

import com.gustavo.subscription.api.dto.SubscriptionRequestDTO;
import com.gustavo.subscription.api.dto.SubscriptionResponseDTO;
import com.gustavo.subscription.api.entity.SubscriptionEntity;
import com.gustavo.subscription.api.enums.SubscriptionStatus;
import com.gustavo.subscription.api.service.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService service;

    public SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SubscriptionResponseDTO> create(@RequestBody @Valid SubscriptionRequestDTO dto) {

        SubscriptionEntity created = service.create(dto.toEntity());

        return ResponseEntity
                .status(201)
                .body(new SubscriptionResponseDTO(created));
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionResponseDTO>> findAll(
            @RequestParam(value = "status", required = false) SubscriptionStatus status) {
        List<SubscriptionEntity> subscriptions = service.findAll(Optional.ofNullable(status));

        List<SubscriptionResponseDTO> response = subscriptions
                .stream()
                .map(SubscriptionResponseDTO::new)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<SubscriptionResponseDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                new SubscriptionResponseDTO(service.findById(id)));
    }

    @PutMapping("{id}/cancel")
    public ResponseEntity<SubscriptionResponseDTO> cancelSubscription(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                new SubscriptionResponseDTO(service.cancelSubscription(id)));
    }

}
