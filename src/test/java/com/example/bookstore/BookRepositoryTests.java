package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;

@DataJpaTest
class BookRepositoryTests {
	
	@Autowired
	private BookRepository repository;
	
	@Test
	public void returnsListOfTwo() {
		ArrayList<Book> books = (ArrayList<Book>) repository.findAll();
		assertThat(books).hasSize(2);
	}

	@Test
	public void findByAuthorShouldReturnBooks() {
		List<Book> books = repository.findByAuthor("Mika Vesterholm, Jorma Kyppö");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualTo("Java-ohjelmointi");
	}
	
	@Test
	public void isAbleToCreateBooks() {
		Book book = new Book("Jäätynyt enkeli","Reijo Mäki", "951-1-11401-8", 1990, new Category("Turku"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteShouldWork() {
		ArrayList<Book> books = (ArrayList<Book>) repository.findAll();
		assertThat(books).hasSize(2);
		String firstTitle = books.get(0).getTitle();
		repository.delete(books.get(0));
		books = (ArrayList<Book>) repository.findAll();
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isNotEqualTo(firstTitle);
	}
}
