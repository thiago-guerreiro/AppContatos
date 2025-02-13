package com.tgm.AppContatos.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgm.AppContatos.model.Pessoa;
import com.tgm.AppContatos.service.PessoaService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
	
	@Autowired
    private PessoaService pessoaService;

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

}
