package com.events.dacapi.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private int volumeOrder;
	
	@Column(length = 2)
	private String language;
	
	@Column(length = 256)
	private String originalTitle;
	
	@Column(length = 256)
	private String englishTitle;
	
	@Column(length = 2048)
	private String originalSummary;
	
	@Column(length = 2048)
	private String englishSummary;
	
	@Column(length = 256)
	private String originalKeywords;
	
	@Column(length = 256)
	private String englishKeywords;
	
	private int numberOfPages;
	
	@OneToMany(mappedBy="article", cascade = CascadeType.ALL)
	@OrderBy("articleOrder")
	private List<Author> authors;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Volume volume;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVolumeOrder() {
		return volumeOrder;
	}

	public void setVolumeOrder(int volumeOrder) {
		this.volumeOrder = volumeOrder;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getEnglishTitle() {
		return englishTitle;
	}

	public void setEnglishTitle(String englishTitle) {
		this.englishTitle = englishTitle;
	}

	public String getOriginalSummary() {
		return originalSummary;
	}

	public void setOriginalSummary(String originalSummary) {
		this.originalSummary = originalSummary;
	}

	public String getEnglishSummary() {
		return englishSummary;
	}

	public void setEnglishSummary(String englishSummary) {
		this.englishSummary = englishSummary;
	}

	public String getOriginalKeywords() {
		return originalKeywords;
	}

	public void setOriginalKeywords(String originalKeywords) {
		this.originalKeywords = originalKeywords;
	}

	public String getEnglishKeywords() {
		return englishKeywords;
	}

	public void setEnglishKeywords(String englishKeywords) {
		this.englishKeywords = englishKeywords;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	public Volume getVolume() {
		return volume;
	}

	public void setVolume(Volume volume) {
		this.volume = volume;
	}
}
