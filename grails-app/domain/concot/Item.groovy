package concot

class Item {

    String nome
    
    String toString() {
        this.nome
    }
    
    static belongsTo = [categoria:Categoria]
    
    static constraints = {
        nome nullable:false, blank:false, maxSize:128
        categoria nullable:false
    }
}