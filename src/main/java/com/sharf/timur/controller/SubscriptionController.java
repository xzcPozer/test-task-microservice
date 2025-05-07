package com.sharf.timur.controller;

import com.sharf.timur.dto.subscription.SubscriptionRequest;
import com.sharf.timur.dto.subscription.SubscriptionResponse;
import com.sharf.timur.dto.subscription.SubscriptionResponseForTop;
import com.sharf.timur.service.subscription.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}")
public class SubscriptionController {

    private final SubscriptionService service;

    @PostMapping("/users/{userId}/subscriptions")
    public ResponseEntity<Void> addSubForUser(
            @PathVariable Long userId,
            @RequestBody @Valid SubscriptionRequest request
    ) {
        log.info("sending request to add subscription: {} for user with id: {}", request.name(), userId);
        service.addSubscriptionToUser(request, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{userId}/subscriptions")
    public ResponseEntity<List<SubscriptionResponse>> getAllUserSubs(
            @PathVariable Long userId
    ) {
        log.info("sending request to find all subscription for user with id: {}", userId);
        return ResponseEntity.ok(service.getAllUserSubs(userId));
    }

    @DeleteMapping("/users/{userId}/subscriptions/{subId}")
    public ResponseEntity<List<SubscriptionResponse>> deleteSubFromUser(
            @PathVariable Long userId,
            @PathVariable Long subId
    ) {
        log.info("sending request to delete subscription with id: {} for user with id: {}", subId, userId);
        service.deleteSubByUserId(userId, subId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/subscriptions/top")
    public ResponseEntity<List<SubscriptionResponseForTop>> getTopThreeSubs() {
        log.info("sending request to find top 3 subscriptions");
        return ResponseEntity.ok(service.getTopThreePopularitySubs());
    }

}
