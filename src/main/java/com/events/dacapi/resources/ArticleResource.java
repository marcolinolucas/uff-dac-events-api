package com.events.dacapi.resources;

import java.util.List;

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
import com.events.dacapi.models.Author;
import com.events.dacapi.repository.ArticleRepository;
import com.events.dacapi.repository.AuthorRepository;

@RestController
@RequestMapping("/articles")
public class ArticleResource {
	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	ArticleRepository articleRepository;
	
	@GetMapping("{articleId}/authors")
	public List<Author> getAllAuthorsFromArticle(@PathVariable(value = "articleId") long articleId) {
		boolean existArticle = articleRepository.existsById(articleId);
		if (!existArticle)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This article not exist");

		return authorRepository.findByArticleIdOrderByArticleOrderAsc(articleId);
	}

	@GetMapping("{articleId}/authors/{authorId}")
	public Author getArticleFromVolume(@PathVariable(value = "articleId") long articleId,
			@PathVariable(value = "authorId") long authorId) {
		boolean existArticle = articleRepository.existsById(articleId);
		if (!existArticle)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This article not exist");

		boolean existAuthor = authorRepository.existsById(authorId);
		if (!existAuthor)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This author not exist");
		
		Article existingArticle = articleRepository.findById(articleId).get();
		
		Author existingAuthor = authorRepository.findById(authorId).get();
		
		if (!existingArticle.getAuthors().contains(existingAuthor)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This author not belongs to this article");
		}

		return existingAuthor;
	}
	
	@PostMapping("{articleId}/authors")
	public Author createAuthorForArticle(@PathVariable(value = "articleId") long articleId,
			@RequestBody Author author) {
		boolean existArticle = articleRepository.existsById(articleId);
		if (!existArticle)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This article not exist");

		Article existingArticle = articleRepository.findById(articleId).get();
		
		author.setArticle(existingArticle);

		return authorRepository.save(author);
	}
	
	@PutMapping("{articleId}/authors/{authorId}")
	public Author updateAuthorFromArticle(@PathVariable(value="articleId") long articleId, @PathVariable(value="authorId") long authorId, @RequestBody Author author) {
		boolean existArticle = articleRepository.existsById(articleId);
		if (!existArticle) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This article not exist");
		
		boolean existAuthor = authorRepository.existsById(authorId);
		if (!existAuthor) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This author not exist");
		
		Article articleAuthor = authorRepository.findById(authorId).get().getArticle();
		
		if (articleAuthor.getId() != articleId) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This author not belongs to this article");
		}

		author.setId(authorId);
		author.setArticle(articleAuthor);

		return authorRepository.save(author);
	}
	
	@DeleteMapping("{articleId}/authors/{authorId}")
	public void deleteAuthorFromArticle(@PathVariable(value="articleId") long articleId, @PathVariable(value="authorId") long authorId) {
		boolean existArticle = articleRepository.existsById(articleId);
		if (!existArticle) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This article not exist");
		
		boolean existAuthor = authorRepository.existsById(authorId);
		if (!existAuthor) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This author not exist");
		
		Article articleAuthor = authorRepository.findById(authorId).get().getArticle();
		
		if (articleAuthor.getId() != articleId) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This author not belongs to this article");
		}

		authorRepository.deleteById(authorId);

		return;
	}
}
