package br.ce.wcaquino.servicos;

import java.time.LocalDate;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.NotaAluguel;

public class AluguelService {

	public NotaAluguel alugar(Filme filme, String tipoAluguel) {
		if(filme.getEstoque() == 0) {
			throw new RuntimeException("Filme sem estoque");
		}
		NotaAluguel nota = new NotaAluguel();
		if("extendido".equals(tipoAluguel)) {
			LocalDate dataEntrega = LocalDate.now().plusDays(3);
			nota.setDataEntrega(dataEntrega);
			nota.setPreco(filme.getAluguel() * 2);
			nota.setPontuacao(2);
		} else {
			LocalDate dataEntrega = LocalDate.now().plusDays(1);
			nota.setDataEntrega(dataEntrega);
			nota.setPreco(filme.getAluguel());
		}
		filme.setEstoque(filme.getEstoque() - 1);
		return nota;
	}

}
