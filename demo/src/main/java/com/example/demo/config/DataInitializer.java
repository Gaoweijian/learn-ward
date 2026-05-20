package com.example.demo.config;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) {
        // 初始化默认用户（如果不存在）
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("admin");
            admin.setNickname("管理员");
            userRepository.save(admin);
        }
        
        if (!userRepository.existsByUsername("child1")) {
            User child1 = new User();
            child1.setUsername("child1");
            child1.setPassword(passwordEncoder.encode("123456"));
            child1.setRole("child");
            child1.setNickname("小明");
            userRepository.save(child1);
        }
        
        if (!userRepository.existsByUsername("child2")) {
            User child2 = new User();
            child2.setUsername("child2");
            child2.setPassword(passwordEncoder.encode("123456"));
            child2.setRole("child");
            child2.setNickname("小红");
            userRepository.save(child2);
        }
        
        System.out.println("默认用户初始化完成！");
        System.out.println("管理员账号: admin / admin123");
        System.out.println("儿童账号: child1 / 123456, child2 / 123456");
    }
}
