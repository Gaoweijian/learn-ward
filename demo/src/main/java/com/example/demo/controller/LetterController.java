package com.example.demo.controller;

import com.example.demo.entity.Letter;
import com.example.demo.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/letter")
public class LetterController {
    
    @Autowired
    private LetterService letterService;
    
    @GetMapping
    public ResponseEntity<Page<Letter>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(letterService.findPage(PageRequest.of(page, size)));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Letter> findById(@PathVariable Long id) {
        return letterService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Letter> create(@RequestBody Letter letter) {
        return ResponseEntity.ok(letterService.save(letter));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Letter> update(@PathVariable Long id, @RequestBody Letter letter) {
        return ResponseEntity.ok(letterService.update(id, letter));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        letterService.delete(id);
        return ResponseEntity.ok().build();
    }
}
