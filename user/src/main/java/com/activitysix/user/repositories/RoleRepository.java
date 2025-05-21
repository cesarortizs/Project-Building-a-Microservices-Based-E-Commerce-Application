package com.activitysix.user.repositories;

import com.activitysix.user.models.ERole;
import com.activitysix.user.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
