package it.epicode.bw.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import it.epicode.bw.models.Comuni_italiani;

@Configuration
public class Comuni_italianiConfig {

	@Bean("Comuni")
	@Scope("prototype")
	public Comuni_italiani comuni() {
		return new Comuni_italiani();

	}
}
