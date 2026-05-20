package com.example.demo.controller;

import com.example.demo.entity.Word;
import com.example.demo.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/word")
public class WordController {
    
    @Autowired
    private WordService wordService;
    
    @GetMapping
    public ResponseEntity<Page<Word>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(wordService.findPage(PageRequest.of(page, size)));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Word> findById(@PathVariable Long id) {
        return wordService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Word> create(@RequestBody Word word) {
        return ResponseEntity.ok(wordService.save(word));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Word> update(@PathVariable Long id, @RequestBody Word word) {
        return ResponseEntity.ok(wordService.update(id, word));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        wordService.delete(id);
        return ResponseEntity.ok().build();
    }
}
