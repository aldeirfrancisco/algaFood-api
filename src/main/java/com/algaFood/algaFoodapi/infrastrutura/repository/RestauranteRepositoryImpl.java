package com.algaFood.algaFoodapi.infrastrutura.repository;

import static com.algaFood.algaFoodapi.infrastrutura.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.algaFood.algaFoodapi.infrastrutura.repository.spec.RestauranteSpecs.comNomeSemelhate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaFood.algaFoodapi.domain.model.Restaurante;
import com.algaFood.algaFoodapi.domain.repository.RestauranteRepository;
import com.algaFood.algaFoodapi.domain.repository.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries  {
	
  @PersistenceContext
  private EntityManager manager; 
  
  @Autowired @Lazy
  private RestauranteRepository restauranteRepository;
  
  @Override
  public List<Restaurante> find(String nome,  BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
	  
	   CriteriaBuilder builder = manager.getCriteriaBuilder();
	   
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);//interface responsavél
                                                                              //por momtar a estrutura as query
	    Root<Restaurante> root =criteria.from(Restaurante.class);
	    var predicates =  new ArrayList<Predicate>();
	    if(StringUtils.hasText(nome)) {
	    	predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
	    }
	    if(taxaFreteInicial != null) {
	    	predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
	    }
	    if(taxaFreteInicial != null) {
	    	predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
	    }
	   criteria.where(predicates.toArray(new Predicate[0]));
	   
	  return manager.createQuery(criteria).getResultList();
    }

	@Override
	public List<Restaurante> findComFreteGratis(String nome) {
		return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhate(nome)));
	}


  
  
}
