<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/shared/header.jsp"/>

	<div class="card">
	    <div class="nav">
	        <a>My History</a>
	        <a>Current Orders</a>
	        <a>Drink Search</a>
	    </div>
	    <div class="bar">
	        <div class="bar-logo" >
	            <img class="logo" alt="${bar.name}" src="img/barlogo${bar.accountNumber}.png">
	        </div>
	    </div>
	    <div class="table">
	    	<c:forEach var="drinkOrder" items="${drinkOrders}">
		        <div class="row">
		            <div class="time col">${drinkOrder.status}</div>
		            <div class="qty col">${drinkOrder.quantity}</div>
		            <div class="drink col">${drinkOrder.drinkName}</div>
		            <div class="comment col">${drinkOrder.comment}</div>
		            <div class="votes">
		                <img src="img/votes.png" alt="vote icon"/>
		            </div>
		        </div>
	        </c:forEach>
	    </div>
	</div> 

<c:import url="/WEB-INF/jsp/shared/footer.jsp"/>