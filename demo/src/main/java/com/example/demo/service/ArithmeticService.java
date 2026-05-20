package com.example.demo.service;

import com.example.demo.entity.Arithmetic;
import com.example.demo.repository.ArithmeticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArithmeticService {

    @Autowired
    private ArithmeticRepository arithmeticRepository;

    public List<Arithmetic> findAll() {
        return arithmeticRepository.findAll();
    }

    public Page<Arithmetic> findPage(Pageable pageable) {
        return arithmeticRepository.findAll(pageable);
    }

    public Optional<Arithmetic> findById(Long id) {
        return arithmeticRepository.findById(id);
    }

    public List<Arithmetic> findRandom(int limit) {
        return arithmeticRepository.findRandomArithmetic(limit);
    }

    public List<Arithmetic> findByIds(List<Long> ids) {
        return arithmeticRepository.findAllById(ids);
    }

    public Arithmetic save(Arithmetic arithmetic) {
        return arithmeticRepository.save(arithmetic);
    }

    public void delete(Long id) {
        arithmeticRepository.deleteById(id);
    }

    public Arithmetic update(Long id, Arithmetic arithmetic) {
        arithmetic.setId(id);
        return arithmeticRepository.save(arithmetic);
    }
}
