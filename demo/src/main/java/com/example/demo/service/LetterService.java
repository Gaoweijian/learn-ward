package com.example.demo.service;

import com.example.demo.entity.Letter;
import com.example.demo.repository.LetterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LetterService {

    @Autowired
    private LetterRepository letterRepository;

    public List<Letter> findAll() {
        return letterRepository.findAll();
    }

    public Page<Letter> findPage(Pageable pageable) {
        return letterRepository.findAll(pageable);
    }

    public Optional<Letter> findById(Long id) {
        return letterRepository.findById(id);
    }

    public List<Letter> findRandom(int limit) {
        return letterRepository.findRandomLetters(limit);
    }

    public List<Letter> findByIds(List<Long> ids) {
        return letterRepository.findAllById(ids);
    }

    public Letter save(Letter letter) {
        return letterRepository.save(letter);
    }

    public void delete(Long id) {
        letterRepository.deleteById(id);
    }

    public Letter update(Long id, Letter letter) {
        letter.setId(id);
        return letterRepository.save(letter);
    }
}
