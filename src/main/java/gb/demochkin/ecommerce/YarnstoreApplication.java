package gb.demochkin.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import gb.demochkin.ecommerce.entities.Product;
import gb.demochkin.ecommerce.entities.Roles;
import gb.demochkin.ecommerce.entities.User;
import gb.demochkin.ecommerce.repositories.ProductRepository;
import gb.demochkin.ecommerce.repositories.RoleRepository;
import gb.demochkin.ecommerce.repositories.UserRepository;

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
}
