<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/shared/header.jsp"/>



<section class="bar-list">
	
	<c:forEach var="bar" items="${bars}">
		<div class="card">
			<c:url var="barPageURL" value="/barPage">
				<c:param name="barId" value="${bar.barId}"></c:param>
			</c:url>
			<a href="${barPageURL}">
				<img class="logo" alt="${bar.name}" src="img/barlogo${bar.accountNumber}.png" />
			</a>
		</div>
	</c:forEach>
</section>

    
<c:import url="/WEB-INF/jsp/shared/footer.jsp"/>