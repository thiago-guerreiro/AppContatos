package com.tgm.AppContatos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgm.AppContatos.model.Contato;
import com.tgm.AppContatos.model.Pessoa;
import com.tgm.AppContatos.repository.ContatoRepository;
import com.tgm.AppContatos.repository.PessoaRepository;

@Service
public class ContatoService {
	
	@Autowired
    private ContatoRepository contatoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Contato save(Contato contato) {
		try {
			if (contato.getPessoa().getId() != null) {
				Optional<Pessoa> findPessoa = pessoaRepository.findById(contato.getPessoa().getId());
				if (findPessoa.isEmpty()) {
					//return ResponseEntity.notFound().build();
					return null;
				} else {
					contato.setPessoa(findPessoa.get());
					return contatoRepository.save(contato);
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
		return contato;
	}

}
