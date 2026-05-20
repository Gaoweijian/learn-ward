package com.example.demo.repository;

import com.example.demo.entity.Arithmetic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArithmeticRepository extends JpaRepository<Arithmetic, Long> {
    
    @Query(value = "SELECT * FROM arithmetic ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Arithmetic> findRandomArithmetic(@Param("limit") int limit);
    
    List<Arithmetic> findByDifficulty(int difficulty);
}
