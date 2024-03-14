package ru.yarn.yarnstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.yarn.yarnstore.entities.Roles;
import ru.yarn.yarnstore.entities.User;
import ru.yarn.yarnstore.repositories.RoleRepository;
import ru.yarn.yarnstore.repositories.UserRepository;
import ru.yarn.yarnstore.security.CustomPasswordEncoder;

import java.util.List;

@SpringBootApplication
public class YarnstoreApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(YarnstoreApplication.class, args);
		UserRepository userRepository = context.getBean(UserRepository.class);
		RoleRepository roleRepository = context.getBean(RoleRepository.class);
		CustomPasswordEncoder customPasswordEncoder = context.getBean(CustomPasswordEncoder.class);

		Roles role = new Roles();
		role.setName("ADMIN");
		roleRepository.save(role);

		User user = new User();
		user.setLogin("admin");
		user.setPassword("123");
		user.setRoles(List.of(role));
		userRepository.save(user);
	}

}
