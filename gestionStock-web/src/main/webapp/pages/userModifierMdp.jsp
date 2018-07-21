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
			<th>login</th>
			<th>password</th>
			<th>again</th>
			
		</tr>
	</thead>
	<tbody>
		<html:form action="/ModifierModificationMdpUser.do">
			<tr>
				<td><select name="login">

						<c:forEach var="u" items="${user}">

							<option>${u.login}</option>

						</c:forEach>
				</select></td>
				<td><nested:password property="mdp" /></td>
				<td><nested:password property="mdp02" /></td>
				<td><html:submit value="valider" /></td>
			</tr>
		</html:form>
	</tbody>
</table>