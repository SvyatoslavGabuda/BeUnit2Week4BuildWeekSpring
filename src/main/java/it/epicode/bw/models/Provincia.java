package it.epicode.bw.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Provincia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_provincia;
private String nome;
private String sigla;
@OneToMany(mappedBy = "provincia")
private List<Comune> comuni;
}
