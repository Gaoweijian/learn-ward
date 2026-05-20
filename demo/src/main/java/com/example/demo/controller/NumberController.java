package com.example.demo.controller;

import com.example.demo.entity.NumberEntity;
import com.example.demo.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/number")
public class NumberController {
    
    @Autowired
    private NumberService numberService;
    
    @GetMapping
    public ResponseEntity<Page<NumberEntity>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(numberService.findPage(PageRequest.of(page, size)));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<NumberEntity> findById(@PathVariable Long id) {
        return numberService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<NumberEntity> create(@RequestBody NumberEntity numberEntity) {
        return ResponseEntity.ok(numberService.save(numberEntity));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<NumberEntity> update(@PathVariable Long id, @RequestBody NumberEntity numberEntity) {
        return ResponseEntity.ok(numberService.update(id, numberEntity));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        numberService.delete(id);
        return ResponseEntity.ok().build();
    }
}
