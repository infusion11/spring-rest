package com.example.springrest.repository;

import com.example.springrest.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface StoryRepository extends JpaRepository<Story,Integer> {
    Optional<Story> findByTitle(String title);
    @Query(value = "SELECT * FROM story WHERE writer = :writerName", nativeQuery = true)
    List<Story> findByWriter(String writerName);
    @Query(value = "SELECT * FROM story WHERE is_vip = false", nativeQuery = true)
    List<Story> findAllNonVip();
    @Query(value = "SELECT * FROM story WHERE is_vip = true", nativeQuery = true)
    List<Story> findAllVip();
}
