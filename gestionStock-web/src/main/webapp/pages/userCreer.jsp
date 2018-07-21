<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

	<c:url value="../elements/navigationUser.jsp" var="urlNavigationUser" />
	<c:import url="${urlNavigationUser}" />





<table class="ligneUserUn" border="1">
	<thead>
		<tr>
			<th>login</th>

			<th>email</th>
			<th>prénom</th>
			<th>nom</th>
			<th>droit</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="u" items="${user}">
			<tr>
				<td>${u.login}</td>
				<td>${u.email}</td>
				<td>${u.firstName}</td>
				<td>${u.lastName}</td>
				<td>${u.grantUser.name}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<div id="conteneurVoid">
		<div class="elementVoid">
<html:form action="/CreerCreationUser.do">

	<table border="1">
		<thead class="ligneUserUn">
			<tr>
			<th><bean:message key="creation.user.login" bundle="creationUser" /></th>
			<th><nested:text property="login" /></th>
			<th>droit</th>
			<th><select name="grant"> 
				<option selected></option> 
				<c:forEach var="g" items="${grantUser}">
				<option>${g.name}</option>
				</c:forEach>
				</select></th>
			</tr>
		</thead>
		
		<tbody class="ligneUserUn">
			
		
			<tr>
			<td><bean:message key="creation.user.lastName" bundle="creationUser" /></td>
			<td><nested:text property="lastName" /></td>
			<td></td>
			<td></td>
			</tr>

			<tr>
			<td><bean:message key="creation.user.firstName" bundle="creationUser" /></td>
			<td><nested:text property="firstName" /></td>			
			<td></td>
			<td></td>
			</tr>
			
			<tr>
			<td><bean:message key="creation.user.email" bundle="creationUser" /></td>
			<td><nested:text property="email" /></td>
			<td><nested:text property="email02" /></td>
			<td></td>
			</tr>
			
			<tr>
			<td><bean:message key="creation.user.mdp" bundle="creationUser" /></td>
			<td><nested:password property="mdp" /></td>
			<td><nested:password property="mdp02" /></td>
			<td><html:submit value="Valider" /></td>
			</tr>			
			
						
		</tbody>
		</table>

				
</html:form>

</div>
<div class="elementVoid">
<div class="red">
	<b><i><html:errors /></i></b>
</div>
</div>
</div>
