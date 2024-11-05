package com.PlayGroundAdv.Solar.repositories.users;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;


@Repository
public interface ActiveWebSocketUserRepository extends CrudRepository<AuthentificationEntity, String> {
    @Query("SELECT DISTINCT(u.email) FROM AuthentificationEntity u WHERE u.email != ?#{principal?.username}")
    List<String> findAllActiveUsers();
}
