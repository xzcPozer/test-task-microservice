package com.sharf.timur.repository;

import com.sharf.timur.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query(value =
            "SELECT s.name, COUNT(us.user_id) AS subscription_count " +
                    "FROM users_subscriptions us " +
                    "JOIN subscriptions s ON us.sub_id = s.id " +
                    "GROUP BY s.id, s.name " +
                    "ORDER BY subscription_count DESC " +
                    "LIMIT 3",
            nativeQuery = true)
    List<Object[]> findTopThreeSubsForAllTime();

    Subscription findByName(String name);
}
