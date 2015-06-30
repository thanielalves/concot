package concot

class Categoria {

    String nome
    
    static hasMany = [itens:Item]
    
    // Modificando o modo como a representação textual é gerada
    String toString() {
        this.nome
    }
    
    static constraints = {
        nome nullable:false, blank:false, maxSize:128, unique:true
    }
}