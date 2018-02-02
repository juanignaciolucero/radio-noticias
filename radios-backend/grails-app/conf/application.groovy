grails.plugin.springsecurity.filterChain.chainMap = [
    //Stateless chain
    [
        pattern: '/api/**',
        filters: 'JOINED_FILTERS,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'
    ],
    //Traditional chain
    [
        pattern: '/**',
        filters: 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'
    ]
]


grails.plugin.springsecurity.rest.token.validation.enableAnonymousAccess = true
grails.plugin.springsecurity.rest.token.validation.useBearerToken = false
grails.plugin.springsecurity.rest.token.validation.headerName = 'X-Auth-Token'

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'radios.backend.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'radios.backend.UserRole'
grails.plugin.springsecurity.authority.className = 'radios.backend.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	//[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]



