package com.events.dacapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.dacapi.models.Article;

public interface ArticleRepository extends JpaRepository<Article ,Long> {
	List<Article> findByVolumeId(long volumeId);
}
