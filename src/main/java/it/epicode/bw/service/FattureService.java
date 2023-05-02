package it.epicode.bw.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.bw.auth.service.AuthServiceImpl;
import it.epicode.bw.enums.StatoFattura;
import it.epicode.bw.models.Cliente;
import it.epicode.bw.models.Fattura;
import it.epicode.bw.repository.FattureRepo;

@Service
public class FattureService {
	
	@Autowired
	FattureRepo fattureRepo;
	@Autowired
	AuthServiceImpl authService;
	
	
	public Fattura creaFattura(Double importo,Cliente c) {
		Fattura f1 = new Fattura();
		f1.setAnno(LocalDate.now().getYear());
		f1.setDataEmissione(LocalDateTime.now());
		f1.setStadioFattura(StatoFattura.EMESSA);
		f1.setCliente(c);
		f1.setImporto(importo);
		fattureRepo.save(f1);
		return f1;
		
	}
	public void modificaFattura(Fattura f) {
		fattureRepo.save(f);
	}
	
	public void eliminaFattura(Long id) {
		fattureRepo.deleteById(id);
	}
	

}
