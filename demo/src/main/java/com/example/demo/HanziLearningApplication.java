package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class HanziLearningApplication {

	@Value("${server.port}")
	private int serverPort;

	public static void main(String[] args) {
		SpringApplication.run(HanziLearningApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		String localhost = "http://localhost:" + serverPort;
		System.out.println("\n========================================");
		System.out.println("  应用启动成功！");
		System.out.println("  访问地址: " + localhost + "/");
		System.out.println("  管理后台: " + localhost + "/admin.html");
		System.out.println("  书籍列表: " + localhost + "/books.html");
		System.out.println("  汉字卡片: " + localhost + "/hanzi-cards.html");
		System.out.println("========================================\n");
	}
}
