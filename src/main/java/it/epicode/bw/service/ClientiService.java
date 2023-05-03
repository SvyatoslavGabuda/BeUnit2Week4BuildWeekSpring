package it.epicode.bw.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

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
		System.out.println("Cliente creato!");
		clienteRepo.save(c);
		return c;
	}
	
	public void modificaCliente(Cliente c) {
		clienteRepo.save(c);
	}
	
	public void eliminaCliente(Long id) {
		clienteRepo.deleteById(id);
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
	
	public List<Cliente> findByFatturato(Double a, Double b) {
		 List<Cliente> list = clienteRepo.findByFatturatoAnnualeBetween(a, b);
		return list;
	}
	
	public Page<Cliente> findByFatturato(Pageable pag, Double a, Double b) {
		 Page<Cliente> list = clienteRepo.findByFatturatoAnnualeBetween(pag, a, b);
		return list;
	}
	
	public List<Cliente> findByDateIns(LocalDate l) {
		List<Cliente> list = clienteRepo.findByDataInserimento(l);
		return list;
	}
	
	public Page<Cliente> findByDateIns(Pageable pag, LocalDate l) {
		Page<Cliente> list = clienteRepo.findByDataInserimento(pag,l);
		return list;
	}
	
	public List<Cliente> findByDateLastCont(LocalDate l) {
		List<Cliente> list = clienteRepo.findByUltimoContatto(l);
		return list;
	}
	
	public Page<Cliente> findByDateLastCont(Pageable pag, LocalDate l) {
		Page<Cliente> list = clienteRepo.findByUltimoContatto(pag,l);
		return list;
	}
	
	public List<Cliente> findByContains(String s) {
		List<Cliente> list = clienteRepo.findByNomeContattoContains(s);
		return list;
	}
	
	public Page<Cliente> findByContains(Pageable pag, String s) {
		Page<Cliente> list = clienteRepo.findByNomeContattoContains(pag ,s);
		return list;
	}
	
//	public List<Cliente> findOrderName(String s) {
//		List<Cliente> list = clienteRepo.findByOrderByRagioneSocialeAsc(s);
//		return list;
//	}
//	
//	public Page<Cliente> findOrderName(Pageable pag, String s) {
//		Page<Cliente> list = clienteRepo.findByOrderByRagioneSocialeAsc(pag, s);
//		return list;
//	}
}
