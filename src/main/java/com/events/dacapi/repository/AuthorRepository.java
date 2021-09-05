package com.events.dacapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.dacapi.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{
	public List<Author> findByArticleIdOrderByArticleOrderAsc(long articleId);
}
