<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html:html>
<head>
<link rel="stylesheet" href="css/style.css" />
<c:if test="${home!=null&&myUser!=null}">
<% 
//si on est sur la page home alors le refresh est automatique 
//response.addHeader("Refresh","30; http://localhost:8080/gestionStock-web/AfficherVenteOrderItem.do");
response.addHeader("Refresh","30; AfficherVenteOrderItem.do"); 

%>
</c:if>
<c:if test="${myUser==null}">
<% 
//si l'utilisateur n'est pas connecté il est renvoyé vers la page de connexion
//response.addHeader("Refresh","0; http://localhost:8080/gestionStock-web/AfficherMyConnection.do"); 
response.addHeader("Refresh","0; AfficherMyConnection.do"); 

%>
</c:if>
<title>Application gestion stock</title>
</head>
<body>
	<c:url value="../elements/header.jsp" var="urlHeader" />
	<c:import url="${urlHeader}" />

	<c:url value="../elements/navigationApplication.jsp" var="urlNavigation" />
	<c:import url="${urlNavigation}" />
	


	<div id="conteneurUser">
		<div class="elementUser">
		<div class="red">${message}</div>
			<tiles:insert attribute="application" />
		</div>
	</div>
	




	<c:url value="../elements/footer.jsp" var="urlFooter" />
	<c:import url="${urlFooter}" />

</body>

</html:html>