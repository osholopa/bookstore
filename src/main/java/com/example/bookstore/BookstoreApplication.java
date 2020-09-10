package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initialBooks(BookRepository repository) {
		return (args) -> {
			log.info("insert a few example books");
			repository.save(new Book("Java-ohjelmointi", "Mika VesterHolm, Jorma Kyppö", "978-952-14-3556-0", 2018));
			repository.save(new Book("Personality disorders", "Thomas A. Widiger, Paul T. Costa Jr.", "978-1-4338-1166-1", 2013));
		};
	}

}
