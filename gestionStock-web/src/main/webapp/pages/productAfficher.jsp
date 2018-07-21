<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<c:url value="../elements/navigationProduct.jsp"
	var="urlNavigationProduct" />
<c:import url="${urlNavigationProduct}" />


<c:forEach var="m" items="${MaPagination}">

	<a href="AfficherProduct.do?debut=${m.debut}">${m.debut}-${m.fin}</a> | 
</c:forEach>


<table class="ligneUserUn" border="1">
	<thead>
		<tr>
			<th>nom</th>
			<th>prix HT</th>

			<th>description</th>
			<th></th>
		</tr>
	</thead>
	<tbody>

		<c:forEach var="t" items="${TerreIyakiProduct}">
			<tr>
				<td>${t.namePointeur}</td>
				<td>${t.pricePointeur}</td>

				<td>${t.descriptionPointeur}</td>
				<td><a
					href="AfficherProductById.do?id=${t.product_idPointeur}&debut=${debutSession}">
						infos</a></td>
			</tr>



		</c:forEach>

	</tbody>



</table>

<div id="conteneurVoid">
	<div class="elementVoid">
		<div class="red">Infos de gestion pour ${message02}</div>

		<c:forEach var="f" items="${FreshProductById}">
			<table class="ligneUserUn" border="1">
				<thead>
					<tr>
						<th>Produit frais</th>
						<th>Description</th>
					</tr>
				</thead>
				<tbody>

					<tr>
						<td>${f.name}</td>
						<td>${f.description}</td>
					</tr>

				</tbody>
			</table>
		</c:forEach>
		<hr />
		<c:forEach var="p" items="${ProductComposantById}">
			<table class="ligneUserUn" border="1">
				<thead>
					<tr>
						<th>Composant</th>
						<th>numéro</th>
						<th>qté (gramme)</th>
						<th>statut</th>
					</tr>
				</thead>
				<tbody>

					<tr>
						<td>${p.freshProduct.name}</td>
						<td>${p.num}</td>
						<td>${p.amount}</td>
						<td>${p.status.name}</td>

					</tr>

				</tbody>
			</table>
		</c:forEach>
	</div>

</div>

