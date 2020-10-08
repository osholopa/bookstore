package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

@DataJpaTest
class CategoryRepositoryTests {
	
	@Autowired
	private CategoryRepository repository;

	@Test
	public void sixInitialCategories() {
		ArrayList<Category> books = (ArrayList<Category>) repository.findAll();
		assertThat(books).hasSize(6);
	}
	
	@Test
	public void findByNameShouldWork() {
		List<Category> categories = repository.findByName("programming");
		assertThat(categories).hasSize(1);
		assertThat(categories.get(0).getName()).isEqualTo("programming");
	}
	
	@Test
	public void createShouldWork() {
		Category category = new Category("crappy");
		repository.save(category);
		assertThat(category.getCategoryid()).isNotNull();
	}
	
	@Test
	public void deleteShouldWork() {
		ArrayList<Category> categories = (ArrayList <Category>) repository.findAll();
		assertThat(categories).hasSize(6);
		String nameOfFirst = categories.get(0).getName();
		repository.delete(categories.get(0));
		categories = (ArrayList <Category>) repository.findAll();
		assertThat(categories).hasSize(5);
		assertThat(categories.get(0).getName()).isNotEqualTo(nameOfFirst);
	}
}
