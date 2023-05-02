package it.epicode.bw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.bw.models.Fattura;
import it.epicode.bw.service.FattureService;


@RestController
@RequestMapping("/fatture")
@CrossOrigin(origins = "*", maxAge = 6000000 )
public class FattureController {

	@Autowired FattureService fattureService;
//get fattura da utente per sola lettura USER E ADMIN
	
//post - put fattura da ADMIN
	
//delete admin ADMIN

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Fattura> trovaFatturaTramiteId(@PathVariable Long id){
		return new ResponseEntity<Fattura>(fattureService.findById(id), HttpStatus.OK );
	
	}
	
	
	
	


	
	

}
