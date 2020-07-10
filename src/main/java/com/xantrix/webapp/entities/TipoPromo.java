package com.xantrix.webapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "TIPOPROMO")
@Data
public class TipoPromo implements Serializable{

	private static final long serialVersionUID = 7855017314131370073L;

	@Id
	@Column(name = "IDTIPOPROMO")
	private String idTipoPromo;
	@Column(name = "DESCRIZIONE")
	private String descrizione;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tipoPromo")
	//@JsonBackReference
	@JsonIgnore
	private Set<DettPromo> dettPromos = new HashSet<>();
}
