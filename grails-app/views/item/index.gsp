
<%@ page import="concot.Item" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'item.label', default: 'Item')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<g:javascript library="jquery"/>
	</head>
	<body>
		<a href="#list-item" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<%--busca Itens--%>
		<g:formRemote name="formBusca" update="resultado"
			url="[controller:'item', action:'busca']">
			Buscar item:
			<g:textField name="nome"/>
			<input type="submit" value="Buscar"/>
		</g:formRemote>

		<%--Resultado da busca Itens--%>
		<div id="resultado"></div>
		
		<div id="list-item" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="nome" title="${message(code: 'item.nome.label', default: 'Nome')}" />
					
						<th><g:message code="item.categoria.label" default="Categoria" /></th>
					
						<g:sortableColumn property="imagem" title="${message(code: 'item.imagem.label', default: 'Imagem')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${itemInstanceList}" status="i" var="itemInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${itemInstance.id}">${fieldValue(bean: itemInstance, field: "nome")}</g:link></td>
					
						<td>${fieldValue(bean: itemInstance, field: "categoria")}</td>
					
						<td>${fieldValue(bean: itemInstance, field: "imagem")}</td>

						<td><img src="${createLinkTo(controller:'item', action:'imagem', id:itemInstance.id)}"/></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${itemInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
