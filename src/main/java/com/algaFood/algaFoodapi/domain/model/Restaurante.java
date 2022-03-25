package com.algaFood.algaFoodapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algaFood.algaFoodapi.Grupos;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull
//    @NotEmpty
    @NotBlank(groups = Grupos.CadastroRestaurante.class)
    private String nome;
    
//    @DecimalMin("1")
    @PositiveOrZero(groups = Grupos.CadastroRestaurante.class)
    @JoinColumn(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;
    
//  @JsonIgnoreProperties("hibernateLazyInitializer")
//  @JsonIgnore
    @Valid
    @NotNull(groups = Grupos.CadastroRestaurante.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;
    
    @JsonIgnore
    @Embedded
    private Endereco endereco;
    
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime dataCadastro;
    
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime dataAtualizacao;
    
    //ToMany default lazy
    //(fetch = FetchType.EAGER)
//    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private Set<FormaPagamento> formasPagamento = new HashSet<>();
    
    
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos;
    
    private Boolean ativo = Boolean.TRUE;
    
}
