package concot

class Categoria {

    //Teste de modifica��o 3
    String nome
    
    static hasMany = [itens:Item]
    
    // Modificando o modo como a representa��o textual � gerada
    String toString() {
        this.nome
    }
    
    static constraints = {
        nome nullable:false, blank:false, maxSize:128, unique:true
    }
}
