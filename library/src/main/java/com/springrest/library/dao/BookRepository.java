// Repository (DAO Layer) - Handles database operations (e.g., @Repository)
// DAO (Data Access Object) layer is typically implemented using Spring Data JPA.
// Spring Data JPA allows us to create an interface (CourseRepository) without needing to implement it.
// Spring will automatically generate the implementation for us at runtime.
// Spring will automatically provide the SQL operations when using JpaRepository or CrudRepository. No need to write SQL manually.

package com.springrest.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrest.library.entity.Book;


@Repository
/* @Repository is not mandatory, but it is recommended in DAO (Data Access Object) classes.
 * Why Does It Work Without @Repository?:
	 * Spring Boot automatically detects DAO classes (interfaces extending JpaRepository, CrudRepository, or PagingAndSortingRepository) and registers them as Spring beans.
	 * That means even if you don’t use @Repository, Spring still creates the bean and injects it wherever needed.
 * Alternative to make it work: Use @Component
	 * Spring can still detect DAO (Data Access Object) classes with @Component, but you lose automatic exception translation.
	 * Problem: You lose Spring’s automatic exception translation, which converts JPA exceptions into Spring’s DataAccessException.
	 * ✅ Solution: If using JPA, prefer @Repository.
 */
public interface BookRepository extends JpaRepository<Book, Long> {		// <entityClassName, primaryKeyType>
	// JpaRepository (Advanced Features) - Inherits all functionalities of CrudRepository and PagingAndSortingRepository.
}
