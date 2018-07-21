<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<div id="conteneurMenuStock">
	<div class="elementMenuStock">
<c:if test="${myUser!=null}">
<a href="AfficherStock.do">Stock afficher</a> 
</c:if>
	</div>
</div>