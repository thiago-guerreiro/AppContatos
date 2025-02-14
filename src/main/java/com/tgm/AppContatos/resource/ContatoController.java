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

import com.tgm.AppContatos.model.Contato;
import com.tgm.AppContatos.service.ContatoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {
	
	@Autowired
	private ContatoService contatoService;
	
	 @Operation(summary = "Adiciona um novo contato a uma pessoa", 
			description = "Este endpoint permite adicionar um novo contato a uma pessoa existente")
	 @PostMapping
	 public ResponseEntity<Contato> salvarContato(@RequestBody @Valid Contato contato) {
		 try {
			 Contato novoContato = contatoService.save(contato);
		     if (novoContato == null) {
		    	 return ResponseEntity.badRequest().build();
			}
		    return ResponseEntity.ok(novoContato);
		} catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	 }

	 @Operation(summary = "Retorna os dados de um contato por ID", 
				description = "Este endpoint retorna os dados de um contato através do seu ID")
	 @GetMapping("/{id}")
	 public ResponseEntity<Contato> getContatoById(@PathVariable @Positive Long id) {
		 try {
			 Optional<Contato> contato = contatoService.findById(id);
			 return contato.map(ResponseEntity::ok)
								.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	 }

	 @Operation(summary = "Lista todos os contatos de uma pessoa", 
				description = "Este endpoint retorna todos os contatos de uma pessoa através do ID da pessoa")
	 @GetMapping("/pessoa/{idPessoa}")
	 public ResponseEntity<List<Contato>> getContatosByPessoaId(@PathVariable @Positive Long idPessoa) {    
	     try {
	         List<Contato> contatos = contatoService.findAllByPessoaId(idPessoa);
	         if (contatos.isEmpty()) {
	             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	         }
	         return ResponseEntity.ok(contatos);
	     } catch (RuntimeException e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	     }
	 }

	 @Operation(summary = "Atualiza um Contato existente", 
				description = "Este endpoint permite atualizar os dados de um contato")
	 @PutMapping("/{id}")
	 public ResponseEntity<Contato> updateContato(@PathVariable @Positive Long id, @RequestBody Contato contato) {
		 try {
			 if (contatoService.findById(id).isEmpty()) {
				 return ResponseEntity.notFound().build();
			 }
			 contato.setId(id);
			 Contato updatedContato = contatoService.save(contato);
			 return ResponseEntity.ok(updatedContato);
		 } catch (IllegalArgumentException e) {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		 } catch (RuntimeException e) {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		 } 
	 }

	 @Operation(summary = "Remove um contato por ID", 
				description = "Este endpoint remove um contato do sistema pelo seu ID")
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Void> deleteContato(@PathVariable @Positive Long id) {
		 try {
			 if (!contatoService.findById(id).isPresent()) {
				 return ResponseEntity.notFound().build();
			 }
			 contatoService.deleteById(id);
			 return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	 }
	 
}
