package com.algaFood.algaFoodapi.modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
public class Cidade {
	    @EqualsAndHashCode.Include
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String nome;

	    @ManyToOne
	    @JoinColumn(name = "estado_id")
	    private Estado estado;
}
