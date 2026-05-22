package com.example.demo.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "letter")
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "letter_upper", nullable = false, length = 1)
    private String letterUpper;

    @Column(name = "letter_lower", nullable = false, length = 1)
    private String letterLower;

    @Column(name = "word_example", nullable = false, length = 50)
    private String wordExample;

    @Column(name = "word_meaning", length = 100)
    private String wordMeaning;

    @Column(length = 255)
    private String imageUrl;

    @Column(length = 20)
    private String phonetic;

    @Column
    private Integer difficulty;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Letter() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLetterUpper() {
        return letterUpper;
    }

    public void setLetterUpper(String letterUpper) {
        this.letterUpper = letterUpper;
    }

    public String getLetterLower() {
        return letterLower;
    }

    public void setLetterLower(String letterLower) {
        this.letterLower = letterLower;
    }

    public String getWordExample() {
        return wordExample;
    }

    public void setWordExample(String wordExample) {
        this.wordExample = wordExample;
    }

    public String getWordMeaning() {
        return wordMeaning;
    }

    public void setWordMeaning(String wordMeaning) {
        this.wordMeaning = wordMeaning;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Letter{id=" + id + ", upper='" + letterUpper + "', lower='" + letterLower + "'}";
    }
}
