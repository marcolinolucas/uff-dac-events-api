package com.events.dacapi.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Author implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private int articleOrder;
	
	@Column(length = 256)
	private String email;
	
	@Column(length = 64)
	private String firstName;
	
	@Column(length = 64)
	private String middleName;
	
	@Column(length = 64)
	private String lastName;
	
	@Column(length = 256)
	private String affiliation;
	
	@Column(length = 256)
	private String englishAffiliation;
	
	@Column(length = 2)
	private String country;
	
	@Column(length = 19)
	private String orcID;
	
	@ManyToOne
	private Article article;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getArticleOrder() {
		return articleOrder;
	}

	public void setArticleOrder(int articleOrder) {
		this.articleOrder = articleOrder;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public String getEnglishAffiliation() {
		return englishAffiliation;
	}

	public void setEnglishAffiliation(String englishAffiliation) {
		this.englishAffiliation = englishAffiliation;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOrcID() {
		return orcID;
	}

	public void setOrcID(String orcID) {
		this.orcID = orcID;
	}
}
