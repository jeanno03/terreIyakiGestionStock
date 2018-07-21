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
		<td></td>
			<th>login</th>
			<th>droit</th>
			<th>status</th>
			<th></th>

		</tr>
	</thead>
	<c:forEach var="u" items="${user}">
		<tbody>
			<html:form action="/ModifierModificationStatusUser.do">
				<tr>
					<nested:hidden property="login" value="${u.login}" />
					
					<td><a
						href="AfficherHistoriqueModificationStatusUser.do?login=${u.login}">historique</a></td>
					<td>${u.login}</td>
					<td>${u.grantUser.name}</td>
					<td><select name="status">
							<option value="${u.status.name}" selected>${u.status.name}</option>
							<c:forEach var="s" items="${status}">
								<c:if test="${u.status.name != s.name}">
									<option>${s.name}</option>
								</c:if>
							</c:forEach>
					</select></td>
					<td><html:submit value="valider" /></td>

				</tr>
			</html:form>
		</tbody>
	</c:forEach>
</table>

<c:if test="${loginHisto!=null}">
<table class="ligneUserUn" border="1">
	<thead>
		<tr>
			<th></th>
			<th>Historique pour ${loginHisto}</th>			
		</tr>
	</thead>
	<tbody>
		<tr>
			
			<td>status</td>
			<td>date</td>
			<td>réalisé par
			<td>
		</tr>

		<c:forEach var="c" items="${change}">
			<tr>
				
				<td>${c.status.name}</td>
				<td>${c.historisation.historisationDate}</td>
				<td>${c.historisation.user.login}</td>
			</tr>
		</c:forEach>

	</tbody>
</table>
</c:if>
