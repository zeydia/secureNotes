package com.zeta.SecureNotes.repositories;

import com.zeta.SecureNotes.enumerations.ROLE;
import com.zeta.SecureNotes.models.Role;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(ROLE roleName);
}

