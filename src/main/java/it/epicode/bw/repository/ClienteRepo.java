package it.epicode.bw.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.epicode.bw.models.Cliente;
import it.epicode.bw.models.Indirizzo;


public interface ClienteRepo extends JpaRepository<Cliente, Long>, PagingAndSortingRepository<Cliente, Long> {
	Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByUsernameOrEmail(String username, String email);

    Optional<Cliente> findByUsername(String username);

    Boolean existsByUsername(String username);
 
    Boolean existsByEmail(String email);
    
    public Page<Cliente> findAll(Pageable pag);
    
    public List<Cliente> findByFatturatoAnnualeBetween(Double a, Double b);
    public List<Cliente> findByFatturatoAnnualeOrderByFatturatoAnnualeAsc(Double a);
    public Page<Cliente> findByFatturatoAnnualeBetween(Pageable pag,Double a,Double b);
    public Page<Cliente> findByFatturatoAnnualeOrderByFatturatoAnnualeAsc(Pageable pag,Double a);
    
    public List<Cliente> findByDataInserimento(LocalDate l);
    public List<Cliente> findByDataInserimentoOrderByDataInserimentoAsc(LocalDate l);
    public Page<Cliente> findByDataInserimento(Pageable pag,LocalDate l);
    public Page<Cliente> findByDataInserimentoOrderByDataInserimentoAsc(Pageable pag,LocalDate l);
    
    public List<Cliente> findByUltimoContatto(LocalDate l);
    public List<Cliente> findByUltimoContattoOrderByUltimoContattoAsc(LocalDate l);
    public Page<Cliente> findByUltimoContatto(Pageable pag,LocalDate l);
    public Page<Cliente> findByUltimoContattoOrderByUltimoContattoAsc(Pageable pag,LocalDate l);
    
    public List<Cliente> findByNomeContattoContains(String s);
    public List<Cliente> findByNomeContattoOrderByNomeContattoAsc(String s);
    public Page<Cliente> findByNomeContattoContains(Pageable pag,String s);
    public Page<Cliente> findByNomeContattoOrderByNomeContattoAsc(Pageable pag,String s);
    
    public List<Cliente> findByRagioneSocialeOrderByRagioneSocialeAsc(String s);
    public Page<Cliente> findByRagioneSocialeOrderByRagioneSocialeAsc(Pageable pag,String s);
    
    
    public List<Cliente> findBySedeLegale(Indirizzo sedeLegale);
    public List<Cliente> findBySedeLegaleOrderBySedeLegaleAsc(Indirizzo sedeLegale);
    public Page<Cliente> findBySedeLegale(Pageable pag, Indirizzo sedeLegale);
    public Page<Cliente> findBySedeLegaleOrderBySedeLegaleAsc(Pageable pag,Indirizzo sedeLegale);
    
}
