package com.example.demo.service;

import com.example.demo.entity.LearningRecord;
import com.example.demo.repository.LearningRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearningRecordService {
    
    @Autowired
    private LearningRecordRepository learningRecordRepository;
    
    public List<LearningRecord> findByUserId(Long userId) {
        return learningRecordRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }
    
    public Optional<LearningRecord> findById(Long id) {
        return learningRecordRepository.findById(id);
    }
    
    public LearningRecord save(LearningRecord record) {
        return learningRecordRepository.save(record);
    }
    
    public void delete(Long id) {
        learningRecordRepository.deleteById(id);
    }
    
    public List<LearningRecord> findAll() {
        return learningRecordRepository.findAll();
    }

    public Page<LearningRecord> findPage(Pageable pageable) {
        return learningRecordRepository.findAll(pageable);
    }
}
