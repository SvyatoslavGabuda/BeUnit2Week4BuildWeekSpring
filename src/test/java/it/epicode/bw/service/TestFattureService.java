package it.epicode.bw.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import it.epicode.bw.auth.exception.MyAPIException;
import it.epicode.bw.models.Cliente;
import it.epicode.bw.models.Fattura;

@SpringBootTest
class TestFattureService {
	@Autowired
	private FattureService fser;
	@Autowired
	ClientiService cser;
	Pageable pag;
	Fattura creata;

	@BeforeTestClass
	void setUpBeforeClass() throws Exception {
		creata = fser.creaFattura(100.0, 1l);
		System.out.println("fattura creata");
	}

	@AfterTestClass
	void endTest() {
		fser.eliminaFattura(creata.getId_fattura());
		System.out.println("fattura eliminata");
	}

	@BeforeEach
	void setUp() throws Exception {

	}

	@Test
	void test() {
		fser.findAllFatture();
	}

	@Test
	void test2() {
		List<Fattura> f = fser.findByDataAnno(2023);
		f.forEach(fa -> System.out.println(fa.getId_fattura()));
	}

	@Test
	void test3() {
		Page<Fattura> p = fser.findByDataAnnoPageable(pag, 1900);
		assertThat(p instanceof Page<Fattura>);
	}

	@Test
	void test4() {
		Cliente c = cser.findById(54l);
		List<Fattura> lf = fser.findByCliente(c);
		assertTrue(lf.size()>0);
	}
	@Test
	void test5() {
		
//		assertThatExceptionOfType(MyAPIException.class);
		assertThrows(MyAPIException.class, ()->{
			fser.findById(10000);
		});
	}

}
