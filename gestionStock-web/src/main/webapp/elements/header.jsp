<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<header>
	<div id="conteneurHeader">
		<div class="elementHeader">
			<a href="AfficherVenteOrderItem.do"> <img class="logo"
				src="images/logo.png" /></a>
		</div>
		<div class="elementHeader">
			<c:if test="${myUser!=null}">
				<br />bienvenue, ${myUser.login}!
				<br />
				<a href="seDeconnecter.do">se déconnecter</a>
			</c:if>


		</div>
	</div>
</header>
