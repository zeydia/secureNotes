package com.zeta.SecureNotes.repositories;

import com.zeta.SecureNotes.models.Role;
import com.zeta.SecureNotes.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(@NotBlank @Size(max = 20) String username);

    List<User> findByRole(Role role);
}
