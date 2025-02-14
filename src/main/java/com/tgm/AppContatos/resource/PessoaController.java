package com.tgm.AppContatos.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tgm.AppContatos.dto.PessoaDTO;
import com.tgm.AppContatos.model.Pessoa;
import com.tgm.AppContatos.service.PessoaService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
	
	@Autowired
    private PessoaService pessoaService;
	
	@Operation(summary = "Cria uma nova pessoa", 
			description = "Este endpoint cria uma nova pessoa e salva no banco de dados")
	@PostMapping
	public ResponseEntity<Pessoa> salvarPessoa(@RequestBody @Valid Pessoa pessoa) {
	    try {
	        return ResponseEntity.ok(pessoaService.save(pessoa));
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
	
	@Operation(summary = "Retorna os dados de uma pessoa por ID", 
			description = "Este endpoint retorna os dados de uma pessoa através do seu ID")
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> findPessoaById(@PathVariable @Positive Long id) {
	    try {
	        Optional<Pessoa> pessoa = pessoaService.findById(id);
	        return pessoa.map(ResponseEntity::ok)
	                     .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
	
	@Operation(summary = "Retorna os dados de uma pessoa por ID para mala direta", 
			description = "Este endpoint retorna os dados de uma pessoa para mala direta através do seu ID")
	@GetMapping("/maladireta/{id}")
	public ResponseEntity<PessoaDTO> findPessoaMalaDireta(@PathVariable @Positive Long id) {
	    try {
	        PessoaDTO pessoaDTO = pessoaService.findPessoaMalaDireta(id);
	        return ResponseEntity.ok(pessoaDTO);
	    } catch (ResponseStatusException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
	
	@Operation(summary = "Lista todas as pessoas", 
			description = "Este endpoint retorna todas as pessoas cadastradas no sistema")
	@GetMapping
	public ResponseEntity<List<Pessoa>> findAllPessoas() {
	    try {
	        List<Pessoa> pessoas = pessoaService.findAll();
	        return ResponseEntity.ok(pessoas);
	    } catch (ResponseStatusException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
	
	@Operation(summary = "Atualiza uma pessoa existente", 
			description = "Este endpoint permite atualizar os dados de uma pessoa")
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> updatePessoa(@PathVariable @Positive Long id, @RequestBody Pessoa pessoa) {
	    try {
	        if (pessoaService.findById(id).isEmpty()) {
	            return ResponseEntity.notFound().build();
	        }
	        pessoa.setId(id);
	        Pessoa updatedPessoa = pessoaService.save(pessoa);
	        return ResponseEntity.ok(updatedPessoa);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
	
	@Operation(summary = "Remove uma pessoa por ID", 
			description = "Este endpoint remove uma pessoa do sistema pelo seu ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePessoa(@PathVariable @Positive Long id) {
	    try {
	    	if (!pessoaService.findById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            pessoaService.deleteById(id);
            return ResponseEntity.noContent().build();
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	
}
