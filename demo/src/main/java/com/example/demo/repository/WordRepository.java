package com.example.demo.repository;

import com.example.demo.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    
    @Query(value = "SELECT * FROM word ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Word> findRandomWords(@Param("limit") int limit);
    
    List<Word> findByCategory(String category);
}
