package com.example.MLS.repository;

import com.example.MLS.entity.Mortgage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MortgageRepository extends JpaRepository<Mortgage, Long> {
}
