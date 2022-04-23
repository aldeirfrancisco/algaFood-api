package com.algaFood.algaFoodapi.domain.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaFood.algaFoodapi.api.mapper.RestauranteMapper;
import com.algaFood.algaFoodapi.api.model.dto.RestauranteDTO;
import com.algaFood.algaFoodapi.api.model.input.restaurante.RestauranteInput;
import com.algaFood.algaFoodapi.domain.exception.EntidadeEmUsoException;
import com.algaFood.algaFoodapi.domain.exception.NegocioException;
import com.algaFood.algaFoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.algaFood.algaFoodapi.domain.model.Restaurante;
import com.algaFood.algaFoodapi.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	 private static final String MSG_RESTAURANTE_EM_USO 
	       = "Restaurante de código %d não pode ser removido, pois está em uso";


	private static final String MSG_COZINHA_NAO_ENCONTRADA 
	       = "Não existe  cadastro de cozinha com código %d";
	 
	 @Autowired
	 private CozinhaService cozinhaService;
	 
	 @Autowired
	 private CidadeService cidadeService;
	 
	 @Autowired
	 private FormaPagamentoService formaPagamentoService;
	 
	 private RestauranteRepository restauranteRepository;
	 
	 private final RestauranteMapper mapper;
	 
	 public RestauranteService(RestauranteRepository restauranteRepository, RestauranteMapper mapper){
		 this.restauranteRepository = restauranteRepository;
		 this.mapper = mapper;
	 }
	 
	 

	public List<RestauranteDTO> listar() {
		 return mapper.toCollectionDto(restauranteRepository.findAll());
	}
	
	public List<RestauranteDTO> restaurantePorNomeFrete(String nome, BigDecimal taxaFreteInicial, BigDecimal         					taxaFreteFinal) {
		 var restaurantes = restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
		 return mapper.toCollectionDto(restaurantes);
	}


	public Restaurante buscar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(()-> new  RestauranteNaoEncontradoException(restauranteId));
		
		
			
		
	}
	
	public RestauranteDTO buscarDTO(Long restauranteId) {
		var restaurante = buscar(restauranteId);
		return mapper.toDto(restaurante);
		
			
		
	}

	@Transactional
	public RestauranteDTO adicionar(RestauranteInput restauranteInput) {
		 var restaurante = mapper.toEntity(restauranteInput);

		 return  mapper.toDto(restauranteRepository.save(salvar(restaurante)));
 
	}
    
	@Transactional
	public RestauranteDTO atualizar(Long id, RestauranteInput restauranteInput) {
		var restaurante = buscar(id);
		mapper.copyToEntity(restauranteInput, restaurante);

		  return mapper.toDto(salvar(restaurante));
	
		 
		 
	}
    
	@Transactional
	public void remover(Long id) {
		
		try {
			
        	restauranteRepository.deleteById(id);
        	restauranteRepository.flush();
        	
    	} catch (EmptyResultDataAccessException e){
             throw new RestauranteNaoEncontradoException(id);	
    		
		 } catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
			  String.format(MSG_RESTAURANTE_EM_USO, id));
		}
    	
		
	}
     
	@Transactional
    public void ativar(Long id) {

        var restaurante = this.buscar(id);

        restaurante.setAtivo(true);

    }

    @Transactional
    public void inativar(Long id) {

        var restaurante = this.buscar(id);

        restaurante.setAtivo(false);

    }

    @Transactional

    public void desassociarFormaPagamento(Long idRestaurante, Long idFormaPagamento) {

        var restaurante = this.buscar(idRestaurante);
        var formaPagamento = formaPagamentoService.buscarOuFalhar(idFormaPagamento);

        if(!restaurante.getFormasPagamento().contains(formaPagamento)){

            throw new NegocioException("Forma de pagamento já está desassociada.");

        }

        restaurante.getFormasPagamento().remove(formaPagamento);

    }

    @Transactional

    public void associarFormaPagamento(Long idRestaurante, Long idFormaPagamento) {

        var restaurante = this.buscar(idRestaurante);
        var formaPagamento = formaPagamentoService.buscarOuFalhar(idFormaPagamento);

        if(restaurante.getFormasPagamento().contains(formaPagamento)){

            throw new NegocioException("Forma de pagamento já está associada.");

        }

        restaurante.getFormasPagamento().add(formaPagamento);

    }

    @Transactional
    public void abrir(Long id) {

        var restaurante = this.buscar(id);
        restaurante.setAberto(true);

    }

    @Transactional

    public void fechar(Long id) {

        var restaurante = this.buscar(id);

        restaurante.setAberto(false);

    }
	
    private Restaurante salvar (Restaurante restaurante) {

            long cozinhaId = restaurante.getCozinha().getId();

            long cidadeId = restaurante.getEndereco().getCidade().getId();

            var cozinha = cozinhaService.buscar(cozinhaId);

            var cidade = cidadeService.buscar(cidadeId);

            restaurante.setCozinha(cozinha);

            restaurante.getEndereco().setCidade(cidade);

            return restauranteRepository.save(restaurante);
    }

}
