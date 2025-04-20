// Entity (Model Layer) - A class mapping to a database - Represents database tables (e.g., @Entity).

package com.springrest.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity 			// so that hibernate can treat it as an entity, and can create table of this class (with same name) in database
public class Book {

	@Id 			// defining id as primary key of the table
	private long id;
	private String bookName;
	private String author;
	private int price;
	private String category;
	private String language;

	// default constructor - required by JPA
	public Book() {
	}

	// parameterized constructor - optional
	public Book(long id, String bookName, String author, int price, String category, String language) {
		this.id = id;
		this.bookName = bookName;
		this.author = author;
		this.price = price;
		this.category = category;
		this.language = language;
	}

	// getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
