package concot

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Shared
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ListaCotacoesService)
// Recebe uma lista com o nome das nossas
// classes de domínio
@Mock([ListaCotacoes, Cotacao, Item,
	Fornecedor, Moeda, Categoria,
	LimiteCotacao, CotacaoLista])
class ListaCotacoesServiceSpec extends Specification {

	/*
	Lista que contém uma restrição para um item
	"Motor" cujo valor deve variar entre 10 e 1000 Reais
	*/
	@Shared listaTeste
	/*
	Uma lista que não contém restrição alguma
	*/
	@Shared listaVazia
	/* Uma cotação para o item "Motor" que vale 100 Reais */
	@Shared cotacaoValida
	/* Uma cotação para o item "Motor" que vale 1 Real */
	@Shared cotacaoInvalida

    def setup() {
    	/*
			Código que inicia os atributos acima,
			"persistindo-os" na versão em memória do GORM
			disponibilizada pelo Grails para testes
			unitários
		*/
    }

    def cleanup() {
    }

    void "situações esperadas para a inclusão de cotações na lista"() {
		expect:"o que esperamos"

			resultado == (service.incluirCotacao(lista, cotacao, data) != null)

		where:"alguns exemplos"

			lista | cotacao | data | resultado
			listaTeste | cotacaoValida | new Date() | true
			listaTeste | cotacaoValida | null | false
			listaTeste | cotacaoInvalida | new Date() | false
			null | cotacaoValida | new Date() | false
			listaVazia | cotacaoInvalida | new Date() | true
			listaVazia | cotacaoValida | new Date() | true
	}

    void "verificando a sanidade do serviço para uma cotação nula"() {
		when:"quando"
			def cotacao = null
			def lista = new ListaCotacoes()
			def data = new Date()
		then:"então..."
			service.incluirCotacao(lista, cotacao, data) == null
	}

	void "uma cotação cujo valor esteja no limite deve poder ser inserida"() {
		when:
			def fornecedor = Fornecedor.findOrSaveByNomeAndEmail("Juca", "juca@juca.com")
			def categoria = Categoria.findOrSaveByNome("Equipamento")
			def item = Item.findOrSaveByCategoriaAndNome(categoria, "Motor")
			def moeda = Moeda.findOrSaveByNomeAndSimbolo("Real", 'R$')
			def cotacao = Cotacao.findOrSaveByFornecedorAndItemAndMoedaAndValor(fornecedor, item, moeda, 100)
			def lista = ListaCotacoes.findOrSaveByNome("Lista de teste")
			def limite = LimiteCotacao.findOrSaveByListaAndItemAndMoeda(lista, item, moeda)
			limite.valorMinimo = 10
			limite.valorMaximo = 1000
			def cotacaoForaDoLimite = Cotacao.findOrSaveByFornecedorAndItemAndMoedaAndValor(fornecedor, item, moeda, 1)
			limite.save()
		then:
			service.incluirCotacao(lista, cotacao, new Date()) != null
			service.incluirCotacao(lista, cotacaoForaDoLimite, new Date()) == null
	}
}
