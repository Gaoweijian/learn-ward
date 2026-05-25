package com.example.demo.repository;

import com.example.demo.entity.RichText;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RichTextRepository extends JpaRepository<RichText, Long> {

    /**
     * 按标题模糊搜索，支持分页
     */
    @Query("SELECT r FROM RichText r WHERE " +
           "LOWER(r.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(r.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<RichText> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
