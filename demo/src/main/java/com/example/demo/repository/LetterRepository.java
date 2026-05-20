package com.example.demo.repository;

import com.example.demo.entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LetterRepository extends JpaRepository<Letter, Long> {
    
    @Query(value = "SELECT * FROM letter ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Letter> findRandomLetters(@Param("limit") int limit);
}
