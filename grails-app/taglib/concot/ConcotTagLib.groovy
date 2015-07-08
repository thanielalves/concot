package concot

class ConcotTagLib {
	// Nosso namespace. Evita conflitos de nomes de tags.
	static namespace = 'concot'

    static defaultEncodeAs = [taglib:'html', imagem:'raw']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    /*
	Tag que gera uma imagem para um
	@attr item Objeto do tipo Item cuja imagem será renderizada caso exista.
	*/
	def imagem = {attrs, body ->
		if (attrs.item?.imagem) {
			def link = g.createLink(controller:'item', action:'imagem', id:item.id)
			print 'Estou na taglib'
			out << "<a href=\'${link}\'>"
		}else{
			// simplesmente passamos a execução de corpo()
			// para ser renderizada!
			print 'Imagem é vazia'
			out << body()
		}
	}
}
