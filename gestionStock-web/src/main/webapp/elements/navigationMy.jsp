<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<div id="conteneurMenuMy">
	<div class="elementMenuMy">
		<c:if test="${myUser==null}">
Veuillez vous connecter
</c:if>
		<c:if test="${myUser!=null}">
			<a href="AfficherMyInfo.do">Mon Compte</a> |
<a href="AfficherMyHistory.do">Mes actions</a>
		</c:if>
	</div>
</div>