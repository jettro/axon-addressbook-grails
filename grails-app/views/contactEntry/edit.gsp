<%@ page import="nl.gridshore.sample.addressbook.ContactEntry" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'contactEntry.label', default: 'ContactEntry')}"/>
  <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>
<body>
<div class="body">
  <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <g:hasErrors bean="${contactEntryInstance}">
    <div class="errors">
      <g:renderErrors bean="${contactEntryInstance}" as="list"/>
    </div>
  </g:hasErrors>
  <g:form method="post">
    <g:hiddenField name="id" value="${contactEntryInstance?.id}"/>
    <g:hiddenField name="version" value="${contactEntryInstance?.version}"/>
    <div class="dialog">
      <table>
        <tbody>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="name"><g:message code="contactEntry.name.label" default="Name"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: contactEntryInstance, field: 'name', 'errors')}">
            <g:textField name="name" value="${contactEntryInstance?.name}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="identifier"><g:message code="contactEntry.identifier.label" default="Identifier"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: contactEntryInstance, field: 'identifier', 'errors')}">
            ${contactEntryInstance?.identifier}
            <g:hiddenField name="identifier" value="${contactEntryInstance?.identifier}"/>
          </td>
        </tr>

        </tbody>
      </table>
    </div>
    <div class="buttons">
      <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}"/></span>
      <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
    </div>
  </g:form>
</div>
</body>
</html>
