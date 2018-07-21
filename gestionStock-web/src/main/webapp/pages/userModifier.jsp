<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

	<c:url value="../elements/navigationUser.jsp" var="urlNavigationUser" />
	<c:import url="${urlNavigationUser}" />
	
<div class="red">
	<b><i><html:errors /></i></b>
</div>
<table class="ligneUserUn" border="1">
	<thead>
		<tr>
			<th><bean:message key="modification.user.login"
					bundle="modificationUser" /></th>
			<th>email</th>
			<th>prénom</th>
			<th>nom</th>
			<th>droit</th>
			<th></th>
		</tr>
	</thead>
	<c:forEach var="u" items="${user}">
		<tbody>
			<html:form action="/ModifierModificationUser.do">
				<tr>
					<nested:hidden property="login" value="${u.login}" />
					<td>${u.login}</td>
					<td><nested:text property="email" value="${u.email}" /></td>
					<td><nested:text property="firstName" value="${u.firstName}" />
					</td>
					<td><nested:text property="lastName" value="${u.lastName}" />
					</td>
					<td><select name="grant">
							<option value="${u.grantUser.name}" selected>${u.grantUser.name}</option>
							<c:forEach var="g" items="${grantUser}">
								<c:if test="${u.grantUser.name != g.name}">
									<option>${g.name}</option>
								</c:if>
							</c:forEach>
					</select></td>
					<td><html:submit value="valider" /></td>
				</tr>
			</html:form>
		</tbody>
	</c:forEach>
</table>




