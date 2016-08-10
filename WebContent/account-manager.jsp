<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.User"%>
<%@page import="model.Product"%>
<%@page import="model.FinancialRecord"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.Controller"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Shop Homepage - Start Bootstrap Template</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/shop-homepage.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<%
		User u = (User) session.getAttribute("user");
		session.setAttribute("user", u);
		Controller c  = Controller.getInstance();
		ArrayList<FinancialRecord> records = c.getTotalPriceByProduct();
	%>
	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Start Bootstrap</a>
		</div>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#"><span class="glyphicon glyphicon-user"></span>
					Hello Account Manager <%=u.getFirstName()%>!</a>
			</li>
			<li>
				<form action="LoginServlet" method="GET">
					<button type="submit" name="logout"
						style="margin-top: 14px; background-color: transparent; color: #9d9d9d; border: none">
						<span class="glyphicon glyphicon-log-out"></span>Logout
					</button>
				</form>
			</li>
		</ul>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="#">About</a></li>
				<li><a href="#">Services</a></li>
				<li><a href="#">Contact</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container --> </nav>

	<!-- Page Content -->
	<div class="container">

		<div class="row">

			<div class="col-md-3">
				<p class="lead">Financial Records</p>
				<div class="col-md-12" style="">
					<div class="dropdown">
						<button class="btn btn-default dropdown-toggle" type="button"
							id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="true">
							Sort Records by <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<li><a style="cursor:pointer" onclick="sort('product')">Sales per product</a></li>
							<li><a style="cursor:pointer" onclick="sort('category')">Sales per product type</a></li>
							<li><a style="cursor:pointer" onclick="sort('all')">Total sales</a></li>
						</ul>
					</div>
				</div>
			</div>

			<div class="col-md-9 financial-records-table">
				<div class="sorted-by">Sorted by: Per Product</div>
				<table class="table">
					<thead>
						<tr>
							<th>Product Name</th>
							<th>Total Sales</th>
						</tr>
					</thead>
					<tbody>
					<%for(int i=0; i<records.size(); i++){ %>
						<tr>
							<td><%=records.get(i).getLabel() %></td>
							<td><%=records.get(i).getPrice() %></td>
						</tr>
					<%} %>
					</tbody>
				</table>

			</div>

		</div>

	</div>
	<!-- /.container -->

	<div class="container">

		<hr>

		<!-- Footer -->
		<footer>
		<div class="row">
			<div class="col-lg-12">
				<p>Copyright &copy; Your Website 2014</p>
			</div>
		</div>
		</footer>

	</div>
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/validator.js"></script>
	<script>
		function sort(sortBy){
			$.post('FinancialRecordServlet', { sortBy: sortBy }, function(response){
				if(response == "timeout"){
					alert('Session Expired! Please try logging in again.');
					window.location.replace("index.jsp");
				}else{
					var mainDiv = $('.financial-records-table');
		    		mainDiv.empty();
					if(sortBy == "all"){
						var soryByDiv = $("<div></div>").addClass("sorted-by").html("Sorted by: All");
						var tableElem = $("<table></table>").addClass("table");
						var threadElem = $("<thead></thead>");
						var trElem = $("<tr></tr>");
						var thElem = $("<th></th>").html("Total Sales");
						var bodyElem = $("<tbody></tbody>");
						var trElem2 = $("<tr></tr>");
						var tdElem = $("<td></td>").html(response);
						thElem.appendTo(trElem);
						trElem.appendTo(threadElem);
						threadElem.appendTo(tableElem);
						tdElem.appendTo(trElem2);
						trElem2.appendTo(bodyElem);
						bodyElem.appendTo(tableElem);
						soryByDiv.appendTo(mainDiv);
						tableElem.appendTo(mainDiv);
					}else{
						if(response.length>0){
							var tableElem = $("<table></table>").addClass("table");
							var threadElem = $("<thead></thead>");
							var trElem = $("<tr></tr>");
							
							if(sortBy == "product"){
								var sortByDiv = $("<div></div>").addClass("sorted-by").html("Sorted by: Per Product");
								var thElem = $("<th></th>").html("Product Name");
							}else{
								var sortByDiv = $("<div></div>").addClass("sorted-by").html("Sorted by: Per Category");
								var thElem = $("<th></th>").html("Category");
							}
							var thElem2 = $("<th></th>").html("Total Sales");
							thElem.appendTo(trElem);
							thElem2.appendTo(trElem);
							trElem.appendTo(threadElem);
							threadElem.appendTo(tableElem);
							
							var bodyElem = $("<tbody></tbody>");
							$.each(response, function(key,value){
								var trElem2 = $("<tr></tr>");
								var tdElem = $("<td></td>").html(value['label']);
								var tdElem2 = $("<td></td>").html(value['price']);
								tdElem.appendTo(trElem2);
								tdElem2.appendTo(trElem2);
								trElem2.appendTo(bodyElem);
			    			});
							bodyElem.appendTo(tableElem);
							sortByDiv.appendTo(mainDiv);
							tableElem.appendTo(mainDiv);
						}
					}
				}
	    	});
		}
// 		<div class="sorted-by">Sorted by: sales per product</div>
// 		<table class="table">
// 			<thead>
// 				<tr>
// 					<th>Product Name</th>
// 					<th>Total Sales</th>
// 				</tr>
// 			</thead>
// 			<tbody>
// 				<tr>
// 					<td>John</td>
// 					<td>Doe</td>
// 				</tr>
// 			</tbody>
// 		</table>
// 		function loadProducts(responseJson){
//     		var mainDiv = $('.products-list');
//     		mainDiv.empty();
//     		if(responseJson.length>0){
//     			$.each(responseJson, function(key,value){
//     				var outerDiv = $("<div></div>").addClass("col-sm-4 col-lg-4 col-md-4");
            		
//             		var thumbnailDiv = $("<div></div>").addClass("thumbnail");
//             		var imgDiv = $("<img></img>").attr("src","http://placehold.it/320x150");
//             		var captionDiv = $("<div></div>").addClass("caption");
//             		var h4PullRight = $("<h4></h4>").addClass("pull-right");
//             		h4PullRight.html("$"+value['price']);
//             		h4PullRight.appendTo(captionDiv);
//             		var h4Name = $("<h4></h4>");
//             		var aElem = $("<a></a>").attr("id", value['id']).attr("href", "javascript:{}");
//             		aElem.attr("onclick","pressed(this);document.getElementById('productform').submit(); return false;");
//             		aElem.html(value['name']);
//             		aElem.appendTo(h4Name);
//             		h4Name.appendTo(captionDiv);
//             		var pElem = $("<p></p>");
//             		pElem.html(value['description']);
//             		pElem.appendTo(captionDiv);
//             		var ratingDiv= $("<div></div>").addClass("buying");
//             		var aRating = $("<a></a>").html("buy product");
//             		aRating.appendTo(ratingDiv);
            		
//             		imgDiv.appendTo(thumbnailDiv);
//             		captionDiv.appendTo(thumbnailDiv);
//             		ratingDiv.appendTo(thumbnailDiv);
//             		thumbnailDiv.appendTo(outerDiv);
//             		outerDiv.appendTo(mainDiv);
            		
//     			});
//     		}else{
//     			var noResultsDiv = $("<div></div>").addClass("no-result-div col-sm-12 col-lg-12 col-md-12");
//     			noResultsDiv.html("Sorry, there are no products of this category.");
//     			noResultsDiv.appendTo(mainDiv);
//     		}
    		
    	
//     	}
	</script>



</body>

</html>
