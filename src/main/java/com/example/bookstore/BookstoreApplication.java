package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;
import com.example.bookstore.domain.User;
import com.example.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initialBooksAndCategories(BookRepository repository, CategoryRepository categories, UserRepository users) {
		return (args) -> {
			log.info("insert a few example books");
			Category programming = new Category("programming");
			Category psychology = new Category("psychology");	
			categories.save(programming);
			categories.save(psychology);
			categories.save(new Category("crime"));
			categories.save(new Category("fantasy"));
			categories.save(new Category("sci-fi"));
			categories.save(new Category("classic"));
			repository.save(new Book("Java-ohjelmointi", "Mika Vesterholm, Jorma Kyppö", "978-952-14-3556-0", 2018, programming));
			repository.save(new Book("Personality disorders", "Thomas A. Widiger, Paul T. Costa Jr.", "978-1-4338-1166-1", 2013, psychology));
			users.save(new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@user.com", "USER"));
			users.save(new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C","admin@admin.com", "ADMIN"));
		};
	}
}
