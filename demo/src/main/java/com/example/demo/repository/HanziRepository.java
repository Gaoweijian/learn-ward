package com.example.demo.repository;

import com.example.demo.entity.Hanzi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HanziRepository extends JpaRepository<Hanzi, Long> {
    
    @Query(value = "SELECT * FROM hanzi ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Hanzi> findRandomHanzi(@Param("limit") int limit);
    
    List<Hanzi> findByDifficulty(int difficulty);
    
    // 模糊搜索
    @Query("SELECT h FROM Hanzi h WHERE " +
           "h.hanzi LIKE %:keyword% OR " +
           "h.pinyin LIKE %:keyword% OR " +
           "h.meaning LIKE %:keyword%")
    Page<Hanzi> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
