package it.epicode.bw.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.bw.enums.StatoFattura;
import it.epicode.bw.models.Fattura;
import it.epicode.bw.service.FattureService;

@RestController
@RequestMapping("/fatture")
@CrossOrigin(origins = "*", maxAge = 6000000)
public class FattureController {

	@Autowired
	FattureService fattureService;
//get fattura da utente per sola lettura USER E ADMIN

//post - put fattura da ADMIN

//delete admin ADMIN

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Fattura> trovaFatturaTramiteId(@PathVariable Long id) {
		return new ResponseEntity<Fattura>(fattureService.findById(id), HttpStatus.OK);

	}

	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Fattura>> trovaFatturaAll() {
		return new ResponseEntity<List<Fattura>>(fattureService.findAllFatture(), HttpStatus.OK);

	}

	@GetMapping("/stadio/{s}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Fattura>> trovaFatturaByStadioFatture(@PathVariable StatoFattura s) {
		return new ResponseEntity<List<Fattura>>(fattureService.findByStadioFattura(s), HttpStatus.OK);

	}

	@GetMapping("/data/{anno}/{mese}/{giorno}") 
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Fattura>> trovaFatturaByData(@PathVariable int anno, @PathVariable int mese,
			@PathVariable int giorno) {
		return new ResponseEntity<List<Fattura>>(fattureService.findByDataEmissione(LocalDate.of(anno, mese, giorno)),
				HttpStatus.OK);

	}

	@GetMapping("/anno/{anno}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Fattura>> trovaFatturaByAno(@PathVariable int anno) {
		return new ResponseEntity<List<Fattura>>(fattureService.findByDataAnno(anno), HttpStatus.OK);

	}
	@GetMapping("/importo/{imp}/{imp2}") 
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Fattura>> trovaFatturaByImporto(@PathVariable Double imp,@PathVariable Double imp2) {
		return new ResponseEntity<List<Fattura>>(fattureService.findByImporto(imp, imp2), HttpStatus.OK);
		
	}
	@GetMapping("/pag/importo/{imp}/{imp2}") 
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Page<Fattura>> trovaFatturaByImportoPage(Pageable pag,@PathVariable Double imp,@PathVariable Double imp2) {
		return new ResponseEntity<Page<Fattura>>(fattureService.findByImportoPageable(pag,imp, imp2), HttpStatus.OK);
		
	}
	@GetMapping("/pag/anno/{anno}") 
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Page<Fattura>> trovaFatturaByAnoPage(Pageable pag,@PathVariable int anno) {
		return new ResponseEntity<Page<Fattura>>(fattureService.findByDataAnnoPageable(pag,anno), HttpStatus.OK);
		
	}

	@GetMapping("/pag/data/{anno}/{mese}/{giorno}") 
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Page<Fattura>> trovaFatturaByDataPagin(Pageable pag, @PathVariable int anno,
			@PathVariable int mese, @PathVariable int giorno) {
		return new ResponseEntity<Page<Fattura>>(
				fattureService.findByDataEmissionePageable(pag, LocalDate.of(anno, mese, giorno)), HttpStatus.OK);

	}

	@GetMapping("/pag/stadio/{s}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Page<Fattura>> trovaFatturaByStadioFatture(Pageable pag, @PathVariable StatoFattura s) {
		return new ResponseEntity<Page<Fattura>>(fattureService.findByStadioFatturaPageable(pag, s), HttpStatus.OK);

	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> eliminafattura( @PathVariable Long id){
		return new ResponseEntity<String>(fattureService.eliminaFattura(id), HttpStatus.OK);
	}
	@PutMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateFattura(@RequestBody Fattura f){
		return new ResponseEntity<Fattura>(fattureService.modificaFattura(f),HttpStatus.OK);
	}
//	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
//	@PreAuthorize("hasRole('ADMIN')")
//	@ResponseBody
//	public ResponseEntity<?> postFattura(@RequestBody Fattura f){
//		return new ResponseEntity<Fattura>(fattureService.modificaFattura(f),HttpStatus.OK);
//	}

}
