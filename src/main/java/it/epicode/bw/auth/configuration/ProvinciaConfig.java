package it.epicode.bw.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import it.epicode.bw.models.Provincia;

@Configuration
public class ProvinciaConfig {

	@Bean("Procincia")
	@Scope("prototype")
	public Provincia provincia() {
		return provincia();
	}
}
