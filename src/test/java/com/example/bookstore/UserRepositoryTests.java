package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.bookstore.domain.User;
import com.example.bookstore.domain.UserRepository;

@DataJpaTest
class UserRepositoryTests {

	@Autowired
	private UserRepository repository;

	@Test
	public void returnsListOfTwo() {
		ArrayList<User> users = (ArrayList<User>) repository.findAll();
		assertThat(users).hasSize(2);
	}

	@Test
	public void findByUserNameShouldWork() {
		User user = repository.findByUsername("admin");
		assertThat(user.getId()).isNotNull();
		assertThat(user.getUsername()).isEqualTo("admin");
	}

	@Test
	public void createShouldWork() {
		User user = new User("jdoe", "$2a$10$h5gs5o2M8RapTziV8khAvehVGIutnFUEy8AdZ.n1Lq.ZXJn/zj1Yy", "jdoe@jdoe.com",
				"USER");
		repository.save(user);
		assertThat(user.getId()).isNotNull();
	}

	@Test
	public void deleteShouldWork() {
		ArrayList<User> users = (ArrayList<User>) repository.findAll();
		assertThat(users).hasSize(2);
		String nameOfFirst = users.get(0).getUsername();
		repository.delete(users.get(0));
		users = (ArrayList<User>) repository.findAll();
		assertThat(users).hasSize(1);
		assertThat(users.get(0).getUsername()).isNotEqualTo(nameOfFirst);
	}

}
