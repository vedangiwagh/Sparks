package com.planning.mealsandrecipes.repository;

import com.planning.mealsandrecipes.entity.Client;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface ClientRepo extends CrudRepository<Client, Integer> {
}
