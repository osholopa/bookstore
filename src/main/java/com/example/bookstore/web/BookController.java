package com.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;

	@GetMapping(value={"/", "/booklist"})
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	@GetMapping(value="/add-book")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "add";
	}
	@PostMapping(value="/save")
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	@GetMapping(value="/edit/{id}")
	public String edit(@PathVariable("id") Long bookId, Book book, Model model) {
		Book toBeEdited = repository.findById(bookId).get();
		model.addAttribute("book", toBeEdited);
		return "edit";
	}
	@PostMapping(value="/update/{id}")
	public String updateBook(@PathVariable("id") Long bookId, Book book, Model model) {
		Book toBeUpdated = repository.findById(bookId).get();
		toBeUpdated.setAuthor(book.getAuthor());
		toBeUpdated.setTitle(book.getTitle());
		toBeUpdated.setIsbn(book.getIsbn());
		toBeUpdated.setYear(book.getYear());
		repository.save(toBeUpdated);
		return "redirect:../booklist";
	}
	@GetMapping(value="/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}
}
