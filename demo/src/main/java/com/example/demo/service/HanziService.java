package com.example.demo.service;

import com.example.demo.entity.Hanzi;
import com.example.demo.repository.HanziRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HanziService {

    @Autowired
    private HanziRepository hanziRepository;

    public List<Hanzi> findAll() {
        return hanziRepository.findAll();
    }

    public Page<Hanzi> findPage(Pageable pageable) {
        return hanziRepository.findAll(pageable);
    }

    public Optional<Hanzi> findById(Long id) {
        return hanziRepository.findById(id);
    }

    public List<Hanzi> findRandom(int limit) {
        return hanziRepository.findRandomHanzi(limit);
    }

    public List<Hanzi> findByIds(List<Long> ids) {
        return hanziRepository.findAllById(ids);
    }

    public Hanzi save(Hanzi hanzi) {
        return hanziRepository.save(hanzi);
    }

    public void delete(Long id) {
        hanziRepository.deleteById(id);
    }

    public Hanzi update(Long id, Hanzi hanzi) {
        hanzi.setId(id);
        return hanziRepository.save(hanzi);
    }
    
    public Page<Hanzi> searchByKeyword(String keyword, Pageable pageable) {
        return hanziRepository.searchByKeyword(keyword, pageable);
    }
}
