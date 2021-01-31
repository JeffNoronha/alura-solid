package br.ce.wcaquino.steps;

import java.time.LocalDate;

import org.junit.Assert;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.NotaAluguel;
import br.ce.wcaquino.servicos.AluguelService;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class AlugarFilmeSteps {

	private Filme filme;
	private AluguelService aluguelService = new AluguelService();
	private NotaAluguel notaAluguel;
	private String erro;
	private String tipoAluguel;
	
	@Dado("^um filme com estoque de (\\d+) unidades$")
	public void umFilmeComEstoqueDeUnidades(int arg1) throws Throwable {
		filme = new Filme();
		filme.setEstoque(arg1);
	}

	@Dado("^que o preço do aluguel seja R\\$ (\\d+)$")
	public void queOPreçoDoAluguelSejaR$(int arg1) throws Throwable {
	   filme.setAluguel(arg1);
	}

	@Quando("^alugar$")
	public void alugar() throws Throwable {
		try {
			notaAluguel = aluguelService.alugar(filme, tipoAluguel);
		}catch (Exception e) {
			erro = e.getMessage();
		}	
	}

	@Então("^o preço do aluguel será R\\$ (\\d+)$")
	public void oPreçoDoAluguelSeráR$(int arg1) throws Throwable {
	    Assert.assertEquals(arg1, notaAluguel.getPreco());
	}

	@Então("^a data de entrega será no dia seguinte$")
	public void aDataDeEntregSeráNoDiaSeguinte() throws Throwable {
		LocalDate diaSeguinte = LocalDate.now().plusDays(1);
		LocalDate dataRetorno = notaAluguel.getDataEntrega();
		Assert.assertEquals(diaSeguinte, dataRetorno);
	}

	@Então("^o estoque do filme será (\\d+) unidade$")
	public void oEstoqueDpFilmeSeráUnidade(int arg1) throws Throwable {
		Assert.assertEquals(arg1, filme.getEstoque());
	}
	
	@Então("^não será possível por falta de estoque$")
	public void nãoSeráPossívelPorFaltaDeEstoque() throws Throwable {
		Assert.assertEquals("Filme sem estoque", erro);
	}
	
	@E("^que o tipo do aluguel seja extendido$")
	public void queOTipoDoAluguelSejaExtendido() throws Throwable {
		tipoAluguel = "extendido";
	}

	@Então("^a data de entrega será em (\\d+) dias$")
	public void aDataDeEntregaSeráEmDias(int arg1) throws Throwable {
		LocalDate dataEsperada = LocalDate.now().plusDays(3);
		LocalDate dataReal = notaAluguel.getDataEntrega();
		
		Assert.assertEquals(dataEsperada, dataReal);
	}

	@Então("^a pontuação será de (\\d+) pontos$")
	public void aPontuaçãoSeráDePontos(int arg1) throws Throwable {
		Assert.assertEquals(arg1, notaAluguel.getPontuacao());
	}


}
