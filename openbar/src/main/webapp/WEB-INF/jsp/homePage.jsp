<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/shared/header.jsp"/>

<c:url var="barPageURL" value="/barPage"/>
<form id="myform" method="post" action="${barPageURL}">
	<c:forEach var="bar" items="${bars}">
	  <input class="card" type="image" name="barId" value="${bar.barId}" alt="${bar.name}" src="img/barlogo${bar.accountNumber}.png">
	</c:forEach>
</form>

    
<c:import url="/WEB-INF/jsp/shared/footer.jsp"/>