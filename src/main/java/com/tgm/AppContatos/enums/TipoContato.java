package com.tgm.AppContatos.enums;

public enum TipoContato {
	
	TELEFONE(0),
	CELULAR(1),
	EMAIL(2);
	
	private final int codigoContato;
	
	private TipoContato(int codigoContato) {
		this.codigoContato = codigoContato;
	}
	
	public int getCodigoContato() {
		return codigoContato;
	}

	public static TipoContato valueOf(int tipoContato) {
		for (TipoContato value : TipoContato.values()) {
			if (value.getCodigoContato() == tipoContato) {
				return value;
			}
		}
		throw new IllegalArgumentException("Tipo de contato inválido");
	}

}
