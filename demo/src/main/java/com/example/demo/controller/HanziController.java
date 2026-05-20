package com.example.demo.controller;

import com.example.demo.entity.Hanzi;
import com.example.demo.service.HanziService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/hanzi")
public class HanziController {
    
    @Autowired
    private HanziService hanziService;
    
    @GetMapping
    public ResponseEntity<Page<Hanzi>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(hanziService.findPage(PageRequest.of(page, size)));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Hanzi> findById(@PathVariable Long id) {
        return hanziService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Hanzi> create(@RequestBody Hanzi hanzi) {
        return ResponseEntity.ok(hanziService.save(hanzi));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Hanzi> update(@PathVariable Long id, @RequestBody Hanzi hanzi) {
        return ResponseEntity.ok(hanziService.update(id, hanzi));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hanziService.delete(id);
        return ResponseEntity.ok().build();
    }
    
    // 汉字卡片展示接口 - 支持搜索和分页，每页18个（6列x3行）
    @GetMapping("/cards")
    public ResponseEntity<Page<Hanzi>> getCards(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(hanziService.searchByKeyword(keyword != null ? keyword : "", PageRequest.of(page, 12)));
    }
}
