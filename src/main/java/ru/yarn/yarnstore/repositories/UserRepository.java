package ru.yarn.yarnstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yarn.yarnstore.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);
    Optional<User> findByLogin(String name);
}
