package it.epicode.bw.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.javafaker.PhoneNumber;

import it.epicode.bw.auth.entity.Role;
import it.epicode.bw.enums.TipoSocieta;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name="clienti")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_cliente;
private String ragioneSociale;
private String pIva;
private String username;
private String email;
private String psw;
private LocalDate datainserimento;
private LocalDate ultimoContatto;
private Double fatturatoAnnuale;
private String pec;
private PhoneNumber tel;
private String emailContatto;
private String nomeContatto;
private String cognomeContatto;
private PhoneNumber telContatto;
@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
//@JoinTable(name = "users_roles",
//        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
//)
private Set<Role> roles = new HashSet<>();
@Enumerated(EnumType.STRING)
private TipoSocieta tipoSocieta;
// due indirizzi diversi 
@OneToOne
private Indirizzo sedeLegale;
@OneToOne
private Indirizzo sedeOperativa;
@OneToMany(mappedBy = "cliente")
private List<Fattura> fatture;


}
