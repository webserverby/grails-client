<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title><g:message code="list.label" /></title>
</head>
<body>

<div id="list-place" class="content scaffold-list" role="main">
    <h1><g:message code="clients.list"/></h1>
    <table id="rounded-corner">
        <thead>
        <tr>
            <th class="rounded-init"><g:message code="client.id.label"/></th>
            <th class="rounded-init"><g:message code="client.name.label"/></th>
            <th class="rounded-init"><g:message code="client.email.label"/></th>
            <th class="rounded-init"><g:message code="client.street.label"/></th>
            <th class="rounded-init"><g:message code="client.zip.label"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${clientList}" var="client">
            <tr>
                <td>${fieldValue(bean: client, field: "id")}</td>
                <td>${fieldValue(bean: client, field: "name")}</td>
                <td>${fieldValue(bean: client, field: "email")}</td>
                <td>${fieldValue(bean: client, field: "street")}</td>
                <td>${fieldValue(bean: client, field: "zip")}</td>
                <td>
                    <g:link controller="upload" action="edit" id="${client?.id}" class="btn btn-warning">
                        <span class="glyphicon glyphicon-pencil"></span>
                    </g:link>
                </td>
                <td>
                    <g:link controller="upload" action="delete" id="${client?.id}" class="btn btn-danger">
                        <span class="glyphicon glyphicon-trash"></span>
                    </g:link>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
</html>
