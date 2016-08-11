<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.User"%>
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
		Controller c  = Controller.getInstance();
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
			<%if(u.getUserType()==2){ %>
			<a class="navbar-brand" href="home_product_manager.jsp">Talaria Footwear</a>
			<%}else if(u.getUserType()==3){ %>
			<a class="navbar-brand" href="account-manager.jsp">Talaria Footwear</a>
			<%} %>
		</div>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#"><span class="glyphicon glyphicon-user"></span>
					Hello <%=u.getFirstName()%>!</a></li>
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
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container --> </nav>

	<!-- Page Content -->
	<div class="container">

		<div class="row">

		
			<div class="col-md-12 user-records-table">

				<!-- -->
				<div class="panel-body">
					<form id="signupform" class="form-horizontal"
						data-toggle="validator" role="form" action="ChangePassServlet"
						method="post">
						<label for="password" class="col-md-3 control-label">Old Password</label>
						<div class="form-group col-md-9">
							<input pattern="^[_A-z0-9]{1,}$" data-minlength="6"
								type="password" class="form-control" name="oldpassword"
								placeholder="Enter old password" required>
							<div class="help-block with-errors"></div>
						</div>
						<label for="password" class="col-md-3 control-label">New
							Pass</label>
						<div class="form-group col-md-9">
							<input id="passwd"  type="password" class="form-control"
								name="newpassword" placeholder="Enter new password"
								required>
							<div class="help-block with-errors"
								style="margin: 0 !important; padding: 0 !important"></div>
						</div>
						<label for="password" class="col-md-3 control-label">Confirm
							Pass</label>
						<div class="form-group col-md-9">
							<input data-match="#passwd" type="password" class="form-control"
								name="confirmpassword" placeholder="Enter new password again"
								required>
							<div class="help-block with-errors"
								style="margin: 0 !important; padding: 0 !important"></div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-5 col-md-3">
								<a onclick="$(this).closest('form').submit()" id="btn-signup"
									href="#" class="btn btn-info"> <i class="icon-hand-right"></i>
									Change Password
								</a>
							</div>
						</div>

					</form>
				</div>
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
		function sort(sortBy) {
			$.post('UserRecordServlet', {
				sortBy : sortBy
			}, function(response) {
				var mainDiv = $('.user-records-table');
				mainDiv.empty();
				if (sortBy == "view") {
					var soryByDiv = $("<div></div>").addClass("sorted-by")
							.html("View all users");
					var tableElem = $("<table></table>").addClass("table");
					var threadElem = $("<thead></thead>");
					var trElem = $("<tr></tr>");
					var thElem = $("<th></th>").html("Username");
					var thElem2 = $("<th></th>").html("Last Name");
					var thElem3 = $("<th></th>").html("First Name");
					var thElem4 = $("<th></th>").html("Middle Name");
					var thElem5 = $("<th></th>").html("Email");
					var thElem6 = $("<th></th>").html("Role");
					var thElem7 = $("<th></th>").html("Expiry");
					var bodyElem = $("<tbody></tbody>");
					var trElem2 = $("<tr></tr>");
					var tdElem = $("<td></td>").html(response);
					thElem.appendTo(trElem);
					thElem2.appendTo(trElem);
					thElem3appendTo(trElem);
					thElem4appendTo(trElem);
					thElem5appendTo(trElem);
					thElem6appendTo(trElem);
					thElem7.appendTo(trElem);
					trElem.appendTo(threadElem);
					threadElem.appendTo(tableElem);
					tdElem.appendTo(trElem2);
					trElem2.appendTo(bodyElem);
					bodyElem.appendTo(tableElem);
					soryByDiv.appendTo(mainDiv);
					tableElem.appendTo(mainDiv);
				}
			});
		}
	</script>



</body>

</html>