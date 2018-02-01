package radios.backend

class BootStrap {

    def init = { servletContext ->
        Role role = Role.findOrCreateByAuthority("ROLE_ADMIN").save()
        User user = User.findByUsername("admin")
        if(!user){
            user = new User([
                    username:"admin",
                    firstName:"admino",
                    lastName:"aasdasd",
                    password:"1234"
            ])
            user.save()
            UserRole.create(user,role)
        }

        Radio.findOrCreateByName("VALENOVENTAICIETESINCO").save()
        NewsCategory.findOrCreateByName("Otros").save()


    }
    def destroy = {
    }
}
