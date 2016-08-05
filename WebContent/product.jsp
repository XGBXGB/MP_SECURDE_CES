<%@page import="model.User"%>
<%@page import="model.Transaction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.Controller"%>
<%@page import="model.Product"%>
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

    <title>Shop Item - Start Bootstrap Template</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/shop-item.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<%User u = (User) session.getAttribute("user"); %>
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Start Bootstrap</a>
            </div>
            <ul class="nav navbar-nav navbar-right">
			    <%if(u!=null) {%>
			    <li><a href="#"><span class="glyphicon glyphicon-user"></span> Hello <%=u.getFirstName()%>!</a></li>
                <li><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> <span class="glyphicon glyphicon-shopping-cart"></span> (0) - View cart</a></li>
                <li>
	                <form action="LoginServlet" method="GET">
	                	<button type="submit" name="logout" style="margin-top:14px;background-color:transparent;color:#9d9d9d;border:none"><span class="glyphicon glyphicon-log-out"></span>Logout</button>
	                </form>
                </li>
            <%}else{ %>
            	<li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            <%} %>
		    </ul>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#">About</a>
                    </li>
                    <li>
                        <a href="#">Services</a>
                    </li>
                    <li>
                        <a href="#">Contact</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">
	
        <div class="row">

            <div class="col-md-3">
                <p class="lead">Shop Name</p>
                <div class="list-group">
                	<a href="index.jsp" class="list-group-item">All</a>
                   <a href="#" onclick="viewProducts(1)" class="list-group-item">Boots</a>
                    <a href="#" onclick="viewProducts(2)" class="list-group-item">Shoes</a>
                    <a href="#" onclick="viewProducts(3)" class="list-group-item">Sandals</a>
                    <a href="#" onclick="viewProducts(4)" class="list-group-item">Slippers</a>
                </div>
            </div>

            <div class="col-md-9">
		<%Product p = (Product) session.getAttribute("product"); %>
                <div class="thumbnail">
                    <img class="img-responsive" src="http://placehold.it/800x300" alt="">
                    <div class="caption-full">
                        <h4 class="pull-right"><%=p.getPrice() %></h4>
                        <h4><a href="#"><%=p.getName() %></a>
                        </h4>
                        <!--  >p>See more snippets like these online store reviews at <a target="_blank" href="http://bootsnipp.com">Bootsnipp - http://bootsnipp.com</a>.</p>
                        <p>Want to make these reviews work? Check out
                            <strong><a href="http://maxoffsky.com/code-blog/laravel-shop-tutorial-1-building-a-review-system/">this building a review system tutorial</a>
                            </strong>over at maxoffsky.com!</p>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum</p-->
                        <%Controller con = new Controller(); %>
                        <h6><%=con.getCategory(p.getCategoryId())%></h6>
                        <p><%=p.getDescription() %></p>
                    </div>
                    <div class="ratings">
                        <p class="pull-right"><%=con.getTransactionsviaProductReviewed(p.getId()).size() %> reviews</p>
                        <p>
                        	<%
                        	int stars = 5;
                        	double score = con.getProductScore(p.getId());
                        	int total = 0;
                        	while(score > 1)
                        	{%>
                            <span class="glyphicon glyphicon-star"></span>
                            <%
                            score--; total++;
                        	}
                        	
                        	
                        	if(score > 0 && total < 5)
                        	{
                        	%>
                       		<span class="glyphicon glyphicon-star-half"></span>
                        	<%
                        	total++;
                        	}
                        	
                        	while(total != 5)
                        	{
                        	%>
                        	<span class="glyphicon glyphicon-star-empty"></span>
                            <%
                            total++;
                        	}
                            %>
                            
                            
                            <!--  span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star-empty"></span-->
                            <%=con.getProductScore(p.getId()) %> stars
                        </p>
                    </div>
                </div>

                <div class="well">

		<div id="confirmationModal" class="modal fade my-modal">
            <div class="modal-dialog my-modal-dialog">
                <div class="modal-content my-modal-content">
                    <div class="modal-header my-modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h2 class="modal-title">Confirmation</h2>
                    </div>
                    <div class="modal-body my-modal-body">
                        <div class="col-xs-12">
                            <p><h2>Are you sure you want to buy this Product?</h2></p>
                            <p><h4>NAME: <%=p.getName() %></h4></p>
                            <p><h4>CATEGORY: <%=con.getCategory(p.getCategoryId()) %></h4></p>
                            <p><h4>PRICE: <%=p.getPrice() %></h4></p>
                            <p><h4>DESCRIPTION: <%=p.getDescription() %></h4></p>
                            <br>
                            <p><h5>Please fill up the info below. The price will be deducted from your account. You will no longer be able to refund after you submit.</h5></p>
                        	<input type = "text" placeholder = "Credit card number" required></input>
                        	<br>
                        	<input type = "date" placeholder = "Expiry" required></input>
							<br>
                        	<input type = "text" placeholder = "CVS Pin" data-maxlength="6" required></input>
							<br>                        
                        	<input type = "number" placeholder = "Zipcode" data-maxlength="4" required></input>
                        	<br><br>
                        </div>
                        <div class="form-group clearfloat"></div>
                        <div class="floatright">
                            <button type="button" class="btn" onclick="setConfirmation(true);">Yes</button>
                            <button type="button" class="btn" onclick="setConfirmation(false);"data-dismiss="modal">No</button>    
                        </div>
                        <div class="clearfloat"></div>
                    </div>
                </div>
            </div>
        </div>

		<div id="SuccessBuyingModal" class="modal fade my-modal">
            <div class="modal-dialog my-modal-dialog">
                <div class="modal-content my-modal-content">
                    <div class="modal-header my-modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h2 class="modal-title">SUCCESS!</h2>
                    </div>
                    <div class="modal-body my-modal-body">
                        <div class="col-xs-12">
                            <p><h2>You have successfully bought this product!</h2></p>
                            <p><h4>NAME: <%=p.getName() %></h4></p>
                            <p><h4>CATEGORY: <%=con.getCategory(p.getCategoryId()) %></h4></p>
                            <p><h4>PRICE: <%=p.getPrice() %></h4></p>
                            <p><h4>DESCRIPTION: <%=p.getDescription() %></h4></p>
                            <br>
                            
                        <form id = "BuyWithRatingForm" action = "RatingServlet" method = "post">
                            <p><h5>The price has been deducted from your account. Please rate this product?</h5></p>
                            <input type="number" step="0.01" id="rateScore" name="rateScore" placeHolder="Score over five">
                            <input type="text" id="rateReview" name="rateReview" placeHolder="Review">
                        </div>
                        <div class="form-group clearfloat"></div>
                        <div class="floatright">
                            <button type="button" class="btn" onclick="RatingModal();">Review the Product</button>
                            <button type="button" class="btn" onclick="submitBuying();">Don't review the product</button>    
                        </form>
                        </div>
                        <div class="clearfloat"></div>
                    </div>
                </div>
            </div>
        </div>


				<form action= "ProductServlet" method = "post" id="BuyProductForm" onsubmit="return checkBuy();">
                <div class="text-right">
                	<%if(u != null){ %>
                    <input type = "submit" value ="Buy This" class="btn btn-success" href="javascript:{}">
                    <%}else{ %>
                    <a id="btn-login" href="login.jsp" class="btn btn-success">Buy This </a>
                    <%} %>
                </div>
				</form>
				
                <hr>
                
                
					<%
					ArrayList<Transaction> t = con.getTransactionsviaProductReviewed(p.getId());
					
					for(int x = 0; x < t.size(); x++)
					{
					%>
					<div class="row">
                        <div class="col-md-12">
                            <%
                        	stars = 5;
                        	score = t.get(x).getScore();
                        	total = 0;
                        	while(score > 1)
                        	{%>
                            <span class="glyphicon glyphicon-star"></span>
                            <%
                            score--; total++;
                        	}
                        	
                        	
                        	if(score > 0 && total < 5)
                        	{
                        	%>
                       		<span class="glyphicon glyphicon-star-half"></span>
                        	<%
                        	total++;
                        	}
                        	
                        	while(total != 5)
                        	{
                        	%>
                        	<span class="glyphicon glyphicon-star-empty"></span>
                            <%
                            total++;
                        	}
                            %>
                         
                            <%=con.getUser((t.get(x)).getUserId()).getFirstName()%>
                            <span class="pull-right"><%=t.get(x).getDate() %></span>
                            <p><%=t.get(x).getReview() %></p>
                        </div>
                    </div>
					<%
					}
					%>

                    <!-- >div class="row">
                        <div class="col-md-12">
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star-empty"></span>
                            Anonymous
                            <span class="pull-right">10 days ago</span>
                            <p>This product was great in terms of quality. I would definitely buy another!</p>
                        </div>
                    </div>

                    <hr>

                    <div class="row">
                        <div class="col-md-12">
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star-empty"></span>
                            Anonymous
                            <span class="pull-right">12 days ago</span>
                            <p>I've alredy ordered another one!</p>
                        </div>
                    </div>

                    <hr>

                    <div class="row">
                        <div class="col-md-12">
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star-empty"></span>
                            Anonymous
                            <span class="pull-right">15 days ago</span>
                            <p>I've seen some better than this, but not at this price. I definitely recommend this item.</p>
                        </div>
                    </div-->

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
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
	<script>
	function viewProducts(categoryId){
		$.post('GoToHomeServlet', {clickedCategoryInSingleProducts:categoryId}, function(data){
			window.location.replace(data);
		});
	}

	function checkBuy()
	{
		$('#confirmationModal').modal('show');
		return false;
	}
	
	function setConfirmation(retValue)
	{
		this.retValue = retValue;
		$('#confirmationModal').modal("hide");
		if(this.retValue)
		{	
			//$('#successBuyModal').modal("show");
			$('#SuccessBuyingModal').modal("show");
            //button.style.display = 'none';
            //button.type = 'submit';
            //document.getElementById("BuyProductForm").appendChild(button).click();
            //document.getElementById("BuyProductForm").removeChild(button);
		}
	}
	
	function submitBuying()
	{
		$('#SuccessBuyingModal').modal("hide");
		document.getElementById("BuyProductForm").submit();
	}
	
	function RatingModal()
	{
		$('#SuccessBuyingModal').modal("hide");
		document.getElementById("BuyWithRatingForm").submit();
	}
	
	</script>
</body>

</html>
