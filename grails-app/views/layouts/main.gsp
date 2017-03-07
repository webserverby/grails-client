<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>
    <asset:stylesheet src="spring-security-ui-register.css"/>
    <g:layoutHead/>

</head>
<body>

    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="${createLink(uri: '/')}">
                    <i class="fa grails-icon">
                        <asset:image src="grails-cupsonly-logo-white.svg"/>
                    </i> Home
                </a>
            </div>

            <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">
                <form class="navbar-form navbar-left" role="search" action="/upload/search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search" name="arg">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <g:pageProperty name="page.nav" />
                    <sec:ifLoggedIn>
                        <sec:ifAllGranted roles="ROLE_ADMIN">
                            <li class="dropdown">
                                <g:link controller="user"><g:message code='main.adminpanel'/></g:link>
                            </li>
                        </sec:ifAllGranted>
                        <li class="dropdown">
                            <g:link controller='logout'><g:message code='main.logout'/></g:link>
                        </li>
                        <li class="dropdown" disabled="true">
                            <g:link><g:message code='main.loggedas'/> <sec:username/></g:link>
                        </li>
                    </sec:ifLoggedIn>
                    <sec:ifNotLoggedIn>
                        <li class="dropdown">
                            <g:link controller='login'><g:message code='main.login'/></g:link>
                        </li>
                    </sec:ifNotLoggedIn>
                </ul>
            </div>
        </div>
    </div>


    <g:layoutBody/>

    <div class="footer" role="contentinfo"></div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <asset:javascript src="application.js"/>
    <asset:javascript src='spring-security-ui-register.js'/>


</body>
</html>
