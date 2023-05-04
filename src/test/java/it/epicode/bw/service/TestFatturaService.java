package it.epicode.bw.service;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import it.epicode.bw.enums.StatoFattura;
import it.epicode.bw.models.Cliente;
import it.epicode.bw.models.Fattura;
import it.epicode.bw.repository.ClienteRepo;
import it.epicode.bw.repository.FattureRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestPropertySource(locations = "classpath:application.properties")
class TestFatturaService {

	@Autowired
	FattureRepo fRepo;

	@Autowired
	ClienteRepo cRepo;

	Cliente c;
	Fattura f1;

	@BeforeEach
	void setUp() throws Exception {
		c = new Cliente();
		f1 = new Fattura();

	}

	@Test
	void testCreaFattura() {

		fRepo.save(f1);
	}

	@Test
	void testModificaFattura() {
		fRepo.save(f1);
	}

	@Test
	void testEliminaFattura() {
		fRepo.delete(f1);
	}

	@Test
	void testFindByCliente() {
		cRepo.save(c);
		fRepo.findByCliente(c);
	}

	@Test
	void testFindById() {
		fRepo.findById(1l);
	}

	@Test
	void testFindAllFatture() {
		fRepo.findAll();
	}

	@Test
	void testFindByStadioFattura() {
		fRepo.findByStadioFattura(StatoFattura.EMESSA);
	}

	@Test
	void testFindByDataEmissione() {
		fRepo.findByDataEmissione(LocalDate.now());
	}

	@Test
	void testFindByDataAnno() {
		fRepo.findByAnno(1900);
	}

	@Test
	void testFindByImporto() {
		fRepo.findByImportoBetween(10912.2, 121.2);
	}

}
