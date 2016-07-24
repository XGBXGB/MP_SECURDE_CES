<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@page import="controller.Controller"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.TripWithCapacity"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--Import Google Icon Font-->
<title>Arrows Express</title>
<link href="http://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet"
	href="materialize/css/materialize.css" media="screen,projection" />
<link type="text/css" rel="stylesheet" href="css/main.css" />
<link type="text/css" rel="stylesheet" href="css/login.css"/>
<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>

<body>


<%
 	Controller controller = Controller.getInstance();
 	Calendar cal = Calendar.getInstance();
 	cal.add(Calendar.DAY_OF_MONTH, 1);
 	Date dateTomorrow = new Date(cal.getTimeInMillis());
 	SimpleDateFormat f = new SimpleDateFormat("hh:mm a");

 	controller.getAllRoutes();

 	ArrayList<TripWithCapacity> stcmnlTrips = controller.getTripsWithRouteAndDate("1", dateTomorrow);
 	ArrayList<TripWithCapacity> mnlstcTrips = controller.getTripsWithRouteAndDate("2", dateTomorrow);
 %>
	<div class="main-reserve"
		style="padding-top: 0; margin-top: 0 !important">
		<div class="container main-container">
			<div class="row">
				<div class="booking">
					<div class="row" style="margin: 0;">
						<div class="book-lbl" style="">
							Reserve a seat for tomorrow (<%=dateTomorrow%>)
						</div>
						<div class="other-dates-lbl">Reserve for a different date?
							Click here for more trips</div>
					</div>
				</div>
				<div class="col l6 m6 s12" style="padding-top: 10px;">
					<div class="upcoming-trip green">
						<div class="container">
							<div class="row">
								<div class="upcoming-trip-title white-text">Manila to STC</div>
								<div class="trip-list-container col s12">

									<form action="ReserveServlet" method="post">
										<input type="hidden" id="hiddenstuffId" name="hiddenstuff">
										<%
											for (int i = 0; i < mnlstcTrips.size(); i++) {
												TripWithCapacity trip = mnlstcTrips.get(i);
										%>
										<button id="<%=trip.getId()%>"
											class=" col s12 white btn <%if (trip.getNumReservation() == trip.getTempCapacity()) {%>disabled disabled-btn<%} else {%> upcoming-btn  waves-effect waves-light <%}%>"
											type="submit" name="action"
											onclick="clicked(<%=trip.getTripId()%>)">
											<div class="col s6 upcoming-lbl valign-wrapper">
												<div>
													<div class="col s12 upcoming-lbl-elem"><%=trip.getId()%></div>
													<div class="col s12 upcoming-lbl-elem"><%=f.format(trip.getDepTime())%></div>
												</div>
											</div>
											<div
												class="col s6 upcoming-lbl battery-container valign-wrapper">
												<div class="battery-outline">
													<%
														if (trip.getNumReservation() == trip.getTempCapacity()) {
													%>
													<div class="col s4 battery-cell cell-1"></div>
													<div class="col s4 battery-cell cell-2"></div>
													<div class="col s4 battery-cell cell-3"></div>

													<%
														} else if (trip.getTempCapacity() - trip.getNumReservation() <= 10
																	&& trip.getTempCapacity() - trip.getNumReservation() > 5) {
													%>
													<div class="col s4 battery-cell cell-1 yellow"></div>
													<div class="col s4 battery-cell cell-2 yellow"></div>
													<div class="col s4 battery-cell cell-3"></div>
													<%
														} else if (trip.getTempCapacity() - trip.getNumReservation() <= 5
																	&& trip.getTempCapacity() - trip.getNumReservation() > 0) {
													%>
													<div class="col s4 battery-cell cell-1 red"></div>
													<div class="col s4 battery-cell cell-2"></div>
													<div class="col s4 battery-cell cell-3"></div>

													<%
														} else {
													%>
													<div class="col s4 battery-cell cell-1 green"></div>
													<div class="col s4 battery-cell cell-2 green"></div>
													<div class="col s4 battery-cell cell-3 green"></div>
													<%
														}
													%>
												</div>

											</div>

										</button>
										<%
											}
										%>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col l6 m6 s12" style="padding-top: 10px;">
					<div class="upcoming-trip green">
						<div class="container">
							<div class="row">
								<div class="upcoming-trip-title white-text">STC to Manila</div>
								<div class="trip-list-container col s12">
									<form action="ReserveServlet" method="post">
									<input type="hidden" id="hiddenstuffId2" name="hiddenstuff">
										<%
											for (int i = 0; i < stcmnlTrips.size(); i++) {
												TripWithCapacity trip = stcmnlTrips.get(i);
										%>
										<button id="<%=trip.getId()%>"
											class=" col s12 white btn <%if (trip.getNumReservation() == trip.getTempCapacity()) {%>disabled disabled-btn<%} else {%> upcoming-btn  waves-effect waves-light <%}%>"
											type="submit" name="action"
											onclick="clicked2(<%=trip.getTripId()%>)">
											<div class="col s6 upcoming-lbl valign-wrapper">
												<div>
													<div class="col s12 upcoming-lbl-elem"><%=trip.getId()%></div>
													<div class="col s12 upcoming-lbl-elem"><%=f.format(trip.getDepTime())%></div>
												</div>
											</div>
											<div
												class="col s6 upcoming-lbl battery-container valign-wrapper">
												<div class="battery-outline">
													<%
														if (trip.getNumReservation() == trip.getTempCapacity()) {
													%>
													<div class="col s4 battery-cell cell-1"></div>
													<div class="col s4 battery-cell cell-2"></div>
													<div class="col s4 battery-cell cell-3"></div>

													<%
														} else if (trip.getTempCapacity() - trip.getNumReservation() <= 10
																	&& trip.getTempCapacity() - trip.getNumReservation() > 5) {
													%>
													<div class="col s4 battery-cell cell-1 yellow"></div>
													<div class="col s4 battery-cell cell-2 yellow"></div>
													<div class="col s4 battery-cell cell-3"></div>
													<%
														} else if (trip.getTempCapacity() - trip.getNumReservation() <= 5
																	&& trip.getTempCapacity() - trip.getNumReservation() > 0) {
													%>
													<div class="col s4 battery-cell cell-1 red"></div>
													<div class="col s4 battery-cell cell-2"></div>
													<div class="col s4 battery-cell cell-3"></div>

													<%
														} else {
													%>
													<div class="col s4 battery-cell cell-1 green"></div>
													<div class="col s4 battery-cell cell-2 green"></div>
													<div class="col s4 battery-cell cell-3 green"></div>
													<%
														}
													%>
												</div>

											</div>

										</button>
										<%
											}
										%>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>

	</section>






      <!-- google sign-in files js-->
      <meta name="google-signin-client_id" content="1035312031605-s9lh4kq3vre7768ffc3h4t11jmodempt.apps.googleusercontent.com">
	  <script type="text/javascript" src="js/init.js"></script>
	  <script type="text/javascript" src="https://apis.google.com/js/platform.js?onload=onLoad"></script> <!-- google sign-in -->
      <script type="text/javascript" src="js/logout.js"></script>
      
      <!--Import jQuery before materialize.js-->
      <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
      <script type="text/javascript" src="materialize/js/materialize.js"></script>
      <script type="text/javascript" src="js/navbar.js"></script>

	
	<script>
	function clicked(id) {
		document.getElementById("hiddenstuffId").value = id;
	}
	function clicked2(id) {
		document.getElementById("hiddenstuffId2").value = id;
	}
		$(document).ready(function() {

			$('select').material_select();
		});

		$('.datepicker').pickadate({
			selectMonths : true, // Creates a dropdown to control month
			selectYears : 15
		// Creates a dropdown of 15 years to control year
		});
		$(".button-collapse").sideNav();
	</script>
</body>
</html>