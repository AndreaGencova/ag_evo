package com.ag_evo.repository;

import com.ag_evo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query(value = "SELECT u.* FROM users u WHERE CONCAT(SUBSTRING(first_name for 1), last_name) LIKE :name LIMIT 1", nativeQuery = true)
    Users findUsersByName(@Param(value = "name") String name);
}
