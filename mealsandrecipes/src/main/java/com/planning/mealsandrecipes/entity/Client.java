package com.planning.mealsandrecipes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientID; // Unique identifier for the client
    private String name; // Name of the client

    // Constructors, getters, and setters
}
