package ru.yarn.yarnstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yarn.yarnstore.entities.Roles;

public interface RoleRepository extends JpaRepository<Roles,Long> {
}
