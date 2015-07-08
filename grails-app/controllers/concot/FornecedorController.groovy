package concot
import grails.validation.Validateable

class FornecedorController implements Validateable {

    static scaffold = Fornecedor

    //action da pagina de envio
    def comunicacao() {
		[fornecedores:Fornecedor.list(), mensagem:new EnvioEmail()]
	}

	def comunicacaoService

	def enviarMensagem(EnvioEmail envio) {
		envio.validate()
		if (envio.hasErrors()) {
			// tratamos o erro
		} else {
			flash.message = "Mensagem enviada com sucesso"
			// Usamos nosso serviço
			comunicacaoService.enviarMensagem(envio.fornecedor,
			envio.email,
			envio.mensagem)
			render(view:'comunicacao')
		}
	}

	//action de envio da mensagem
	def enviarMensagem(EnvioEmail envio) {
		withForm{
			// submissão esperada
			envio.validate()
			if (envio.hasErrors()) {
				// Erro encontrado
				flash.message = "Erro de validação"
				render(view:"comunicacao",
				model:[mensagem:envio,fornecedores:Fornecedor.list()])
			} else {
				// Mensagem enviada (código omitido)
				flash.message = "Mensagem enviada com sucesso"
				render(view:'comunicacao')
			}
		}.invalidToken {
		// submissão duplicada detectada
			flash.message = "Ops! Aguarde um instante para enviar E-mail novamente!"
			render(view:'comunicacao')
		}
	}

	//Erro na compilação pedia a implementação desses dois metodos.
    boolean nullable(){}
    java.lang.Class annotationType(){}
		
}