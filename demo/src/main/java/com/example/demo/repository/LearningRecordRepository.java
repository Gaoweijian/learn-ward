package com.example.demo.repository;

import com.example.demo.entity.LearningRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearningRecordRepository extends JpaRepository<LearningRecord, Long> {
    List<LearningRecord> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<LearningRecord> findByModuleTypeOrderByCreatedAtDesc(String moduleType);
}
