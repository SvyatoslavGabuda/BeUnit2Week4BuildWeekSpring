package it.epicode.bw.models;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "comuni-italiani")
public class Comuni_italiani {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_comuneItaliano;
	private String Codice_Provincia;
	private String Progressivo_del_Comune;
	private String Denominazione_in_italiano;
	private String Nome_Provincia;
	
	@JsonIgnoreProperties("comune")
	@OneToMany(mappedBy="comune")
	private List<Indirizzo> indirizzo;

}
