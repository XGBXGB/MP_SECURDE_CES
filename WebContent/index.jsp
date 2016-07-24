<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/signup.css" rel="stylesheet">
<link href="css/bootstrap-formhelpers.min.css" rel="stylesheet"
	media="screen">
</head>

<body>
	<div class="container">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Sign In</div>
					<div
						style="float: right; font-size: 80%; position: relative; top: -10px">
						<a href="#">Forgot password?</a>
					</div>
				</div>

				<div style="padding: 30px" class="panel-body">
					<form id="loginform" method="post" class="form-horizontal" action="LoginServlet"
						data-toggle="validator" role="form">
						<div style="display: none" id="login-alert"
							class="alert alert-danger col-sm-12">${errorMessage}</div>
						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span> <input id="login-username"
									type="text" class="form-control" name="username" value=""
									placeholder="username or email" required>
							</div>
    						<div class="help-block with-errors"></div>
						</div>
						
						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i></span> <input id="login-password"
									type="password" class="form-control" name="password"
									placeholder="password" required>
							</div>
							<div class="help-block with-errors" style="margin:0 !important; padding:0 !important"></div>
						</div>



						<div style="margin-top: 10px;padding-left:0px;margin-left:0" class="">
							<div class="col-sm-12 controls form-group">
								<a onclick="$(this).closest('form').submit()" id="btn-login"
									href="#" class="btn btn-success">Login </a>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-12 control">
								<div style="font-size: 85%">
									Don't have an account! <a href="#"
										onClick="$('#loginbox').hide(); $('#signupbox').show()">
										Sign Up Here </a>
								</div>
							</div>
						</div>
					</form>
				</div>







			</div>
		</div>
	</div>

	<!-- START OF SIGN UP FORM NIGGAAHHHHHH-->
	<div id="signupbox" style="display: none; margin-top: 50px"
		class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		<div class="panel panel-info">
			<div class="panel-heading">
				<div class="panel-title">Sign Up</div>
				<div
					style="float: right; font-size: 85%; position: relative; top: -10px">
					<a id="signinlink" href="#"
						onclick="$('#signupbox').hide(); $('#loginbox').show()">Sign
						In</a>
				</div>
			</div>
			<div class="panel-body">
				<form action="SignUpServlet" id="signupform" method="post" class="form-horizontal" data-toggle="validator" role="form">
					<div id="signupalert" style="display: none"
						class="alert alert-danger">
						<p>Error:</p>
						<span></span>
					</div>
					<span class="info-category">Personal Information:</span>
					<label class="col-md-3 control-label" for="firstname">Name</label>
					<div class="form-group col-md-4">
							<input pattern="^[ A-z]{1,}$" type="text" class="form-control" name="firstname"
									placeholder="First Name" required>
					</div>
					<div class="form-group col-md-2">
							<input maxlegnth="1" pattern="[A-z]" type="text" class="form-control" name="middleinitial"
									placeholder="M.I." required>
					</div>
					<div class="form-group col-md-3">
							<input pattern="^[ A-z]{1,}$" type="text" class="form-control" name="lastname"
									placeholder="Last Name" required>
					</div>
					

					<label for="username" class="col-md-3 control-label">Username</label>
					<div class="form-group col-md-9">
							<input data-minlength="6" pattern="^[_A-z0-9]{1,}$" type="text" class="form-control" name="username"
								placeholder="Minimum of 6 characters. Composed of numbers, letters and _" required>
						<div class="help-block with-errors"></div>
					</div>
					<label for="password" class="col-md-3 control-label">Password</label>
					<div class="form-group col-md-9">
							<input pattern="^[_A-z0-9]{1,}$" data-minlength="6" id="passwd" type="password" class="form-control" name="password"
								placeholder="Minimum of 6 characters" required>
							<div class="help-block with-errors"></div>
					</div>
					<label for="password" class="col-md-3 control-label">Confirm Pass</label>
					<div class="form-group col-md-9">
							<input data-match="#passwd" type="password" class="form-control" name="confirmpassword"
								placeholder="Match input with password" required>
							<div class="help-block with-errors" style="margin:0 !important; padding:0 !important"></div>
					</div>
					<label for="email" class="col-md-3 control-label">Email</label>
					<div class="form-group col-md-9">
							<input type="email" class="form-control" name="email"
								placeholder="Email Address" required>
							<div class="help-block with-errors"></div>
					</div>
					<hr>
					<span class="info-category">Billing Address:</span>
					<label class="col-md-3 control-label">House #</label>
					<div class="form-group col-md-9">
							<input for="housenoB" pattern="^[0-9]{1,}$" type="text" class="form-control" name="housenoB"
								placeholder="House Number" required>
							<div class="help-block with-errors"></div>
					</div>
					<label for="streetB" class="col-md-3 control-label">Street</label>
					<div class="form-group col-md-9">
							<input pattern="^[ A-z]{1,}$" type="text" class="form-control" name="streetB"
								placeholder="Street" required>
							<div class="help-block with-errors"></div>
					</div>
					<label for="subdivisionB" class="col-md-3 control-label">Subdivision</label>
					<div class="form-group col-md-9">
							<input pattern="^[ A-z]{1,}$" type="text" class="form-control" name="subdivisionB"
								placeholder="Subdivision" required>
							<div class="help-block with-errors"></div>
					</div>
					<label for="cityB" class="col-md-3 control-label">City</label>
					<div class="form-group col-md-9">
							<input pattern="^[ A-z]{1,}$" type="text" class="form-control" name="cityB"
								placeholder="City" required>
							<div class="help-block with-errors"></div>
					</div>
					<label for="postalcodeB" class="col-md-3 control-label">Postal Code</label>
					<div class="form-group col-md-9">
							<input for="housenoB" pattern="^[0-9]{1,}$" type="text" class="form-control" name="postalcodeB"
								placeholder="Postal Code" required>
							<div class="help-block with-errors"></div>
					</div>
					<label for="countryB" class="col-md-3 control-label">Country</label>
					<div class="form-group col-md-9">
							<select name="countryB" class="form-control bfh-countries"
								data-country="PH"></select>
					</div>

					<hr>
					<span class="info-category">Shipping Address:</span>
					<label for="housenoS" class="col-md-3 control-label">House
							#</label>
					<div class="form-group col-md-9">
							<input pattern="^[0-9]{1,}$" type="text" class="form-control" name="housenoS"
								placeholder="House Number" required>
							<div class="help-block with-errors"></div>
					</div>
					<label for="streetS" class="col-md-3 control-label">Street</label>
					<div class="form-group col-md-9">
							<input pattern="^[ A-z]{1,}$" type="text" class="form-control" name="streetS"
								placeholder="Street" required>
							<div class="help-block with-errors"></div>
					</div>
					<label for="subdivisionS" class="col-md-3 control-label">Subdivision</label>
					<div class="form-group col-md-9">
							<input pattern="^[ A-z]{1,}$" type="text" class="form-control" name="subdivisionS"
								placeholder="Subdivision" required>
							<div class="help-block with-errors"></div>
					</div>
					<label for="cityS" class="col-md-3 control-label">City</label>
					<div class="form-group col-md-9">
							<input pattern="^[ A-z]{1,}$" type="text" class="form-control" name="cityS"
								placeholder="City" required>
							<div class="help-block with-errors"></div>
					</div>
					<label for="postalcodeS" class="col-md-3 control-label">Postal
							Code</label>
					<div class="form-group col-md-9">
							<input pattern="^[0-9]{1,}$" type="text" class="form-control" name="postalcodeS"
								placeholder="Postal Code" required>
							<div class="help-block with-errors"></div>
					</div>
					<label for="countryS" class="col-md-3 control-label">Country</label>
					<div class="form-group col-md-9">
							<select name="countryS" class="form-control bfh-countries"
								data-country="PH"></select>
					</div>

					<div class="form-group">
						<!-- Button -->
						<div class="col-md-offset-5 col-md-3">
							<a onclick="$(this).closest('form').submit()" id="btn-signup" href="#" class="btn btn-info">
								<i class="icon-hand-right"></i> Sign Up
							</a>
						</div>
					</div>


				</form>
			</div>
		</div>
	</div>
	<!-- END OF SIGN UP FORM NIGGAAHHHHHH-->


	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap-formhelpers.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/validator.js"></script>
</body>
</html>