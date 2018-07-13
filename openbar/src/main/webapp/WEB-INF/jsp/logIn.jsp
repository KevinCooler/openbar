<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/shared/header.jsp"/>

		<div class="card">
			<h2>Log In</h2>
			<c:url var="formAction" value="/doLogIn"/>
			<form method="post" action="${formAction}">
				<label for="email">Email Address:</label>
				<input type="text" id="email" name="email"/>
				<input type="submit" value="Submit" />
			</form>
		</div>
		<div class="card">
			<h2>Create Account</h2>
			<c:url var="formAction" value="/doCreateAccount"/>
			<form method="post" action="${formAction}">
				<label for="email">Email Address:</label>
				<input type="text" id="email" name="email"/>
				<label for="name">Display Name:</label>
				<input type="text" id="name" name="name"/>
				<label for="creditCardNumber">Credit Card Number:</label>
				<input type="text" id="creditCardNumber" name="creditCardNumber"/>
				<input type="submit" value="Submit" />
			</form>
		</div>
		<div class="card">
			<h2>Employee Log In</h2>
			<c:url var="EmployeeAction" value="/EmployeePage"/>
			<form method="post" action="${formAction}">
				<label for="barId">Bar Number:</label>
				<input type="text" id="barId" name="barId"/>
				<input type="submit" value="Submit" />
			</form>
		</div>
		
	

<c:import url="/WEB-INF/jsp/shared/footer.jsp"/>