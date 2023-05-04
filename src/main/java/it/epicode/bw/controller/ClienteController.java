package it.epicode.bw.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.bw.models.Cliente;
import it.epicode.bw.models.Indirizzo;
import it.epicode.bw.service.ClientiService;

@RestController
@RequestMapping("/Cliente")
@CrossOrigin(origins = "*", maxAge = 6000000)
public class ClienteController {
	
	@Autowired ClientiService clientiService;

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Cliente> trovaClienteTramiteId(@PathVariable Long id) {
		return new ResponseEntity<Cliente>(clientiService.findById(id), HttpStatus.OK);
	}
	
	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Cliente>> trovaClientiAll() {
		return new ResponseEntity<List<Cliente>>(clientiService.findAllClienti(), HttpStatus.OK);
	}
	
	@GetMapping("/dataInserimento/{anno}/{mese}/{giorno}") 
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Cliente>> trovaClienteByDataInseriemento(@PathVariable int anno, @PathVariable int mese,
			@PathVariable int giorno) {
		return new ResponseEntity<List<Cliente>>(clientiService.findByDateIns(LocalDate.of(anno, mese, giorno)),
				HttpStatus.OK);
	}
	
	@GetMapping("/fatturato/{imp}/{imp2}") 
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Cliente>> trovaFatturato(@PathVariable Double imp,@PathVariable Double imp2) {
		return new ResponseEntity<List<Cliente>>(clientiService.findByFatturato(imp, imp2), HttpStatus.OK);
	}
	
	@GetMapping("/dataUltimoCont/{anno}/{mese}/{giorno}") 
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Cliente>> trovaClienteperUltimoCont(@PathVariable int anno, @PathVariable int mese,
			@PathVariable int giorno) {
		return new ResponseEntity<List<Cliente>>(clientiService.findByDateLastCont(LocalDate.of(anno, mese, giorno)),
				HttpStatus.OK);
	}
	
	@GetMapping("/name/{imp}") 
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Cliente>> trovaNome(@PathVariable String imp) {
		return new ResponseEntity<List<Cliente>>(clientiService.findByNameContains(imp), HttpStatus.OK);
	}
	
	@GetMapping("/sedeLegale/{imp}") 
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Cliente>> trovaSedeLegale(@PathVariable Indirizzo imp) {
		return new ResponseEntity<List<Cliente>>(clientiService.findBySedeLegale(imp), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> eliminaCliente( @PathVariable Long id){
		return new ResponseEntity<String>(clientiService.eliminaCliente(id), HttpStatus.OK);
	}
	
	@PutMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateCliente(@RequestBody Cliente c){
		return new ResponseEntity<Cliente>(clientiService.modificaCliente(c),HttpStatus.OK);
	}
	
	
}
