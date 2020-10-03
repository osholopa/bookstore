package com.example.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository bookrepository;
	@Autowired
	private CategoryRepository categoryrepository;

	@GetMapping(value={"/", "/booklist"})
	public String bookList(Model model) {
		model.addAttribute("books", bookrepository.findAll());
		return "booklist";
	}
	@GetMapping(value="/add-book")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryrepository.findAll());
		return "add";
	}
	@PostMapping(value="/save")
	public String save(Book book) {
		bookrepository.save(book);
		return "redirect:booklist";
	}
	@GetMapping(value="/edit/{id}")
	public String edit(@PathVariable("id") Long bookId, Book book, Model model) {
		model.addAttribute("categories", categoryrepository.findAll());
		Book toBeEdited = bookrepository.findById(bookId).get();
		model.addAttribute("book", toBeEdited);
		return "edit";
	}
	@PostMapping(value="/update/{id}")
	public String updateBook(@PathVariable("id") Long bookId, Book book, Model model) {
		Book toBeUpdated = bookrepository.findById(bookId).get();
		toBeUpdated.setAuthor(book.getAuthor());
		toBeUpdated.setTitle(book.getTitle());
		toBeUpdated.setIsbn(book.getIsbn());
		toBeUpdated.setYear(book.getYear());
		toBeUpdated.setCategory(book.getCategory());
		bookrepository.save(toBeUpdated);
		return "redirect:../booklist";
	}
	@GetMapping(value="/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		bookrepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) bookrepository.findAll();
	}
	@RequestMapping(value="/books/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findById(@PathVariable("id") Long bookId) {
		return bookrepository.findById(bookId);
	}
	@GetMapping(value="/login")
	public String loginPage() {
		return "login";
	}
}
