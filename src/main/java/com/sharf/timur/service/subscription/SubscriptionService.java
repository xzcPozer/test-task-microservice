package com.sharf.timur.service.subscription;

import com.sharf.timur.dto.subscription.SubscriptionRequest;
import com.sharf.timur.dto.subscription.SubscriptionResponse;
import com.sharf.timur.dto.subscription.SubscriptionResponseForTop;

import java.util.List;

public interface SubscriptionService {
    void addSubscriptionToUser(SubscriptionRequest request, Long userId);
    List<SubscriptionResponse> getAllUserSubs(Long userId);
    void deleteSubByUserId(Long userId, Long subId);
    List<SubscriptionResponseForTop> getTopThreePopularitySubs();

}
