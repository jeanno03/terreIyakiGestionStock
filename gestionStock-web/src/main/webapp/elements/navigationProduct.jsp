<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<div id="conteneurMenuProduct">
	<div class="elementMenuProduct">
		<c:if test="${myUser!=null}">


			<div id="conteneurVoid">
				<div class="elementVoid">
					Restaurant |<br /> 
						
<c:if test="${not empty debutSession}"><a href="AfficherProduct.do?debut=${debutSession}">produits</a></c:if>
<c:if test="${empty debutSession}"><a href="AfficherProduct.do?debut=1">produits</a></c:if>					
					
				</div>
				<div class="elementVoid">

					Produits frais |<br /> <a href="AfficherFreshProduct.do">créer</a> <a
						href="GererFreshProduct.do">gérer</a>
				</div>

				<div class="elementVoid">
					Composant |<br /> <a href="AfficherProductComposant.do">créer</a> <a
						href="GererProductComposant.do">activer</a>

				</div>
			</div>
		</c:if>
	</div>
</div>