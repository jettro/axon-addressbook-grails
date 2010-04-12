<%@ page import="nl.gridshore.sample.addressbook.ContactEntry" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'contactEntry.label', default: 'ContactEntry')}"/>
  <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>
<body>
<div class="body">
  <h1><g:message code="default.create.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <g:hasErrors bean="${contactEntryInstance}">
    <div class="errors">
      <g:renderErrors bean="${contactEntryInstance}" as="list"/>
    </div>
  </g:hasErrors>
  <g:form action="save" method="post">
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
        </tbody>
      </table>
    </div>
    <div class="buttons">
      <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
    </div>
  </g:form>
</div>
</body>
</html>
