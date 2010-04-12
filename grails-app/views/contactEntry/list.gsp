
<%@ page import="nl.gridshore.sample.addressbook.ContactEntry" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'contactEntry.label', default: 'ContactEntry')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'contactEntry.id.label', default: 'Id')}" />

                            <g:sortableColumn property="identifier" title="${message(code: 'contactEntry.identifier.label', default: 'Identifier')}" />

                            <g:sortableColumn property="name" title="${message(code: 'contactEntry.name.label', default: 'Name')}" />

                            <th>&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${contactEntryInstanceList}" status="i" var="contactEntryInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                          <td><g:link action="show" id="${contactEntryInstance.id}">${fieldValue(bean: contactEntryInstance, field: "id")}</g:link></td>


                          <td>${fieldValue(bean: contactEntryInstance, field: "identifier")}</td>

                          <td>${fieldValue(bean: contactEntryInstance, field: "name")}</td>

                          <td>add images</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${contactEntryInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
