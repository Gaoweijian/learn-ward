package com.example.demo.service;

import com.example.demo.entity.RichText;
import com.example.demo.repository.RichTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RichTextService {

    @Autowired
    private RichTextRepository richTextRepository;

    public List<RichText> findAll() {
        return richTextRepository.findAll();
    }

    public Page<RichText> findPage(Pageable pageable) {
        return richTextRepository.findAll(pageable);
    }

    public Page<RichText> searchByKeyword(String keyword, Pageable pageable) {
        return richTextRepository.searchByKeyword(keyword, pageable);
    }

    public Optional<RichText> findById(Long id) {
        return richTextRepository.findById(id);
    }

    public RichText save(RichText richText) {
        return richTextRepository.save(richText);
    }

    public void delete(Long id) {
        richTextRepository.deleteById(id);
    }

    public RichText update(Long id, RichText richText) {
        richText.setId(id);
        return richTextRepository.save(richText);
    }
}
