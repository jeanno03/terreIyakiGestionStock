<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>




<c:url value="../elements/navigationMy.jsp" var="urlNavigationMy" />
<c:import url="${urlNavigationMy}" />
 <%-- 
	<br />Créer jeu d'essai :
	<a href="EditerDonnees.do"> ici</a>

--%>





	<br />Bienvenue sur l'application test de gestion de stock de produits frais 
	
	<br />
	<br />
	
	<c:if test="${not empty lastProductComposant}">
	
	<table class="ligneUserUn" border="1">
		<thead>
			<tr>
				<th>Info de la dernière commande</th>
			</tr>
		</thead>

	</table>
	<table class="ligneUserUn" border="1">
		<thead>
			<tr>
				<th>Num</th>
				<th>Produits frais</th>
				<th>grammes</th>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach var="l" items="${lastProductComposant}">
			<tr>
				
					<th>${l.num}</th>
					<th>${l.freshProduct.name}</th>

					<th>${l.amount}</th>
			</tr>
				</c:forEach>
			
		</tbody>
	</table>
	
	</c:if>
	
<c:if test="${not empty terreIyakiOrderItem}">
	<table class="ligneUserUn" border="1">
		<thead>
			<tr>
				<th></th>
				<th>Dernières préparations du restaurant</th>
				<th></th>
			</tr>
		</thead>

		<tbody>
			<tr>
				<th>n°</th>
				<th>plat</th>
				<th>prix HT</th>
			</tr>



			<c:forEach var="t" items="${terreIyakiOrderItem}">
				<tr>
					<td>${t.orderItem_idPointeur}</td>
					<td>${t.terreIyakiProduct.namePointeur}</td>
					<td>${t.pricePointeur}</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</c:if>
