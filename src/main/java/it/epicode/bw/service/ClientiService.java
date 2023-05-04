package it.epicode.bw.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import it.epicode.bw.auth.exception.MyAPIException;
import it.epicode.bw.auth.service.AuthServiceImpl;
import it.epicode.bw.models.Cliente;
import it.epicode.bw.models.Fattura;
import it.epicode.bw.models.Indirizzo;
import it.epicode.bw.repository.ClienteRepo;

@Service
public class ClientiService {

	@Autowired
	ClienteRepo clienteRepo;
	@Autowired
	AuthServiceImpl authService;

	public Cliente creaCliente(String ragioneSociale, String pIva, Double fatturatoAnnuale, Indirizzo indirizzo) {

		Faker fake = Faker.instance(new Locale("it-IT"));
		Cliente c = new Cliente();
		c.setUsername(fake.name() + "." + fake.number());
		c.setRagioneSociale(ragioneSociale);
		c.setPIva(pIva);
		c.setEmail(c.getUsername() + "@mail.com");
		c.setPsw(fake.internet().password(6, 10, true, true));
		c.setDataInserimento(LocalDate.now());
		c.setUltimoContatto(LocalDate.now());
		c.setFatturatoAnnuale(fatturatoAnnuale);
		c.setPec(c.getUsername() + "@pec.it");
		c.setTel(fake.phoneNumber().cellPhone());
		c.setEmailContatto(c.getEmail());
		c.setNomeContatto(fake.name() + "");
		c.setCognomeContatto(fake.name().lastName());
		c.setTelContatto(c.getTel());
		c.setSedeLegale(indirizzo);
		c.setSedeOperativa(indirizzo);
		System.out.println("Cliente creato!");
		clienteRepo.save(c);
		return c;
	}
	
	public Cliente modificaCliente(Cliente c) {
		if (clienteRepo.existsById(c.getId_cliente())) {
			clienteRepo.save(c);
			return c;
		} else {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "Cliente non torvata");
		}
	}

	
	public String eliminaCliente(Long id) {
		if (clienteRepo.existsById(id)) {
			clienteRepo.deleteById(id);
			return "Cliente eliminata";

		} else {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "Cliente non torvata");
		}
	}
	
	public Cliente findById(Long id) {
		return clienteRepo.findById(id).get();
			
	}
	
	public List<Cliente> findAllClienti(){
		return  clienteRepo.findAll();		
	}
	
	public Page<Cliente> findAllClienti(Pageable pag) {
		return clienteRepo.findAll(pag);
		
	}
	
//	<<<<INIZIO FATTURATO>>>>
	public List<Cliente> findByFatturato(Double a, Double b) {
		 List<Cliente> list = clienteRepo.findByFatturatoAnnualeBetween(a, b);
		return list;
	}
	public List<Cliente> orderByFatturato(Double a) {
		List<Cliente> list = clienteRepo.findByFatturatoAnnualeOrderByFatturatoAnnualeAsc(a);
		return list;
	}
	
	public Page<Cliente> findByFatturato(Pageable pag, Double a, Double b) {
		 Page<Cliente> list = clienteRepo.findByFatturatoAnnualeBetween(pag, a, b);
		return list;
	}
	public Page<Cliente> OrderByFatturato(Pageable pag, Double a) {
		Page<Cliente> list = clienteRepo.findByFatturatoAnnualeOrderByFatturatoAnnualeAsc(pag, a);
		return list;
	}
//	<<<<FINE FATTURATO>>>>
	
	
//	<<<<INIZIO DATA INSERIMENTO>>>>
	public List<Cliente> findByDateIns(LocalDate l) {
		List<Cliente> list = clienteRepo.findByDataInserimento(l);
		return list;
	}
	public List<Cliente> orderByDateIns(LocalDate l) {
		List<Cliente> list = clienteRepo.findByDataInserimentoOrderByDataInserimentoAsc(l);
		return list;
	}
	
	public Page<Cliente> findByDateIns(Pageable pag, LocalDate l) {
		Page<Cliente> list = clienteRepo.findByDataInserimento(pag,l);
		return list;
	}
	public Page<Cliente> orderByDateIns(Pageable pag, LocalDate l) {
		Page<Cliente> list = clienteRepo.findByDataInserimentoOrderByDataInserimentoAsc(pag,l);
		return list;
	}
//	<<<<FINE DATA INSERIMENTO>>>>
	
	
	
//	<<<<INIZIO LAST CONT>>>>	
	public List<Cliente> findByDateLastCont(LocalDate l) {
		List<Cliente> list = clienteRepo.findByUltimoContatto(l);
		return list;
	}
	public List<Cliente> orderByDateLastCont(LocalDate l) {
		List<Cliente> list = clienteRepo.findByUltimoContattoOrderByUltimoContattoAsc(l);
		return list;
	}
	
	public Page<Cliente> findByDateLastCont(Pageable pag, LocalDate l) {
		Page<Cliente> list = clienteRepo.findByUltimoContatto(pag,l);
		return list;
	}
	public Page<Cliente> orderByDateLastCont(Pageable pag, LocalDate l) {
		Page<Cliente> list = clienteRepo.findByUltimoContattoOrderByUltimoContattoAsc(pag,l);
		return list;
	}
//	<<<<FINE LAST CONT>>>>	
	
	
	
//	<<<<INIZIO RICERCA PER NOME>>>>	
	public List<Cliente> findByNameContains(String s) {
		List<Cliente> list = clienteRepo.findByNomeContattoContains(s);
		return list;
	}
	public List<Cliente> orderByName(String s) {
		List<Cliente> list = clienteRepo.findByNomeContattoOrderByNomeContattoAsc(s);
		return list;
	}
	
	public Page<Cliente> findByNameContains(Pageable pag, String s) {
		Page<Cliente> list = clienteRepo.findByNomeContattoContains(pag ,s);
		return list;
	}
	public Page<Cliente> orderByName(Pageable pag, String s) {
		Page<Cliente> list = clienteRepo.findByNomeContattoOrderByNomeContattoAsc(pag ,s);
		return list;
	}
//	<<<<FINE RICERCA PER NOME>>>>	
	
	
	
//	<<<<INIZIO RICERCA SEDE LEGALE>>>>	
	public List<Cliente> findBySedeLegale( Indirizzo sedeLegale){
		List<Cliente> list = clienteRepo.findBySedeLegale(sedeLegale);
		return list;
	}
	public List<Cliente> orderBySedeLegale( Indirizzo sedeLegale){
		List<Cliente> list = clienteRepo.findBySedeLegaleOrderBySedeLegaleAsc(sedeLegale);
		return list;
	}
	
	public Page<Cliente> findBySedeLegale( Pageable pag, Indirizzo sedeLegale){
		Page<Cliente> list = clienteRepo.findBySedeLegale(pag, sedeLegale);
		return list;
	}
	public Page<Cliente> orderBySedeLegale( Pageable pag, Indirizzo sedeLegale){
		Page<Cliente> list = clienteRepo.findBySedeLegaleOrderBySedeLegaleAsc(pag, sedeLegale);
		return list;
	}
//	<<<<FINE RICERCA SEDE LEGALE>>>>	

}
