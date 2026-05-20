package com.example.demo.service;

import com.example.demo.entity.Word;
import com.example.demo.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    public List<Word> findAll() {
        return wordRepository.findAll();
    }

    public Page<Word> findPage(Pageable pageable) {
        return wordRepository.findAll(pageable);
    }

    public Optional<Word> findById(Long id) {
        return wordRepository.findById(id);
    }

    public List<Word> findRandom(int limit) {
        return wordRepository.findRandomWords(limit);
    }

    public List<Word> findByIds(List<Long> ids) {
        return wordRepository.findAllById(ids);
    }

    public Word save(Word word) {
        return wordRepository.save(word);
    }

    public void delete(Long id) {
        wordRepository.deleteById(id);
    }

    public Word update(Long id, Word word) {
        word.setId(id);
        return wordRepository.save(word);
    }
}
