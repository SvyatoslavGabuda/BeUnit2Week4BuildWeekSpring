package it.epicode.bw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.bw.models.Comuni_italiani;

public interface ComuneRepo extends JpaRepository<Comuni_italiani, Long>{

	public List<Comuni_italiani> findByNomeProvinciaIgnoreCase(String s);
}
