<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/shared/header.jsp"/>

	<div class="card">
	    <div class="nav">
	        <a>My History</a>
	        <a class="current-orders">Current Orders</a>
	        <a>Drink Search</a>
	    </div>
	    <div class="bar">
	        <div class="bar-name" >
	            <img alt="${bar.name}" src="img/barlogo${bar.accountNumber}.png">
	        </div>
	    </div>
	    <div class="table">
	    	<c:forEach var="order" items="orders">
		        <div class="row">
		            <div class="time col">10</div>
		            <div class="mins-ago col">
		                <div class="mins-ago">mins</div>
		                <div class="mins-ago">ago</div>
		            </div>
		            <div class="qty col">3</div>
		            <div class="drink col">Bud Light Bottle</div>
		            <div class="comment col">Dilly Dilly!</div>
		            <div class="votes">
		                <img src="img/votes.png" alt="vote icon"/>
		            </div>
		        </div>
	        </c:forEach>
	    </div>
	</div> 

<c:import url="/WEB-INF/jsp/shared/footer.jsp"/>