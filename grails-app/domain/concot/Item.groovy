package concot

class Item {

    String nome
    
    // Modificando o modo como a representação textual é gerada
    String toString() {
        this.nome
    }
    
    static belongsTo = [categoria:Categoria]

    static constraints = {
        nome nullable:false, blank:false, maxSize:128
        categoria nullable:false
    }
}