package com.tgm.AppContatos.dto;

import com.tgm.AppContatos.model.Pessoa;

public record PessoaDTO(Long id, String nome, String malaDireta) {
	
	public static PessoaDTO from(Pessoa pessoa) {
        String malaDireta = String.format("%s – CEP: %s – %s/%s",
                                          pessoa.getEndereco(),
                                          pessoa.getCep(),
                                          pessoa.getCidade(),
                                          pessoa.getUf());
        return new PessoaDTO(pessoa.getId(), pessoa.getNome(), malaDireta);
    }

}
