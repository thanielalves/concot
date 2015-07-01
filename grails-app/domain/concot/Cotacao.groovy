package concot

class Cotacao {

    BigDecimal valor
    
    Date data
    
    String toString() {
        this.valor
    }
    
    static belongsTo = [item:Item, moeda:Moeda, fornecedor:Fornecedor]
    
    static constraints = {
    }
}