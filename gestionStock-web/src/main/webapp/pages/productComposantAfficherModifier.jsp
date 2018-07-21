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


<div class="red">
	<b><i><html:errors /></i></b>
</div>	
<table class="ligneUserUn" border="1">


	<thead>
		<tr>
			
		<th><bean:message key="product.composant.num" bundle="productComposantGerer" /></th>
		<th><bean:message key="product.composant.fresh-product-name" bundle="productComposantGerer" /></th>
		<th><bean:message key="product.composant.amount" bundle="productComposantGerer" /></th>
							<th>plat</th>
					<th>status</th>
					<th></th>
		</tr>
	</thead>
	<tbody>
	

		
		<c:forEach var="p" items="${ProductComposant}">
		<html:form action="/GererModifierProductComposant.do">
			<tr>

				<td>
				<nested:hidden property="idComposant" value="${p.id}"/>
				${p.num}
				</td>
				<td>
				
				${p.freshProduct.name}
				</td>	
				<td>${p.amount}</td>
				<td>
				<c:if test="${p.terreIyakiProduct.namePointeur!=null}">
				<nested:hidden property="terreIyakiProductComposant" value="${p.terreIyakiProduct.namePointeur}"/>
				${p.terreIyakiProduct.namePointeur}
				</c:if>
				<c:if test="${p.terreIyakiProduct.namePointeur==null}">
				<nested:hidden property="terreIyakiProductComposant" value=""/>
				${p.terreIyakiProduct.namePointeur}
				</c:if>				
				
				
				</td>
				<td>
				<select name="statusComposant">
				<option value="${p.status.name}" selected>${p.status.name}</option>
			
			
	<c:forEach var="s" items="${status}">
								<c:if test="${p.status.name != s.name}">
									<option>${s.name}</option>
								</c:if>
							</c:forEach>		
			

				</select>

				</td>
				
				<td><html:submit value="Valider" /></td>
				
			</tr>
			</html:form>
		</c:forEach>
		
			</tbody>
			
</table>


