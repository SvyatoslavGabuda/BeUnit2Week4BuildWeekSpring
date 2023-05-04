package it.epicode.bw.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;

import it.epicode.bw.auth.exception.MyAPIException;
import it.epicode.bw.models.Cliente;
import it.epicode.bw.models.Comuni_italiani;
import it.epicode.bw.models.Indirizzo;
import it.epicode.bw.repository.ComuneRepo;
import it.epicode.bw.repository.IndirizzoRepo;

@Service
public class IndirizzoService {

	@Autowired
	IndirizzoRepo repo;
	@Autowired
	ComuneRepo comuneRepo;
	
	public void creaIndirizzo() {
		
		Comuni_italiani c = comuneRepo.findById(randomNumber(0l,7904l)).get();
		Faker fake = Faker.instance(new Locale("it-IT"));
		Address a = fake.address();
		a.cityName();
		
		
		Indirizzo i = new Indirizzo();
		i.setVia(fake.address().streetName());
		i.setCivico(randomNumber(0l,300l).toString());
		i.setLocalita(c.getDenominazione_in_italiano());
		i.setCap(Integer.parseInt(fake.address().zipCode()));
		c.getIndirizzo().add(i);
		i.setComune(c);
		repo.save(i);
		comuneRepo.save(c);
	}
	public Indirizzo modificaIndirizzo(Indirizzo c) {
		if (repo.existsById(c.getId_indirizzo())) {
			repo.save(c);
			return c;
		} else {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "Indirizzo non torvato");
		}
	}

	
	public String eliminaIndirizzo(Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return "Cliente eliminata";

		} else {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "Indirizzo non torvato");
		}
	}
	
	public Indirizzo findById(Long id) {
		return repo.findById(id).get();
			
	}
	
	public List<Indirizzo> findAllIndirizzo(){
		return  repo.findAll();		
	}
	
	public Page<Indirizzo> findAllIndirizzo(Pageable pag) {
		return repo.findAll(pag);
		
	}
	
	private Long randomNumber(Long a,Long b) {
		Long min = a;
		Long max = b;
		Long range = max - min + 1;
		Long rn = (long) (Math.random()*range + min);
		return rn;
		
	}
	
}
