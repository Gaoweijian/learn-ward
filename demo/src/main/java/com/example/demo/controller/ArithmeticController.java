package com.example.demo.controller;

import com.example.demo.entity.Arithmetic;
import com.example.demo.service.ArithmeticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/arithmetic")
public class ArithmeticController {
    
    @Autowired
    private ArithmeticService arithmeticService;
    
    @GetMapping
    public ResponseEntity<Page<Arithmetic>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(arithmeticService.findPage(PageRequest.of(page, size)));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Arithmetic> findById(@PathVariable Long id) {
        return arithmeticService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Arithmetic> create(@RequestBody Arithmetic arithmetic) {
        return ResponseEntity.ok(arithmeticService.save(arithmetic));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Arithmetic> update(@PathVariable Long id, @RequestBody Arithmetic arithmetic) {
        return ResponseEntity.ok(arithmeticService.update(id, arithmetic));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        arithmeticService.delete(id);
        return ResponseEntity.ok().build();
    }
}
