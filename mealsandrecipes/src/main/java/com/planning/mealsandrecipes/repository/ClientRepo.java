package com.planning.mealsandrecipes.repository;

import com.planning.mealsandrecipes.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {

}
