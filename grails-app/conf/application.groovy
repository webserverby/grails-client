
grails.plugin.springsecurity.userLookup.userDomainClassName = 'migration.SecUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'migration.SecUserSecRole'
grails.plugin.springsecurity.authority.className = 'migration.SecRole'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
[pattern: '/',               access: ['permitAll']],
[pattern: '/error',          access: ['permitAll']],
[pattern: '/index',          access: ['permitAll']],
[pattern: '/index.gsp',      access: ['permitAll']],
[pattern: '/shutdown',       access: ['permitAll']],
[pattern: '/assets/**',      access: ['permitAll']],
[pattern: '/**/js/**',       access: ['permitAll']],
[pattern: '/**/css/**',      access: ['permitAll']],
[pattern: '/**/fonts/**',    access: ['permitAll']],
[pattern: '/**/images/**',   access: ['permitAll']],
[pattern: '/**/favicon.ico', access: ['permitAll']],
[pattern: '/user/**', 		 access: ['ROLE_ADMIN']],
[pattern: '/role/**', 		 access: ['ROLE_ADMIN']],
[pattern: '/upload/**', 	 access: ['ROLE_ADMIN']],
[pattern: '/upload/**', 	 access: ['ROLE_USER']],
[pattern: '/securityInfo/**',access: ['ROLE_ADMIN']],
[pattern: '/plugins/**',access: ['ROLE_USER']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
        [pattern: '/assets/**',      filters: 'none'],
        [pattern: '/**/js/**',       filters: 'none'],
        [pattern: '/**/css/**',      filters: 'none'],
        [pattern: '/**/images/**',   filters: 'none'],
        [pattern: '/**/fonts/**',    filters: 'none'],
        [pattern: '/**/favicon.ico', filters: 'none'],
        [pattern: '/**',             filters: 'JOINED_FILTERS']
]
grails.plugin.springsecurity.logout.postOnly = false// allows logout to work


grails {
    mail {
        host = "smtp.gmail.com"
        port = 587
        username = "ticktwo.ticktwo@gmail.com"
        password = "TickTwoAdmin"
        props = ["mail.smtp.auth":"true",
                 "mail.smtp.starttls.enable" : "true"]


    }
}





