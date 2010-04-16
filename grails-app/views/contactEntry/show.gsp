<%@ page import="nl.gridshore.sample.addressbook.ContactEntry" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'contactEntry.label', default: 'ContactEntry')}"/>
  <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>
<body>
<div class="body">
  <h1><g:message code="default.show.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <div class="dialog">
    <table>
      <tbody>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="contactEntry.id.label" default="Id"/></td>

        <td valign="top" class="value">${fieldValue(bean: contactEntryInstance, field: "id")}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="contactEntry.name.label" default="Name"/></td>

        <td valign="top" class="value">${fieldValue(bean: contactEntryInstance, field: "name")}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name"><g:message code="contactEntry.identifier.label" default="Identifier"/></td>

        <td valign="top" class="value">${fieldValue(bean: contactEntryInstance, field: "identifier")}</td>

      </tr>
      </tbody>
    </table>
  </div>
  <div class="list">
    <table>
      <thead>
      <tr>
        <td>&nbsp;</td>
        <g:sortableColumn property="streetAndNumber" title="${message(code: 'addressEntry.streetAndNumber.label', default: 'Street And Number')}"/>
        <g:sortableColumn property="zipCode" title="${message(code: 'addressEntry.zipCode.label', default: 'Zip Code')}"/>
        <g:sortableColumn property="city" title="${message(code: 'addressEntry.city.label', default: 'City')}"/>
        <g:sortableColumn property="addressType" title="${message(code: 'addressEntry.addressType.label', default: 'Address Type')}"/>
      </tr>
      </thead>
      <tbody>
      <g:each in="${addressEntryInstanceList}" status="i" var="addressEntryInstance">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
          <td>
            <g:link action="deleteAddress" id="${addressEntryInstance.id}"><img src="${resource(dir:'images/skin',file:'database_delete.png')}" alt="delete"></g:link>
          </td>
          <td>${fieldValue(bean: addressEntryInstance, field: "streetAndNumber")}</td>
          <td>${fieldValue(bean: addressEntryInstance, field: "zipCode")}</td>
          <td>${fieldValue(bean: addressEntryInstance, field: "city")}</td>
          <td>${fieldValue(bean: addressEntryInstance, field: "addressType")}</td>
        </tr>
      </g:each>
      </tbody>
    </table>
  </div>
  <div class="buttons">
    <g:form>
      <g:hiddenField name="id" value="${contactEntryInstance?.id}"/>
      <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
      <span class="button"><g:actionSubmit class="add" action="registerAddress" value="${message(code: 'orderEntry.button.registerAddress.label', default: 'Register Address')}"/></span>
      <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
    </g:form>
  </div>
</div>
</body>
</html>
