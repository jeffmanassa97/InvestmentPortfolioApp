package com.example.MLS.repository;

import com.example.MLS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //@Query("SELECT u FROM Users u WHERE u.email = ?1")
    public User findByEmail(String email);
}
