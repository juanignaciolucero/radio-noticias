package radios.backend

class User {
    String firstName
    String lastName
    String username
    String password
    static constraints = {
        firstName nullable: false,blank:false
        lastName nullable: false,blank:false
        username nullable: false,blank:false,unique:true
        password nullable: false,blank:false

    }
}
