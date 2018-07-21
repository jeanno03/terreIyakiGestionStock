<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<c:url value="../elements/navigationProduct.jsp"
	var="urlNavigationProduct" />
<c:import url="${urlNavigationProduct}" />

<div id="conteneurVoid">
	<div class="elementVoid">
		<table class="ligneUserUn" border="1">
			<thead>
				<tr>

					<th><bean:message key="fresh-product.name"
							bundle="freshProduct" /></th>
					<th><bean:message key="fresh-product.description"
							bundle="freshProduct" /></th>

					<th></th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="f" items="${FreshProduct}">
					<tr>
						<td>${f.name}</td>

						<td>${f.description}</td>



						<td><a
							href="AfficherTerreIyakiProductListByFreshProduct.do?name=${f.name}">Infos</a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</div>



<div class="red">
	<b><i><html:errors /></i></b>
</div>

<div id="conteneurVoid">
	<div class="elementVoid">
<table class="ligneUserUn" border="1">
	<thead>
		<tr>
			<th>${nomChoisi}</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="t" items="${TerreIyakiProductChoice}">
			<tr>
				<td>${t.namePointeur}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<table class="ligneUserUn" border="1">
	<thead>
		<tr>
<tr></tr>
			<th>Créer</th>
		
		<tr></tr>
	</thead>
	<tbody>
		<html:form action="/CreerFreshProduct.do">
			<tr>
				<td><bean:message key="fresh-product.name"
						bundle="freshProduct" /></td>
				<td><nested:text property="name" /></td>
				<td></td>

			</tr>
			<tr>
				<td><bean:message key="fresh-product.description"
						bundle="freshProduct" /></td>
				<td><nested:textarea property="description" /></td>
				<td><html:submit value="Valider" /></td>
			</tr>

		</html:form>
	</tbody>

</table>
</div>
</div>