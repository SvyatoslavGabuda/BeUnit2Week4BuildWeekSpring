package it.epicode.bw.service;

import java.time.LocalDate;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import it.epicode.bw.auth.service.AuthServiceImpl;
import it.epicode.bw.models.Cliente;
import it.epicode.bw.models.Indirizzo;
import it.epicode.bw.repository.ClienteRepo;

@Service
public class ClientiService {

	@Autowired
	ClienteRepo clienteRepo;
	@Autowired
	AuthServiceImpl authService;

	public Cliente creaCliente(String ragioneSociale, String pIva, Double fatturatoAnnuale, Indirizzo indirizzo) {

		Faker fake = Faker.instance(new Locale("it-IT"));
		Cliente c = new Cliente();
		c.setUsername(fake.name() + "." + fake.number());
		c.setRagioneSociale(ragioneSociale);
		c.setPIva(pIva);
		c.setEmail(c.getUsername() + "@mail.com");
		c.setPsw(fake.internet().password(6, 10, true, true));
		c.setDatainserimento(LocalDate.now());
		c.setUltimoContatto(LocalDate.now());
		c.setFatturatoAnnuale(fatturatoAnnuale);
		c.setPec(c.getUsername() + "@pec.it");
		c.setTel(fake.phoneNumber());
		c.setEmailContatto(c.getEmail());
		c.setNomeContatto(fake.name() + "");
		c.setCognomeContatto(fake.name().lastName());
		c.setTelContatto(c.getTel());
		return c;

	}
}
