<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/shared/header.jsp"/>

<c:url var="sumbitOrderURL" value="/submitOrder"/>

	<div class="card">
        <h1>Drink Order Details</h1>
        <div>
        	<div>${drinkOrder.customerName}</div>
            <div>${drinkOrder.quantity}</div>
            <div>${drinkOrder.drinkName}</div>
        </div>
        <form action="${sumbitOrderURL}" method="post">
			<table>
				<tr>
					<td>
						<label for="quantity">Quantity:</label>
					</td>
					<td>
						<select name="quantity" id="form-quantity">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label for="comment">Tell the bartender how to prepare your drink:</label>
					</td>
					<td>
						<input id ="form-comment" name="comment"/>
					</td>
				</tr>
			</table>
			<div class="submit">
				<input type="submit" value="Submit Order" class="submit-button"/>
			</div>	
		</form>
	</div> 

<c:import url="/WEB-INF/jsp/shared/footer.jsp"/>