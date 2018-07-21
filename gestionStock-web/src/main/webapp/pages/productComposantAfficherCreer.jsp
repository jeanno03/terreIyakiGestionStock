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

<table class="ligneUserUn" border="1">
	<thead>
		<tr>

			<th><bean:message key="product.composant.num"
					bundle="productComposant" /></th>
			<th><bean:message key="product.composant.fresh.product.name"
					bundle="productComposant" /></th>
			<th><bean:message key="product.composant.amount"
					bundle="productComposant" /></th>
			<th>plat</th>
			<th>status</th>
		</tr>
	</thead>
	<tbody>

		<c:forEach var="p" items="${ProductComposant}">
			<tr>

				<td>${p.num}</td>
				<td>${p.freshProduct.name}</td>
				<td>${p.amount}</td>
				<td><c:if test="${p.terreIyakiProduct.namePointeur!=null}">
				${p.terreIyakiProduct.namePointeur}
				</c:if> <c:if test="${p.terreIyakiProduct.namePointeur==null}">
						<a
							href="AfficherProductComposant.do?idComposant=${p.id}&nameComposant=${p.freshProduct.name}">définir</a>
					</c:if></td>

				<td>${p.status.name}</td>

			</tr>
		</c:forEach>

	</tbody>

</table>



<div class="red">
	<b><i><html:errors /></i></b>
</div>


<c:if test="${not empty TerreIyakiProductChoice}">
	<html:form action="/AssocierProductComposant.do">
${productComposantChoisi.num} - ${productComposantChoisi.freshProduct.name} - ${productComposantChoisi.amount}g | plat choisi :  
<nested:hidden property="idComposant"
			value="${productComposantChoisi.id}" />

		<select name="terreIyakiProductComposant">
			<option selected></option>
			<c:forEach var="t" items="${TerreIyakiProductChoice}">
				<option>${t.namePointeur}</option>
			</c:forEach>
		</select>
		<html:submit value="Valider" />
	</html:form>
</c:if>
<c:if test="${empty TerreIyakiProductChoice}">




<table class="ligneUserUn" border="1">

	<thead>
		<tr>
			<th></th>
			<th>Créer</th>
			<th></th>
		</tr>
	</thead>

	<tbody>

		<html:form action="/CreerProductComposant.do">
			<tr>
				<td><bean:message key="product.composant.num"
						bundle="productComposant" /></td>
				<td><nested:text property="numComposant" /></td>
				<td></td>
			</tr>
			<tr>
				<td><bean:message key="product.composant.fresh.product.name"
						bundle="productComposant" /></td>
				<td><select name="freshProductComposant">
						<option selected></option>
						<c:forEach var="f" items="${FreshProduct}">
							<option>${f.name}</option>
						</c:forEach>
				</select></td>
				<td></td>
			</tr>
			<tr>
				<td><bean:message key="product.composant.amount"
						bundle="productComposant" /></td>
				<td><nested:text property="amountComposant" /></td>
				<td><html:submit value="Valider" /></td>
			</tr>
		</html:form>

	</tbody>

</table>
</c:if>
