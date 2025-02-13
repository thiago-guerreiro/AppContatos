package com.tgm.AppContatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgm.AppContatos.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
	
}
