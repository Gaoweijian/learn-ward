package com.example.demo.controller;

import com.example.demo.entity.RichText;
import com.example.demo.service.RichTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/richtext")
public class RichTextController {

    @Autowired
    private RichTextService richTextService;

    @GetMapping
    public ResponseEntity<Page<RichText>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if (keyword != null && !keyword.trim().isEmpty()) {
            return ResponseEntity.ok(richTextService.searchByKeyword(keyword.trim(), pageRequest));
        }
        return ResponseEntity.ok(richTextService.findPage(pageRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RichText> findById(@PathVariable Long id) {
        return richTextService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RichText> create(@RequestBody RichText richText) {
        return ResponseEntity.ok(richTextService.save(richText));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RichText> update(@PathVariable Long id, @RequestBody RichText richText) {
        return ResponseEntity.ok(richTextService.update(id, richText));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        richTextService.delete(id);
        return ResponseEntity.ok().build();
    }
}
