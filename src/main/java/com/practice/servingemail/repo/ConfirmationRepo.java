package com.practice.servingemail.repo;

import com.practice.servingemail.domain.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationRepo extends JpaRepository<Confirmation, Long> {
    Confirmation findByToken(String token);

}
