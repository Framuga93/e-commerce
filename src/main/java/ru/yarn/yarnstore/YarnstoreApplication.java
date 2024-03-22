package ru.yarn.yarnstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.yarn.yarnstore.entities.Product;
import ru.yarn.yarnstore.entities.Roles;
import ru.yarn.yarnstore.entities.User;
import ru.yarn.yarnstore.repositories.ProductRepository;
import ru.yarn.yarnstore.repositories.RoleRepository;
import ru.yarn.yarnstore.repositories.UserRepository;

import java.util.List;

@SpringBootApplication
public class YarnstoreApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(YarnstoreApplication.class, args);
		UserRepository userRepository = context.getBean(UserRepository.class);
		RoleRepository roleRepository = context.getBean(RoleRepository.class);
		PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);


		Roles role = new Roles();
		role.setName("ADMIN");
		roleRepository.save(role);

		User user = new User();
		user.setLogin("admin");
		user.setPassword(passwordEncoder.encode("123"));
		user.setEmail("fat@gmail.com");
		user.setRoles(List.of(role));
		userRepository.save(user);

		ProductRepository productRepository = context.getBean(ProductRepository.class);
		Product product1 = new Product("каша", 12.2, 2);
		Product product2 = new Product("хлеб", 1.0, 5);
		Product product3 = new Product("молоко", 2.3, 7);
		Product product4 = new Product("кефир", 44.4, 8);
		productRepository.saveAll(List.of(product1,product2,product3,product4));
	}

	//TODO: Добавить РОЛИ в тимлиф страницу user. Исправить чтобы вновь авторизованные люди получали роль USER

}
