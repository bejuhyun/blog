package com.mori.blog.repository;

import com.mori.blog.model.board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<board, Long> {

    Page<board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
