package com.sharf.timur.dto.subscription;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionResponseForTop {
    private String name;
    private Long count;
}
