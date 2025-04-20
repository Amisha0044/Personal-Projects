// Service (Business Layer) - handles Business logic (e.g., @Service).

package com.springrest.library.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.library.dao.BookRepository;
import com.springrest.library.entity.Book;


@Service			// to mark a class as a service layer component 
/* 
 * Marks a Business Logic Layer: @Service indicates that the class contains business logic and acts as a bridge between the controller and the data access layer (DAO/repository).
 * Enables Automatic Component Scanning: Spring automatically detects @Service classes and registers them as beans in the Spring container. No need for explicit bean configuration in @Configuration classes.
 * Supports Dependency Injection: You can inject @Service beans into controllers or other services using @Autowired, reducing manual object creation.
 * Separation of Concerns: Keeps business logic separate from controllers and repositories, making code cleaner and easier to maintain.
 * 
 * @Service id not mandatory to use but highly recommended.
 * ✅ Alternative: Use @Component
 	  * If you don’t use @Service, you can replace it with @Component, which still registers the bean.
 */
public class BookServiceImplementation implements BookService {

	@Autowired
	private BookRepository bookRepo;
	
	
	@Override
	public List<Book> getAll() {
		return bookRepo.findAll();		// returns a list of all the books
	}

	@Override
	public Book getById(long id) {
		return bookRepo.findById(id).orElseThrow( () -> new RuntimeException("Book not found with the id:"+id));
	}

	@Override
	public Book insert(Book book) {
//		Primitive long vs. Wrapper Long:
//		Type	Can be null?	Default Value
//		long	❌ No			0 (default)
//		Long	✅ Yes			null (default if not initialized)
		if(book.getId()!=0 && bookRepo.existsById(book.getId())) {
			return null;				// If book exists, return null
		}
		else {
			bookRepo.save(book);		// else insert
			return book;
		}
	}

	@Override
	public Book updateById(long id, Book updatedBook) {
		//Book entity = bookRepo.findById(id).orElseThrow(()->new RuntimeException("Book not found with the id:"+id));
		if(bookRepo.existsById(id)) {
			bookRepo.save(updatedBook);		// update if book id already present
			return updatedBook;
		}
		else {
			return null;					// else ignore
		}
	}

	@Override
	public boolean deleteById(long id) {
		Optional<Book> targetBook = bookRepo.findById(id);
		if(targetBook.isPresent()) {
			bookRepo.deleteById(id);
			//bookRepo.delete(targetBook.get());
			return true;				// book was found and deleted
		}
		else {
			return false;				// book was not found
		}
		
	}

}
