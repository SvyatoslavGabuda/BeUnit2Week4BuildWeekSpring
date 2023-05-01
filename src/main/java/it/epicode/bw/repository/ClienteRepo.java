package it.epicode.bw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



import it.epicode.bw.models.Cliente;

public interface ClienteRepo extends JpaRepository<Cliente, Long> {
	Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByUsernameOrEmail(String username, String email);

    Optional<Cliente> findByUsername(String username);

    Boolean existsByUsername(String username);
 
    Boolean existsByEmail(String email);
}
