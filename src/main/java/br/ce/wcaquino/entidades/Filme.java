package br.ce.wcaquino.entidades;

public class Filme {
	
	private int estoque;
	
	private int aluguel;
	
	public void setEstoque(int estoque) {
		this.estoque = estoque;		
	}

	public void setAluguel(int aluguel) {
		this.aluguel = aluguel;
	}

	public int getAluguel() {
		return aluguel;
	}

	public int getEstoque() {
		return estoque;
	}

}
