package com.learnspring.repository;

import com.learnspring.dto.response.UserResponse;
import com.learnspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByUsername(String username);
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
