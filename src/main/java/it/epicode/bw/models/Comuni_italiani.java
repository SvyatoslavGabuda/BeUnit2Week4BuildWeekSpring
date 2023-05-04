package it.epicode.bw.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

	@JsonIgnoreProperties(value = "comune")
	@OneToMany(mappedBy = "comune", fetch = FetchType.EAGER)
	private List<Indirizzo> indirizzo = new ArrayList<Indirizzo>();

	@Override
	public String toString() {
		return "Comuni_italiani [id_comuneItaliano=" + id_comuneItaliano + ", Codice_Provincia=" + Codice_Provincia
				+ ", Progressivo_del_Comune=" + Progressivo_del_Comune + ", Denominazione_in_italiano="
				+ Denominazione_in_italiano + ", Nome_Provincia=" + Nome_Provincia + "]";
	}
	
	

}
