// Controller (Web Layer) - Handles HTTP requests (e.g., @RestController).

package com.springrest.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.library.entity.Book;
import com.springrest.library.service.BookService;


@RestController
/* @RestController is not mandatory but highly recommended when building REST APIs in Spring Boot.
 * Alternative to Work Without @RestController:
   * If you use @Controller instead, Spring still detects the class as a controller, but you'll need to manually add @ResponseBody to each method.
   *  Problem: If you forget @ResponseBody, Spring tries to return a view name instead of JSON.
   *  Solution: No need for @ResponseBody. The response is automatically converted to JSON.
   *  Best Practice: Always use @RestController for REST APIs.
 * Benefits of @RestController:
   * Combines @Controller + @ResponseBody
   * Every method returns JSON by default, so you don’t need to add @ResponseBody manually.
   * Improves Code Readability
*/
public class LibraryController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/")
	public String homePage() {
		return "Welcome to Library Management System";
	}
	
	@GetMapping("/books")
	public List<Book> getAll() {
		return bookService.getAll();
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getById(@PathVariable Long id) {
		try {
			Book resultedBook = bookService.getById(id);
			return new ResponseEntity<Book>(resultedBook, HttpStatus.OK);	// 200
		}
		catch(RuntimeException e) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);			// Return 404 if book id doesn't exist
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatusCode.valueOf(500));		// 500
		}
//		Why Long instead of long?
//		If id is missing or invalid, long (primitive) can't be null, so it may cause errors.
//		Long allows handling null values safely.
	}
	
	@PostMapping("/books")
	public ResponseEntity<Book> insert(@RequestBody Book book) {
		Book insertResult = bookService.insert(book);
		if(insertResult != null)
			return new ResponseEntity<>(insertResult, HttpStatus.OK);			// 200
		else
			return new ResponseEntity<>(HttpStatus.CONFLICT);					// 409
	}
	
	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateById(@PathVariable long id, @RequestBody Book updatedBook) {
		Book updateResult = bookService.updateById(id, updatedBook);
		if(updateResult != null)
			return new ResponseEntity<>(updateResult, HttpStatus.OK);			// 200
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);					// 404
	}
	
	@DeleteMapping("/books/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
		boolean isDeleted;
		try {
			isDeleted = this.bookService.deleteById(id);
			if(isDeleted) {
				return new ResponseEntity<HttpStatus>(HttpStatus.OK);			// 200
			}
			else {
				return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);	// 404
			}
		}
		catch(NumberFormatException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);				// 400
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatusCode.valueOf(500));			// 500
		}
	}
	
}
