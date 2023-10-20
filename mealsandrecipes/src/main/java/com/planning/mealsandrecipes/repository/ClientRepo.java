package com.planning.mealsandrecipes.repository;

import com.planning.mealsandrecipes.entity.Client;

import org.springframework.data.repository.CrudRepository;

// ClientRepo is a repository interface for the Client entity.
// It extends the CrudRepository to provide basic CRUD operations for clients.
public interface ClientRepo extends CrudRepository<Client, Integer> {
}
