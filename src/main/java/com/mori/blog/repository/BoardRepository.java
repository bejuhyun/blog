package com.mori.blog.repository;

import com.mori.blog.model.board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<board, Long> {
}
