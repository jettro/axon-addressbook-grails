<%@ page import="nl.gridshore.sample.addressbook.AddressEntry" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'addressEntry.label', default: 'AddressEntry')}"/>
  <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
  <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></span>
</div>
<div class="body">
  <h1><g:message code="default.create.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <g:hasErrors bean="${addressEntryInstance}">
    <div class="errors">
      <g:renderErrors bean="${addressEntryInstance}" as="list"/>
    </div>
  </g:hasErrors>
  <g:form action="saveAddress" method="post">
    <div class="dialog">
      <table>
        <tbody>
        <tr class="prop">
          <td valign="top" class="name">
            <label for="contactIdentifier"><g:message code="addressEntry.contactIdentifier.label" default="Contact Identifier"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: addressEntryInstance, field: 'contactIdentifier', 'errors')}">
            ${addressEntryInstance?.contactIdentifier}
            <g:hiddenField name="contactIdentifier" value="${addressEntryInstance?.contactIdentifier}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="contactName"><g:message code="addressEntry.contactName.label" default="Contact Name"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: addressEntryInstance, field: 'contactName', 'errors')}">
            ${addressEntryInstance?.contactName}
            <g:hiddenField name="contactName" value="${addressEntryInstance?.contactName}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="addressType"><g:message code="addressEntry.addressType.label" default="Address Type"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: addressEntryInstance, field: 'addressType', 'errors')}">
            <g:select name="addressType" from="${nl.gridshore.sample.addressbook.domain.AddressType?.values()}" value="${addressEntryInstance?.addressType}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="streetAndNumber"><g:message code="addressEntry.streetAndNumber.label" default="Street And Number"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: addressEntryInstance, field: 'streetAndNumber', 'errors')}">
            <g:textField name="streetAndNumber" value="${addressEntryInstance?.streetAndNumber}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="zipCode"><g:message code="addressEntry.zipCode.label" default="Zip Code"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: addressEntryInstance, field: 'zipCode', 'errors')}">
            <g:textField name="zipCode" value="${addressEntryInstance?.zipCode}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="city"><g:message code="addressEntry.city.label" default="City"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: addressEntryInstance, field: 'city', 'errors')}">
            <g:textField name="city" value="${addressEntryInstance?.city}"/>
          </td>
        </tr>

        </tbody>
      </table>
    </div>
    <div class="buttons">
      <span class="button"><g:submitButton name="save" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
    </div>
  </g:form>
</div>
</body>
</html>
