package it.epicode.bw.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import it.epicode.bw.models.Indirizzo;

@Configuration
public class IndirizzoConfig {

	@Scope("Indirizzo")
	@Bean("prototype")
	public Indirizzo indirizzo() {
		return indirizzo();
	}
}