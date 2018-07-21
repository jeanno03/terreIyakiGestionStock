<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
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
