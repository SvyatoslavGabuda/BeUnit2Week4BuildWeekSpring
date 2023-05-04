package it.epicode.bw.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import it.epicode.bw.auth.exception.MyAPIException;
import it.epicode.bw.auth.service.AuthServiceImpl;
import it.epicode.bw.enums.StatoFattura;
import it.epicode.bw.models.Cliente;
import it.epicode.bw.models.Fattura;
import it.epicode.bw.repository.ClienteRepo;
import it.epicode.bw.repository.FattureRepo;

@Service
public class FattureService {

	@Autowired
	FattureRepo fattureRepo;
	@Autowired
	AuthServiceImpl authService;
	@Autowired
	ClienteRepo cRepo;

	public Fattura creaFattura(Double importo, Long id) {
		Fattura f1 = new Fattura();
		f1.setAnno(LocalDate.now().getYear());
		f1.setDataEmissione(LocalDate.now());
		f1.setStadioFattura(StatoFattura.EMESSA);
		Cliente clinte = cRepo.findById(id).get();
		f1.setCliente(clinte);
		f1.setImporto(importo);
		f1.setNumeroFattura(Integer.parseInt
				(""+clinte.getId_cliente()+
						""+clinte.getEmailContatto().length()+
						""+LocalDateTime.now().getMinute()+""+
						LocalDateTime.now().getSecond()));
		fattureRepo.save(f1);
		return f1;

	}

	public Fattura modificaFattura(Fattura f) {
		if (fattureRepo.existsById(f.getId_fattura())) {
			fattureRepo.save(f);
			return f;
		} else {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "Fattura non torvata");
		}
	}

	public String eliminaFattura(Long id) {
		System.out.println(id);
		System.out.println(fattureRepo.existsById(id));
		
		if (fattureRepo.existsById(id)) {
			fattureRepo.deleteById(id);
			return "fattura eliminata";

		} else {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "Fattura non torvata");
		}
	}

	public List<Fattura> findByCliente(Cliente c) {
		// metodo che ricerca se il cliente esiste
		List<Fattura> f = fattureRepo.findByCliente(c);
		return f;

	}

	public Page<Fattura> findByClientePagable(Pageable pag, Cliente c) {
		Page<Fattura> f = fattureRepo.findByCliente((org.springframework.data.domain.Pageable) pag, c);
		return f;

	}

	public Fattura findById(long id) {
		if (fattureRepo.existsById(id)) {
			return fattureRepo.findById(id).get();
		} else {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "Fattura non torvata");
		}

	}

	public List<Fattura> findAllFatture() {
		if (!fattureRepo.findAll().isEmpty()) {
			return fattureRepo.findAll();

		} else {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "Fatture non torvate");
		}

	}
	public Page<Fattura> findAllFatturepagin(Pageable pag) {
		if (!fattureRepo.findAll().isEmpty()) {
			return fattureRepo.findAll(pag);
			
		} else {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "Fatture non torvate");
		}
		
	}

	public List<Fattura> findByStadioFattura(StatoFattura stadioFattura) {
		if (!(stadioFattura instanceof StatoFattura)) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "valore inserito non valido");
		}
		if (fattureRepo.findByStadioFattura(stadioFattura).isEmpty()) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "Fatture non torvate");
		}
		List<Fattura> f = fattureRepo.findByStadioFattura(stadioFattura);
		return f;

	}

	public Page<Fattura> findByStadioFatturaPageable(Pageable pag, StatoFattura stadioFattura) {
		if (!(stadioFattura instanceof StatoFattura)) {
			throw new MyAPIException(HttpStatus.BAD_REQUEST, "valore inserito non valido");
		}
		if (fattureRepo.findByStadioFattura(stadioFattura).isEmpty()) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "Fatture non torvate");
		}
		Page<Fattura> f = fattureRepo.findByStadioFattura((org.springframework.data.domain.Pageable) pag,
				stadioFattura);
		return f;

	}

	public List<Fattura> findByDataEmissione(LocalDate data) {
		if (!(data instanceof LocalDate)) {
			throw new MyAPIException(HttpStatus.BAD_REQUEST, "valore inserito non valido");
		}
		if (fattureRepo.findByDataEmissione(data).isEmpty()) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "Fatture non torvate");
		}
		List<Fattura> f = fattureRepo.findByDataEmissione(data);
		return f;

	}

	public Page<Fattura> findByDataEmissionePageable(Pageable pag, LocalDate data) {
		if (!(data instanceof LocalDate)) {
			throw new MyAPIException(HttpStatus.BAD_REQUEST, "valore inserito non valido");
		}
		if (fattureRepo.findByDataEmissione(data).isEmpty()) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "Fatture non torvate");
		}
		Page<Fattura> f = fattureRepo.findByDataEmissione((org.springframework.data.domain.Pageable) pag, data);
		return f;

	}

	public List<Fattura> findByDataAnno(Integer a) {
		if (!(a instanceof Integer)) {
			throw new MyAPIException(HttpStatus.BAD_REQUEST, "valore inserito non valido");
		}
		if (fattureRepo.findByAnno(a).isEmpty()) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "Fatture non torvate");
		}
		List<Fattura> f = fattureRepo.findByAnno(a);
		return f;

	}

	public Page<Fattura> findByDataAnnoPageable(Pageable pag, Integer a) {
		if (!(a instanceof Integer)) {
			throw new MyAPIException(HttpStatus.BAD_REQUEST, "valore inserito non valido");
		}
		if (fattureRepo.findByAnno(a).isEmpty()) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "Fatture non torvate");
		}
		Page<Fattura> f = fattureRepo.findByAnno((org.springframework.data.domain.Pageable) pag, a);
		return f;

	}

	public List<Fattura> findByImporto(Double a, Double b) {
		if (!(a instanceof Double && b instanceof Double)) {
			throw new MyAPIException(HttpStatus.BAD_REQUEST, "valore inserito non valido");
		}
		if (fattureRepo.findByImportoBetween(a, b).isEmpty()) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "Fatture non torvate");
		}
		List<Fattura> f = fattureRepo.findByImportoBetween(a, b);
		return f;

	}

	public Page<Fattura> findByImportoPageable(Pageable pag, Double a, Double b) {
		if (!(a instanceof Double && b instanceof Double)) {
			throw new MyAPIException(HttpStatus.BAD_REQUEST, "valore inserito non valido");
		}
		if (fattureRepo.findByImportoBetween(a, b).isEmpty()) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "Fatture non torvate");
		}
		Page<Fattura> f = fattureRepo.findByImportoBetween((org.springframework.data.domain.Pageable) pag, a, b);
		return f;

	}

}
