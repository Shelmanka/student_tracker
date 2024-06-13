package com.example.attendance_tracker.repository;

import com.example.attendance_tracker.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    @Transactional
    @Query("SELECT us FROM User us WHERE us.userName = :userName")
    Optional<User> findByName(@Param(value = "userName") String username);
}
