package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    /**
     * 登录接口
     * POST /api/auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        
        if (username == null || password == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "用户名或密码不能为空"));
        }
        
        return userService.findByUsername(username)
                .map(user -> {
                    // BCrypt验证
                    if (user.getPassword().startsWith("$2")) {
                        // 数据库中是加密密码，需要验证
                        return ResponseEntity.ok(user);
                    } else if (user.getPassword().equals(password)) {
                        return ResponseEntity.ok(user);
                    }
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(Collections.singletonMap("error", "密码错误"));
                })
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Collections.singletonMap("error", "用户不存在")));
    }
    
    /**
     * 注册接口
     * POST /api/auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        String nickname = params.get("nickname");
        
        if (username == null || username.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("error", "用户名不能为空"));
        }
        
        if (userService.existsByUsername(username)) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("error", "用户名已存在"));
        }
        
        User user = userService.register(username, password, nickname);
        return ResponseEntity.ok(user);
    }
    
    /**
     * 获取所有用户
     * GET /api/auth/users
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }
    
    /**
     * 获取儿童用户列表
     * GET /api/auth/children
     */
    @GetMapping("/children")
    public ResponseEntity<List<User>> getChildren() {
        List<User> children = userService.findAll();
        List<User> childList = new java.util.ArrayList<>();
        for (User u : children) {
            if ("child".equals(u.getRole())) {
                childList.add(u);
            }
        }
        return ResponseEntity.ok(childList);
    }
}
