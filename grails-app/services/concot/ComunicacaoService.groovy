package concot

import grails.transaction.Transactional

@Transactional
class ComunicacaoService {

	// Definindo o escopo prototype
	static scope = "prototype"

    def enviarMensagem(Fornecedor fornecedor,
		String email,
		String mensagem) {
		// Executa a tarefa de envio de e-mail
	}
}
