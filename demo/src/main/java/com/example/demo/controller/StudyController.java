package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/study")
public class StudyController {
    
    @Autowired private HanziService hanziService;
    @Autowired private NumberService numberService;
    @Autowired private ArithmeticService arithmeticService;
    @Autowired private LetterService letterService;
    @Autowired private WordService wordService;
    @Autowired private LearningRecordService learningRecordService;
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * 获取随机题目
     * GET /api/study/{moduleType}?limit=10
     */
    @GetMapping("/{moduleType}")
    public ResponseEntity<?> getStudyQuestions(
            @PathVariable String moduleType,
            @RequestParam(defaultValue = "10") int limit) {
        
        limit = Math.min(Math.max(limit, 10), 20);
        
        switch (moduleType.toLowerCase()) {
            case "hanzi":
                return ResponseEntity.ok(hanziService.findRandom(limit));
            case "number":
                return ResponseEntity.ok(numberService.findRandom(limit));
            case "arithmetic":
                return ResponseEntity.ok(arithmeticService.findRandom(limit));
            case "letter":
                return ResponseEntity.ok(letterService.findRandom(limit));
            case "word":
                return ResponseEntity.ok(wordService.findRandom(limit));
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("error", "无效的模块类型"));
        }
    }

    /**
     * 获取题库列表（用于选题）
     * GET /api/study/library/{moduleType}
     */
    @GetMapping("/library/{moduleType}")
    public ResponseEntity<?> getQuestionLibrary(@PathVariable String moduleType) {
        switch (moduleType.toLowerCase()) {
            case "hanzi":
                return ResponseEntity.ok(hanziService.findAll());
            case "number":
                return ResponseEntity.ok(numberService.findAll());
            case "arithmetic":
                return ResponseEntity.ok(arithmeticService.findAll());
            case "letter":
                return ResponseEntity.ok(letterService.findAll());
            case "word":
                return ResponseEntity.ok(wordService.findAll());
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("error", "无效的模块类型"));
        }
    }

    /**
     * 根据ID列表获取题目
     * POST /api/study/custom
     * Body: { moduleType: "hanzi", ids: [1, 2, 3] }
     */
    @PostMapping("/custom")
    public ResponseEntity<?> getQuestionsByIds(@RequestBody Map<String, Object> request) {
        try {
            String moduleType = request.get("moduleType").toString();
            List<?> idList = (List<?>) request.get("ids");

            if (idList == null || idList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("error", "请选择题目"));
            }

            List<Long> ids = new ArrayList<>();
            for (Object id : idList) {
                ids.add(Long.valueOf(id.toString()));
            }

            switch (moduleType.toLowerCase()) {
                case "hanzi":
                    return ResponseEntity.ok(hanziService.findByIds(ids));
                case "number":
                    return ResponseEntity.ok(numberService.findByIds(ids));
                case "arithmetic":
                    return ResponseEntity.ok(arithmeticService.findByIds(ids));
                case "letter":
                    return ResponseEntity.ok(letterService.findByIds(ids));
                case "word":
                    return ResponseEntity.ok(wordService.findByIds(ids));
                default:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(Collections.singletonMap("error", "无效的模块类型"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }
    
    /**
     * 保存学习记录
     * POST /api/study/record
     */
    @PostMapping("/record")
    public ResponseEntity<?> saveRecord(@RequestBody Map<String, Object> request) {
        try {
            LearningRecord record = new LearningRecord();
            record.setUserId(Long.valueOf(request.get("userId").toString()));
            record.setModuleType(request.get("moduleType").toString());
            
            Object questionIdsObj = request.get("questionIds");
            if (questionIdsObj instanceof List) {
                record.setQuestionIds(objectMapper.writeValueAsString(questionIdsObj));
            }
            
            Object answersObj = request.get("answers");
            if (answersObj instanceof List) {
                record.setAnswers(objectMapper.writeValueAsString(answersObj));
            }
            
            record.setCorrectCount(Integer.valueOf(request.get("correctCount").toString()));
            record.setTotalCount(Integer.valueOf(request.get("totalCount").toString()));
            record.setScore(Integer.valueOf(request.get("score").toString()));
            
            if (request.containsKey("duration")) {
                record.setDuration(Integer.valueOf(request.get("duration").toString()));
            }
            
            LearningRecord saved = learningRecordService.save(record);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }
    
    /**
     * 获取用户学习历史
     * GET /api/study/history/{userId}
     */
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<LearningRecord>> getHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(learningRecordService.findByUserId(userId));
    }
    
    /**
     * 获取所有学习记录（管理员用）
     * GET /api/study/records
     */
    @GetMapping("/records")
    public ResponseEntity<Page<LearningRecord>> getAllRecords(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(learningRecordService.findPage(PageRequest.of(page, size)));
    }
}
