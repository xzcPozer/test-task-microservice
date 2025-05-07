package com.sharf.timur.service.subscription;

import com.sharf.timur.domain.Subscription;
import com.sharf.timur.domain.User;
import com.sharf.timur.dto.subscription.SubscriptionRequest;
import com.sharf.timur.dto.subscription.SubscriptionResponse;
import com.sharf.timur.dto.subscription.SubscriptionResponseForTop;
import com.sharf.timur.exception.AlreadyExistException;
import com.sharf.timur.exception.ResourceNotFoundException;
import com.sharf.timur.repository.SubscriptionRepository;
import com.sharf.timur.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subRepository;
    private final UserRepository userRepository;
    private final SubMapper mapper;

    @Override
    public void addSubscriptionToUser(SubscriptionRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user with id:" + userId + " hasn't found"));

        Subscription sub = Optional.ofNullable(subRepository.findByName(request.name()))
                .orElseThrow(() -> new ResourceNotFoundException("subscription with name:" + request.name() + " hasn't found"));

        // optional check
        if (user.getSubscriptions().stream().anyMatch(s -> s.getName().equals(sub.getName()))) {
            throw new AlreadyExistException("subscription" + sub.getName() + " already exists for user " + userId);
        }

        user.addSubscription(sub);
        sub.addUser(user);

        userRepository.save(user);
        subRepository.save(sub);
    }


    @Override
    public List<SubscriptionResponse> getAllUserSubs(Long userId) {
        List<Subscription> userSubs = userRepository.findById(userId)
                .map(User::getSubscriptions)
                .map(List::copyOf)
                .orElseThrow(() -> new ResourceNotFoundException("user with id:" + userId + " has no subscriptions"));

        return userSubs.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSubByUserId(Long userId, Long subId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user with id:" + userId + " hasn't found"));

        Subscription sub = subRepository.findById(subId)
                .orElseThrow(() -> new ResourceNotFoundException("subscription with id:" + subId + " hasn't found"));

        // optional check
        if (user.getSubscriptions().stream().noneMatch(s -> s.getName().equals(sub.getName()))) {
            throw new AlreadyExistException("subscription" + sub.getName() + " hasn't exists for user " + userId);
        }

        user.removeSubscription(sub);
        sub.removeUser(user);

        userRepository.save(user);
        subRepository.save(sub);
    }

    @Override
    public List<SubscriptionResponseForTop> getTopThreePopularitySubs() {
        List<Object[]> sub = Optional.ofNullable(subRepository.findTopThreeSubsForAllTime())
                .orElseThrow(() -> new EntityNotFoundException("there are no subscriptions connected yet"));

        return sub.stream()
                .map(mapper::toResponseForTop)
                .collect(Collectors.toList());
    }
}
