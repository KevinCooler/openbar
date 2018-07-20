<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/shared/header.jsp"/>

<c:url var="barHistoryURL" value="/barHistory"/>
<c:url var="barPageURL" value="/barPage">
	<c:param name="barId" value="${bar.barId}"></c:param>
</c:url>
<c:url var="barDrinkSearchURL" value="/barDrinkSearch"/>

	<div class="card">
	    <div class="nav">
	        <a href="${barHistoryURL}">My History</a>
	        <a href="${barPageURL}">Current Orders</a>
	        <a href="${barDrinkSearchURL}">Drink Search</a>
	    </div>
	    <div class="bar">
	        <div class="bar-logo" >
	            <img class="logo" alt="${bar.name}" src="img/barlogo${bar.accountNumber}.png">
	        </div>
	    </div>
	    <div class="table">
	    	<c:forEach var="drinkOrder" items="${drinkOrders}">
		        <div class="row">
		        	<c:url var="orderPageURL" value="/orderPage">
		        		<c:param name="drinkId" value="${drinkOrder.drinkId}"></c:param>
		        	</c:url>
		            <div class="votes col">
		                <img src="img/votes.png" alt="vote icon"/>
		            </div>
		            <div class="status col">${drinkOrder.status}</div>
		            <div class="qty col">${drinkOrder.quantity}</div>
		            <div class="drink col">
		            	<div>${drinkOrder.drinkName}</div>
		            	<div class="comment col">${drinkOrder.comment}</div>
		            </div>
		            
		            <div class="order col">
		            	<a class="order-button"href="${orderPageURL}">Order</a>
		            </div>
		        </div>
	        </c:forEach>
	    </div>
	</div> 

<c:import url="/WEB-INF/jsp/shared/footer.jsp"/>