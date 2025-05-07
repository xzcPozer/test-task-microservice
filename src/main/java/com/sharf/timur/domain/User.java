package com.sharf.timur.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = true)
    private String surName;
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    @Column(unique = true, nullable = false)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_subscriptions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "sub_id")
    )
    private Set<Subscription> subscriptions = new HashSet<>();

    public void addSubscription(Subscription subscription){
        this.subscriptions.add(subscription);
    }
    public void removeSubscription(Subscription subscription){
        this.subscriptions.remove(subscription);
    }
}
