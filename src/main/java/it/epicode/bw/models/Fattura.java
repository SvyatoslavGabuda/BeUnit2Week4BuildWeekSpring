package it.epicode.bw.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import it.epicode.bw.enums.StatoFattura;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "fatture")
public class Fattura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_fattura;
private Integer anno;
private LocalDate dataEmissione;
//bigdecimal???
private Double importo;
private Integer numeroFattura;
@ManyToOne
private Cliente cliente;
//da vedere domani:
@Enumerated(EnumType.STRING)
private StatoFattura stadioFattura;
}
