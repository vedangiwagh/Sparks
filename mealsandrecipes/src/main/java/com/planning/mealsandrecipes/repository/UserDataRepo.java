package com.planning.mealsandrecipes.repository;

import com.planning.mealsandrecipes.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDataRepo extends JpaRepository<UserData, String> {
    // No need for additional query methods, as findByUserEmail is inherited from JpaRepository
//    List<UserData> findByClientId( @Param("client_id") long client);

}
