package com.example.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.bookstore.web.BookController;

@SpringBootTest
class BookstoreApplicationTests {

	@Autowired
	private BookController bookController;
	
	@Test
	void contextLoads() {
		assertThat(bookController).isNotNull();
	}

}
