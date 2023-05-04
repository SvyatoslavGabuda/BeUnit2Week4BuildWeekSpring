package it.epicode.bw.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "indirizzi")
public class Indirizzo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_indirizzo;
private String via;
private String civico;
private String Localita;
private int cap;
@ManyToOne
@JsonIgnoreProperties(value = "indirizzo")
private Comuni_italiani comune;


}
