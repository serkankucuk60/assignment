package com.nurd.project.authmanagement.repo;

import java.util.Optional;

import com.nurd.project.authmanagement.models.Role;
import com.nurd.project.authmanagement.models.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
