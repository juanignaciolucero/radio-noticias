package backend

class BootStrap {

    def init = { servletContext ->
        User user = User.findByUsername("admin")
        if(!user){
            user = new User([
                    username:"admin",
                    firstName:"admino",
                    lastName:"aasdasd",
                    password:"1234"
            ])
            user.save()
        }
        Radio.findOrCreateByName("VALENOVENTAICIETESINCO").save()
        NewsCategory.findOrCreateByName("Otros").save()
    }
    def destroy = {
    }
}
