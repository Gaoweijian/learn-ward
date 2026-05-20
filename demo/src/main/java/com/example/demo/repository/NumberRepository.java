package com.example.demo.repository;

import com.example.demo.entity.NumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NumberRepository extends JpaRepository<NumberEntity, Long> {
    
    @Query(value = "SELECT * FROM `number` ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<NumberEntity> findRandomNumbers(@Param("limit") int limit);
}
