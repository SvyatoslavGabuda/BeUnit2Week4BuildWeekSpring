package it.epicode.bw.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import it.epicode.bw.auth.service.AuthServiceImpl;
import it.epicode.bw.enums.StatoFattura;
import it.epicode.bw.models.Cliente;
import it.epicode.bw.models.Fattura;
import it.epicode.bw.repository.FattureRepo;

@Service
public  class FattureService {

	@Autowired
	FattureRepo fattureRepo;
	@Autowired
	AuthServiceImpl authService;

	public Fattura creaFattura(Double importo, Cliente c) {
		Fattura f1 = new Fattura();
		f1.setAnno(LocalDate.now().getYear());
		f1.setDataEmissione(LocalDate.now());
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

	public List<Fattura> findByCliente(Cliente c) {
		List<Fattura> f = fattureRepo.findByCliente(c);
		return f;

	}

	public Page<Fattura> findByClientePagable(Pageable pag,Cliente c) {
		Page<Fattura> f = fattureRepo.findByCliente((org.springframework.data.domain.Pageable) pag,c);
		return f;

	}
	
	public List<Fattura> findByStadioFattura(StatoFattura stadioFattura){
		List<Fattura> f = fattureRepo.findByStadioFattura(stadioFattura);
		return f;
		
	}
	public Page<Fattura> findByStadioFatturaPageable(Pageable pag, StatoFattura stadioFattura){
		Page<Fattura> f = fattureRepo.findByStadioFattura((org.springframework.data.domain.Pageable) pag, stadioFattura);
		return f;
		
	}
	
	public List<Fattura> findByDataEmissione(LocalDate data){
		List<Fattura> f= fattureRepo.findByDataEmissione(data);
		return f;
		
	}
	
	public Page<Fattura> findByDataEmissionePageable (Pageable pag,LocalDate data){
		Page<Fattura> f= fattureRepo.findByDataEmissione((org.springframework.data.domain.Pageable) pag,data);
		return f;
		
	}
	public List<Fattura> findByDataAnno(Integer a){
		List<Fattura> f= fattureRepo.findByAnno(a);
		return f;
		
	}
	
	public Page<Fattura> findByDataAnnoPageable (Pageable pag,Integer a){
		Page<Fattura> f= fattureRepo.findByAnno((org.springframework.data.domain.Pageable) pag,a);
		return f;
		
	}
	public List<Fattura> findByImporto(Double a, Double b){
		List<Fattura> f= fattureRepo.findByImportoBetween(a,b);
		return f;
		
	}
	
	public Page<Fattura> findByDataImportoPageable (Pageable pag,Double a, Double b){
		Page<Fattura> f= fattureRepo.findByImportoBetween((org.springframework.data.domain.Pageable) pag,a,b);
		return f;
		
	}
	
	
	
}
