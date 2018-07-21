<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<nav>
	<div id="conteneurNavigation">
		<div class="elementNavigation">


			<c:if test="${myUser!=null}">




				<a href="AfficherMyInfo.do">Mon compte</a> |
			<a href="AfficherUser.do">Gestion utilisateurs </a> | 
			
<c:if test="${not empty debutSession}"><a href="AfficherProduct.do?debut=${debutSession}">Gestion produits</a></c:if>
<c:if test="${empty debutSession}"><a href="AfficherProduct.do?debut=1">Gestion produits</a></c:if>				
			
			
					
					| <a href="AfficherStock.do">Gestion stock </a>
			</c:if>

		</div>
	</div>

</nav>