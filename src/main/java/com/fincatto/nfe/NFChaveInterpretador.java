package com.fincatto.nfe;

import java.util.Date;
import java.util.GregorianCalendar;

public class NFChaveInterpretador {
	
	private final String chave;
	
	public NFChaveInterpretador(final String chave) {
		if (chave == null || chave.replaceAll("\\D", "").length() != 44) {
			throw new IllegalArgumentException(String.format("A chave deve ter exatos 44 caracteres numericos: %s", chave));
		}
		this.chave = chave.replaceAll("\\D", "");
	}
	
	public NFUnidadeFederativa getUnidadeFederativa() {
		return NFUnidadeFederativa.valueOfCodigo(this.chave.substring(0, 2));
	}
	
	public Date getDataEmissao() {
		return new GregorianCalendar(this.getDataEmissaoAno(), this.getDataEmissaoMes(), 1).getTime();
	}
	
	private int getDataEmissaoMes() {
		return Integer.parseInt(this.chave.substring(4, 6)) - 1;
	}
	
	private int getDataEmissaoAno() {
		return 2000 + Integer.parseInt(this.chave.substring(2, 4));
	}
	
	public String getCnpjEmitente() {
		return this.chave.substring(6, 20);
	}
	
	public String getModelo() {
		return this.chave.substring(20, 22);
	}
	
	public String getSerie() {
		return this.chave.substring(22, 25);
	}
	
	public String getNumero() {
		return this.chave.substring(25, 34);
	}
	
	public String getFormaEmissao() {
		return this.chave.substring(34, 35);
	}
	
	public String getCodigoNumerico() {
		return this.chave.substring(35, 43);
	}
	
	public String getDV() {
		return this.chave.substring(43, 44);
	}
	
	public boolean isEmitidaContingenciaSCAN() {
		return this.getSerie().matches("9[0-9]{2}");
	}
	
	public String getFormatado() {
		String chaveOriginal = this.chave.toString(); // copiar a string original para nao modifica-la
		final StringBuilder formatado = new StringBuilder();
		while (chaveOriginal.length() > 0) {
			formatado.append(chaveOriginal.substring(0, 4)).append(" ");
			chaveOriginal = chaveOriginal.substring(4);
		}
		return formatado.toString().trim();
	}
}
