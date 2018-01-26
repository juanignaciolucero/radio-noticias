package backend

class Usuario {
    String firstName
    String lastName
    String user
    String pass
    static constraints = {
        firstName nullable: false,blank:false
        lastName nullable: false,blank:false
        user nullable: false,blank:false//unique:true ???
        pass nullable: false,blank:false

    }
}
