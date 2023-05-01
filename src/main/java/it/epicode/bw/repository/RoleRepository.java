package it.epicode.bw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.bw.auth.entity.ERole;
import it.epicode.bw.auth.entity.Role;





public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByRoleName(ERole roleName);
}
