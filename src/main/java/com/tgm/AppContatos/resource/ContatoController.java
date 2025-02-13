package com.tgm.AppContatos.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgm.AppContatos.model.Contato;
import com.tgm.AppContatos.service.ContatoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {
	
	@Autowired
    private ContatoService contatoService;

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

}
