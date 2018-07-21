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
							bundle="freshProductGerer" /></th>
					<th><bean:message key="fresh-product.description"
							bundle="freshProductGerer" /></th>

					<th></th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="f" items="${FreshProduct}">
					<tr>
						<td>${f.name}</td>

						<td>${f.description}</td>



						<td><a
							href="AfficherTerreIyakiProductListByFreshProductGerer.do?name=${f.name}">Infos</a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</div>

<div class="red">
	<b><i><html:errors /></i></b>
</div>

<c:if test="${nomChoisi!=null}">
<div id="conteneurVoid">

<div class="elementVoid">
<table class="ligneUserUn" border="1">
	<thead>
		<tr>
		<th>ajouter pour ${nomChoisi}</th>
		<th></th>
		</tr>
		</thead>
	<tbody>		
		
			<html:form action="/AssocierTerreIyakiProduct.do">
			<nested:hidden property="name" value="${nomChoisi}" />

			<tr>
				<td><select name="terreIyakiProduct">
						<option selected></option>
						<c:forEach var="t" items="${TerreIyakiProduct}">
							<option>${t.namePointeur}</option>
						</c:forEach>
				</select></td>
				
				<td><html:submit value="Valider" /></td>
				
	
				
				</tr>
	
				
				</html:form>
			
			
		
	</tbody>
</table>
</div>

	<div class="elementVoid">
<table class="ligneUserUn" border="1">
	<thead>
		<tr>
		
			<th>Plats</th>
			
		</tr>
	</thead>
	<tbody>
		<c:forEach var="tc" items="${TerreIyakiProductChoice}">
			<tr>
			<td>${tc.namePointeur}</td>

			</tr>
		</c:forEach>		
			
		</tbody>
</table>
</div>


</div>
</c:if>









