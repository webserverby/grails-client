package migration

class BootStrap {

    def init = { servletContext ->

        def adminRole = new SecRole(authority: 'ROLE_ADMIN').save()
        def userRole = new SecRole(authority: 'ROLE_USER').save()

        def user = new SecUser(username: 'user', password: 'user', email: 'user@gmail.com',
                enabled: true, accountExpired: false, accountLocked: false, passwordExpired: false,
                firstName: 'user', lastName: 'user', telephone: '223344').save()


        def admin = new SecUser(username: 'admin', password: 'admin', email: 'admin@gmail.com',
                enabled: true, accountExpired: false, accountLocked: false, passwordExpired: false,
                firstName: 'admin', lastName: 'admin', telephone: '445566').save()

        SecUserSecRole.create(user, userRole)
        SecUserSecRole.create(admin, adminRole)
        SecUserSecRole.create(admin, userRole)
        SecUserSecRole.withSession {
            it.flush()
            it.clear()
        }
    }

    def destroy = {
    }
}
