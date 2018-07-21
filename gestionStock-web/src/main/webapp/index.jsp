<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<link rel="stylesheet" href="css/style.css" />
<title><bean:message key="titre.test" /></title>
</head>

<body>

	<c:url value="elements/header.jsp" var="urlHeader" />
	<c:import url="${urlHeader}" />

	<c:url value="elements/navigationApplication.jsp" var="urlNavigation" />
	<c:import url="${urlNavigation}" />


<!-- 	 
	<br />Créer jeu d'essai :
	<a href="EditerDonnees.do"> ici</a>

 -->
 
  	<br />Démarrer Web Service :
	<a href="WebService.do"> ici</a>

	<c:if test="${myUser==null}">
		<div class="red">
			<b><i><html:errors /></i></b>
		</div>


		<br />


		<div id="conteneurUser">
			<div class="elementUser">
				<div class="red">${message}</div>



				<table border="1">
					<html:form action="CreerConnectionUser">
						<thead class="ligneUserUn">
							<tr>
								<th><bean:message key="connection.user.login"
										bundle="connectionMy" /></th>
								<th><nested:text property="login" /></th>
								<th></th>
							</tr>
						</thead>
						<tbody class="ligneUserUn">
							<tr>
								<td><bean:message key="connection.user.mdp"
										bundle="connectionMy" /></td>
								<td><nested:password property="mdp" /></td>


								<td><html:submit value="Valider" /></td>
							</tr>
						</tbody>
					</html:form>
				</table>


			</div>
		</div>

	</c:if>

	<c:url value="elements/footer.jsp" var="urlFooter" />
	<c:import url="${urlFooter}" />

</body>

</html:html>