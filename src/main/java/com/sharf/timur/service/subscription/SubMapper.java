package com.sharf.timur.service.subscription;

import com.sharf.timur.domain.Subscription;
import com.sharf.timur.dto.subscription.SubscriptionResponse;
import com.sharf.timur.dto.subscription.SubscriptionResponseForTop;
import org.springframework.stereotype.Service;

@Service
public class SubMapper {

    SubscriptionResponse toResponse(Subscription sub){
        return SubscriptionResponse.builder()
                .name(sub.getName())
                .build();
    }

    SubscriptionResponseForTop toResponseForTop(Object[] subs){
        return SubscriptionResponseForTop.builder()
                .name((String) subs[0])
                .count((Long) subs[1])
                .build();
    }

}
