<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title><g:message code="client.edit.label"/></title>
</head>
<body>
<a href="#edit-placeBooking" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>
<div id="edit-placeBooking" class="content scaffold-create" role="main">
    <h1><g:message code="client.edit.label"/></h1>

    <g:form method="post">
        <g:hiddenField name="id" value="${client?.id}"/>
        <g:hiddenField name="version" value="${client?.version}"/>

        <h2><g:message code="client.update"/></h2>
        <div data-role="fieldcontain">
            <label for="name"><g:message code="client.name.label" default="Name" /></label>
            <g:textField name="name" required="required" value="${client?.name}" />
        </div>

        <div data-role="fieldcontain">
            <label for="email"><g:message code="client.email.label" default="Email" /></label>
            <g:field type="email" name="email" value="${client?.email}" disabled="disabled"/>
        </div>

        <div data-role="fieldcontain">
            <label for="street"><g:message code="client.street.label" default="Street" /></label>
            <g:textField name="street" required="required" value="${client?.street}"  />
        </div>

        <div data-role="fieldcontain">
            <label for="zip"><g:message code="client.zip.label" default="Zip" /></label>
            <g:textField name="zip" value="${client?.zip}" />
        </div>

        <div id="actionbar" class="menuButton">
            <g:actionSubmit action="update" value="Save" />
        </div>
    </g:form>
</div>
</body>
</html>
