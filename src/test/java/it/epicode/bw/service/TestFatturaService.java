package it.epicode.bw.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import it.epicode.bw.models.Cliente;
import it.epicode.bw.models.Fattura;
import it.epicode.bw.repository.FattureRepo;

@DataJpaTest
//@AutoConfigureTestDatabase
class TestFatturaService {
	
	//FattureRepo fRepo = new FattureRepo();
//	@Autowired
//	FattureRepo fRepo;
//	@Autowired
//	FattureService fService;
	
//	Cliente c;
//	Fattura f1;

	@BeforeEach
	void setUp() throws Exception {
//		c = new Cliente();
		
		
	}

	@Test
	void testCreaFattura() {
//		f1 = fService.creaFattura(100.1, c);
//        assertSame(f1.getCliente(), c);
	}

	@Test
	void testModificaFattura() {

	}

	@Test
	void testEliminaFattura() {

	}

	@Test
	void testFindByCliente() {

	}

	@Test
	void testFindById() {

	}

	@Test
	void testFindAllFatture() {
//		List<Fattura> lf = fService.findAllFatture();

	}

	@Test
	void testFindByStadioFattura() {

	}

	@Test
	void testFindByDataEmissione() {

	}

	@Test
	void testFindByDataAnno() {
	}

	@Test
	void testFindByImporto() {

	}

}
