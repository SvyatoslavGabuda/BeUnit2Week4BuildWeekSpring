package it.epicode.bw.repository;


import java.time.LocalDate;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.bw.enums.StatoFattura;
import it.epicode.bw.models.Cliente;
import it.epicode.bw.models.Fattura;

public interface FattureRepo extends JpaRepository<Fattura, Long>{
	
	public List<Fattura> findByCliente(Cliente cliente);
	public Page<Fattura> findByCliente(Pageable pag, Cliente cliente);
	
	public List<Fattura> findByStadioFattura(StatoFattura stadioFattura);
	public Page<Fattura> findByStadioFattura(Pageable pag,StatoFattura stadioFattura);
	
	public List<Fattura> findByDataEmissione(LocalDate l);
	public Page<Fattura> findByDataEmissione(Pageable pag,LocalDate l);
	
	public List<Fattura> findByAnno(Integer a);
	public Page<Fattura> findByAnno(Pageable pag,Integer a);
	
	public List<Fattura> findByImportoBetween(Double importo,Double importo2);
	public Page<Fattura> findByImportoBetween(Pageable pag,Double importo,Double importo2);
	
	

}
