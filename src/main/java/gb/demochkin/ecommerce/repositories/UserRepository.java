package gb.demochkin.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import gb.demochkin.ecommerce.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
