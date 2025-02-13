package com.tgm.AppContatos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tgm.AppContatos.dto.PessoaDTO;
import com.tgm.AppContatos.model.Pessoa;
import com.tgm.AppContatos.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa save(Pessoa pessoa) {
		if (pessoa.getNome() == null) {
			System.out.println("Nome não pode ser nulo");
			return null;
		}
		try {			
			return pessoaRepository.save(pessoa);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public Optional<Pessoa> findById(Long id) {
	    try {
	        return pessoaRepository.findById(id);
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage());
	    }
	}

	public PessoaDTO findPessoaMalaDireta(Long id) {
	    try {
	        Pessoa pessoa = pessoaRepository.findById(id)
	                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada com ID: " + id));
	        return PessoaDTO.from(pessoa);
	    } catch (Exception e) {
	    	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao obter dados de mala direta: " + e.getMessage());
	    }
	}

}
