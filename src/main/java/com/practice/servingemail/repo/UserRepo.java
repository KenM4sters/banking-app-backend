package com.practice.servingemail.repo;

import com.practice.servingemail.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmailIgnoreCase(String email);

    boolean existsByEmail(String email);

}
