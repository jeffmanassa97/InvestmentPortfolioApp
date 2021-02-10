package com.example.MLS.repository;

import com.example.MLS.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository<Image, Long> {
}
