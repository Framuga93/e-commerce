package gb.demochkin.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import gb.demochkin.ecommerce.entities.Roles;

public interface RoleRepository extends JpaRepository<Roles,Long> {
    Roles findByName(String name);
}
