package com.example.demo.service;

import com.example.demo.entity.NumberEntity;
import com.example.demo.repository.NumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NumberService {

    @Autowired
    private NumberRepository numberRepository;

    public List<NumberEntity> findAll() {
        return numberRepository.findAll();
    }

    public Page<NumberEntity> findPage(Pageable pageable) {
        return numberRepository.findAll(pageable);
    }

    public Optional<NumberEntity> findById(Long id) {
        return numberRepository.findById(id);
    }

    public List<NumberEntity> findRandom(int limit) {
        return numberRepository.findRandomNumbers(limit);
    }

    public List<NumberEntity> findByIds(List<Long> ids) {
        return numberRepository.findAllById(ids);
    }

    public NumberEntity save(NumberEntity numberEntity) {
        return numberRepository.save(numberEntity);
    }

    public void delete(Long id) {
        numberRepository.deleteById(id);
    }

    public NumberEntity update(Long id, NumberEntity numberEntity) {
        numberEntity.setId(id);
        return numberRepository.save(numberEntity);
    }
}
