package concot
import org.grails.databinding.BindingFormat

class Cotacao {

    BigDecimal valor
    
    //datas de cotação no formato dd/MM/yyyy
    @BindingFormat('dd/MM/yyyy')
    Date data
    
    String toString() {
        this.valor
    }
    
    static belongsTo = [item:Item, moeda:Moeda, fornecedor:Fornecedor]
    
    static constraints = {
    }
}