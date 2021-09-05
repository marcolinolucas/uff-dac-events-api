package com.events.dacapi.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.events.dacapi.models.Article;
import com.events.dacapi.models.Volume;
import com.events.dacapi.repository.ArticleRepository;
import com.events.dacapi.repository.VolumeRepository;

@RestController
@RequestMapping("/volumes")
public class VolumeResource {

	@Autowired
	VolumeRepository volumeRepository;

	@Autowired
	ArticleRepository articleRepository;

	@GetMapping("")
	public List<Volume> getVolumes() {
		return volumeRepository.findAllByOrderByInitialsAscStartDateAsc();
	}

	@GetMapping("{volumeId}")
	public Optional<Volume> getVolume(@PathVariable(value = "volumeId") long volumeId) {
		return volumeRepository.findById(volumeId);
	}

	@PostMapping("")
	public Volume createVolume(@RequestBody Volume volume) {
		return volumeRepository.save(volume);
	}

	@PutMapping("{volumeId}")
	public Volume updateVolume(@PathVariable(value = "volumeId") long volumeId, @RequestBody Volume volume) {
		boolean existVolume = volumeRepository.existsById(volumeId);
		if (!existVolume)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This volume not exist");
		
		Volume existingVolume = volumeRepository.findById(volumeId).get();

		volume.setId(volumeId);
		volume.setArticles(existingVolume.getArticles());

		return volumeRepository.save(volume);
	}

	@DeleteMapping("{volumeId}")
	public void deleteVolume(@PathVariable(value = "volumeId") long volumeId) {
		boolean existVolume = volumeRepository.existsById(volumeId);
		if (!existVolume)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This volume not exist");

		volumeRepository.deleteById(volumeId);

		return;
	}

	@GetMapping("{volumeId}/articles")
	public List<Article> getAllArticlesFromVolume(@PathVariable(value = "volumeId") long volumeId) {
		boolean existVolume = volumeRepository.existsById(volumeId);
		if (!existVolume)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This volume not exist");

		return articleRepository.findByVolumeIdOrderByVolumeOrderAsc(volumeId);

	}

	@GetMapping("{volumeId}/articles/{articleId}")
	public Article getArticleFromVolume(@PathVariable(value = "volumeId") long volumeId,
			@PathVariable(value = "articleId") long articleId) {
		boolean existVolume = volumeRepository.existsById(volumeId);
		if (!existVolume)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This volume not exist");

		boolean existArticle = articleRepository.existsById(articleId);
		if (!existArticle)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This article not exist");

		Article existingArticle = articleRepository.findById(articleId).get();

		Volume articleVolume = existingArticle.getVolume();

		if (articleVolume.getId() != volumeId) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This article not belongs to this volume");
		}

		return existingArticle;

	}

	@PostMapping("{volumeId}/articles")
	public Article createArticleInVolume(@PathVariable(value = "volumeId") long volumeId,
			@RequestBody Article article) {
		boolean existVolume = volumeRepository.existsById(volumeId);
		if (!existVolume)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This volume not exist");

		Volume existingVolume = volumeRepository.findById(volumeId).get();

		article.setVolume(existingVolume);

		return articleRepository.save(article);
	}

	@PutMapping("{volumeId}/articles/{articleId}")
	public Article updateArticleFromVolume(@PathVariable(value="volumeId") long volumeId, @PathVariable(value="articleId") long articleId, @RequestBody Article article) {
		boolean existVolume = volumeRepository.existsById(volumeId);
		if (!existVolume) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This volume not exist");
		
		boolean existArticle = articleRepository.existsById(articleId);
		if (!existArticle) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This article not exist");
		
		Volume articleVolume = articleRepository.findById(articleId).get().getVolume();
		
		if (articleVolume.getId() != volumeId) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This article not belongs to this volume");
		}

		article.setId(articleId);
		article.setVolume(articleVolume);

		return articleRepository.save(article);
	}
	
	@DeleteMapping("{volumeId}/articles/{articleId}")
	public void deleteArticleFromVolume(@PathVariable(value="volumeId") long volumeId, @PathVariable(value="articleId") long articleId) {
		boolean existVolume = volumeRepository.existsById(volumeId);
		if (!existVolume) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This volume not exist");
		
		boolean existArticle = articleRepository.existsById(articleId);
		if (!existArticle) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This article not exist");
		
		Volume articleVolume = articleRepository.findById(articleId).get().getVolume();
		
		if (articleVolume.getId() != volumeId) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This article not belongs to this volume");
		}

		articleRepository.deleteById(articleId);

		return;
	}
}
