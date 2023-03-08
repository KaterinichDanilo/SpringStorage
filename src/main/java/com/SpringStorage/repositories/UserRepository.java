package com.SpringStorage.repositories;

import com.SpringStorage.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
    User findUserById(Long id);
}
