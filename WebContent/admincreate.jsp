<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Talaria Footwear</title>

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
			<p class="navbar-brand" style="cursor:default">Talaria Footwear</p>
		</div>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#"><span class="glyphicon glyphicon-user"></span>
					Hello Admin!</a></li>
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
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container --> </nav>

	<!-- Page Content -->
	<div class="container">

		<div id="confirmationModal" class="modal fade my-modal">
            <div class="modal-dialog my-modal-dialog">
                <div class="modal-content my-modal-content">
                    <div class="modal-header my-modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h2 class="modal-title">Confirmation</h2>
                    </div>
                    <div class="modal-body my-modal-body">
                    	<!--form id="BuyProductForm" class="form-horizontal" data-toggle="validator" role="form" action="ProductServlet" method="post"-->
                    	<input type = "hidden" name = "wasbought" id = "wasbought" value = "false">
                        <div class="col-xs-12">
                            <p><h2>Are you sure you want to add this User?</h2></p>
                            <p><h5>Please place your password below to confirm.</h5></p>
                            <div class="form-group">
							<input pattern="^[_A-z0-9]{1,}$" id="passwd" type="password" class="form-control" name="password"
								placeholder="Enter password" required>
							<div class="help-block with-errors"></div>
							</div>
                            <br>
                       </div>
                        <div class="form-group clearfloat"></div>
                        <div class="floatright">
                            <button type="button" class="btn" onclick="setConfirmation(true);">Yes</button>
                            <button type="button" class="btn" onclick="setConfirmation(false);"data-dismiss="modal">No</button>    
                        </div>
                        <div class="clearfloat"></div>
                        <!-- /form-->
                    </div>
                </div>
            </div>
        </div>

		<div id="SuccessCreatingModal" class="modal fade my-modal">
            <div class="modal-dialog my-modal-dialog">
                <div class="modal-content my-modal-content">
                    <div class="modal-header my-modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h2 class="modal-title">SUCCESS!</h2>
                    </div>
                    <div class="modal-body my-modal-body">
                        <div class="col-xs-12">
                            <p><h2>You have successfully created the user!</h2></p>
                            <p><h5>Please remind the user to change his/her password within 24 hours.</h5></p>
                        </div>
                        <div class="clearfloat"></div>
                        <div class="clearfloat"></div>
                        <div class="floatright">
                            <button type="button" class="btn" onclick = "Yehey();" data-dismiss="modal">Okay</button>    
                        </div>
                        <div class="clearfloat"></div>
                    </div>
                </div>
            </div>
        </div>


		<div id="FailCreatingModal" class="modal fade my-modal">
            <div class="modal-dialog my-modal-dialog">
                <div class="modal-content my-modal-content">
                    <div class="modal-header my-modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h2 class="modal-title">FAIL! YOU ENTERED THE WRONG PASSWORD!</h2>
                    </div>
                    <div class="modal-body my-modal-body">
                        <div class="form-group clearfloat"></div>
                        <div class="floatright">
                            <button type="button" class="btn" onclick="Retry();">Try Again</button>
                            <button type="button" class="btn" data-dismiss="modal">Cancel</button>    
                        </div>
						<div class="clearfloat"></div>
                    </div>
                </div>
            </div>
        </div>

		<div id="DupeModal" class="modal fade my-modal">
            <div class="modal-dialog my-modal-dialog">
                <div class="modal-content my-modal-content">
                    <div class="modal-header my-modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h2 class="modal-title">FAIL! USERNAME ALREADY EXISTS!</h2>
                    </div>
                    <div class="modal-body my-modal-body">
                        <div class="form-group clearfloat"></div>
                        <div class="floatright">
                            <button type="button" class="btn" data-dismiss="modal">Okay</button>    
                        </div>
						<div class="clearfloat"></div>
                    </div>
                </div>
            </div>
        </div>

		<div id="PWMismatchModal" class="modal fade my-modal">
            <div class="modal-dialog my-modal-dialog">
                <div class="modal-content my-modal-content">
                    <div class="modal-header my-modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h2 class="modal-title">FAIL! ACCOUNT CREATION PASSWORD MISMATCH!</h2>
                    </div>
                    <div class="modal-body my-modal-body">
                        <div class="form-group clearfloat"></div>
                        <div class="floatright">
                            <button type="button" class="btn" data-dismiss="modal">Okay</button>    
                        </div>
						<div class="clearfloat"></div>
                    </div>
                </div>
            </div>
        </div>



		<div class="row">

			<div class="col-md-3">
				<p class="lead">User Records</p>
				<div class="list-group">
					<a href="admin.jsp" class="list-group-item">View Users</a> 
					<a href="#" class="list-group-item active">Add User</a>
				</div>
			</div>

			<div class="col-md-9 user-records-table">

				<!-- -->
				<div class="panel-body">
					<!-- form id="signupform" class="form-horizontal"
						data-toggle="validator" role="form" action="CreateAccountServlet"
						method="post"-->
						<label class="col-md-3 control-label" for="sel1">Account
							Type</label>
						<div class="form-group col-md-9">
							<select style="width: 180px; display: inline-block"
								class="form-control" id="sel1" name="userType">
								<option>Accounting Manager</option>
								<option>Product Manager</option>
							</select>
						</div>
						<label class="col-md-3 control-label" for="firstname">Name</label>
						<div class="form-group col-md-4">
							<input pattern="^[ A-z]{1,}$" type="text" class="form-control"
								name="firstname" id="firstname" placeholder="First Name" required>
						</div>
						<div class="form-group col-md-2">
							<input maxlegnth="1" pattern="[A-z]" type="text"
								class="form-control" name="middleinitial" id="middleinitial" placeholder="M.I."
								required>
						</div>
						<div class="form-group col-md-3">
							<input pattern="^[ A-z]{1,}$" type="text" class="form-control"
								name="lastname" id="lastname" placeholder="Last Name" required>
						</div>


						<label for="username" class="col-md-3 control-label">Username</label>
						<div class="form-group col-md-9">
							<input data-minlength="6" pattern="^[_A-z0-9]{1,}$" type="text"
								class="form-control" name="username" id= "username"
								placeholder="Minimum of 6 characters. Composed of numbers, letters and _"
								required>
							<div class="help-block with-errors"></div>
						</div>
						<label for="password" class="col-md-3 control-label">Password</label>
						<div class="form-group col-md-9">
							<input pattern="^[_A-z0-9]{1,}$" data-minlength="6" id="pw"
								type="password" class="form-control" name="pw"
								placeholder="Minimum of 6 characters" required>
							<div class="help-block with-errors"></div>
						</div>
						<label for="password" class="col-md-3 control-label">Confirm
							Pass</label>
						<div class="form-group col-md-9">
							<input pattern="^[_A-z0-9]{1,}$" data-minlength="6" type="password" class="form-control"
								name="confirmpassword" id = "confirmpassword" placeholder="Match input with password"
								required>
							<div class="help-block with-errors"
								style="margin: 0 !important; padding: 0 !important"></div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-5 col-md-3">
								<!-- a onclick="$(this).closest('form').submit()" id="btn-signup"
									href="#" class="btn btn-info"> <i class="icon-hand-right"></i>
									Create User
								</a-->
								<a onclick="checkAdd();" id="btn-signup"
									href="#" class="btn btn-info"> <i class="icon-hand-right"></i>
									Create User
								</a>
							</div>
						</div>

					<!--/form-->
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
				<p>Copyright &copy; Your Website 2016</p>
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
		
		function checkAdd()
		{
			$('#confirmationModal').modal('show');
			return false;
		}
		
		function setConfirmation(retValue)
		{
			$('#confirmationModal').modal("hide");
			if(retValue)
			{	
				$.post('CreateAccountServlet', {password:document.getElementById("passwd").value,
					firstname:document.getElementById("firstname").value,
					middleinitial: document.getElementById("middleinitial").value,
					lastname: document.getElementById("lastname").value,
					username: document.getElementById("username").value,
					pw:document.getElementById("pw").value,
					confirmpassword: document.getElementById("confirmpassword").value,
					userType: document.getElementById("sel1").value}, function(data){
					var data1=data[0], data2=data[1], data3 = data[2]; 
					
					if(data3 == "true")
					{
						$('#DupeModal').modal("show");
					}
					else if(data2 == "false")
					{
						$('#PWMismatchModal').modal("show");						
					}
					else if(data1 == "true")
					{
						$('#SuccessCreatingModal').modal("show");
						
					}
					else
					{
						$('#FailCreatingModal').modal("show");
					}
				});
			}
			
		}
		function Retry()
		{
			$('#FailCreatingModal').modal("hide");
			$('#confirmationModal').modal("show");
		}
		
		function Yehey()
		{
			window.location.replace("admincreate.jsp");
		}
		
		
	</script>



</body>

</html>