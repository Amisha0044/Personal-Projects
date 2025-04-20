// to have loose coupling - controller does not depend on the implementation, If you need to switch the implementation (e.g., from a database service to an in-memory service for testing), you can do so without changing the controller.
// and easier unit testing - We can create mock implementations of the service for unit tests. Example: Using Mockito in tests (when(courseService.getCourses()).thenReturn(mockCourses);)

package com.springrest.library.service;

import java.util.List;

import com.springrest.library.entity.Book;


public interface BookService {
	
	// by default interface methods are public and static
	List<Book> getAll();
	Book getById(long id);
	Book insert(Book book);
	Book updateById(long id, Book updatedBook);
	boolean deleteById(long id);
	
}
