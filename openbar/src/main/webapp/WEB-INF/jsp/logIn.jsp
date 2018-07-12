<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/shared/header.jsp"/>

	

	<c:url var="formAction" value="/homePage"/>
	<form method="post" action="${formAction}">
		<label for="email">Email Address:</label>
		<input type="text" id="email" name="email"/>
		<input type="submit" value="Submit" />
	</form>

<c:import url="/WEB-INF/jsp/shared/footer.jsp"/>