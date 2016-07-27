<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="model.User"%>
	<%@page import="model.Product"%>
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

	<%User u = (User) session.getAttribute("user"); session.setAttribute("user", u);%>
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
			    <li><a href="#"><span class="glyphicon glyphicon-user"></span> Hello <%=u.getFirstName()%>!</a></li>
                <li><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> <span class="glyphicon glyphicon-shopping-cart"></span> (0) - View cart</a></li>
                <li>
	                <form action="LoginServlet" method="GET">
	                	<button type="submit" name="logout" style="margin-top:14px;background-color:transparent;color:#9d9d9d;border:none"><span class="glyphicon glyphicon-log-out"></span>Logout</button>
	                </form>
                </li>
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
                	<a href="#" onclick="loadProducts(-1)" class="list-group-item">All</a>
                    <a href="#" onclick="loadProducts(1)" class="list-group-item">Boots</a>
                    <a href="#" onclick="loadProducts(2)" class="list-group-item">Shoes</a>
                    <a href="#" onclick="loadProducts(3)" class="list-group-item">Sandals</a>
                    <a href="#" onclick="loadProducts(4)" class="list-group-item">Slippers</a>
                </div>
            </div>

            <div class="col-md-9">

                <div class="row carousel-holder">

                    <div class="col-md-12">
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            </ol>
                            <div class="carousel-inner">
                                <div class="item active">
                                    <img class="slide-image" src="http://placehold.it/800x300" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="http://placehold.it/800x300" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="http://placehold.it/800x300" alt="">
                                </div>
                            </div>
                            <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                    </div>

                </div>
                <form id = "productform" action="ProductServlet" method = "GET">
                <input type = "hidden" id = "selectedProduct" name = "selectedProduct" value = "-1">
				<%
				Controller c = Controller.getInstance();
				ArrayList<Product> products;
				if(session.getAttribute("clickedCategoryInSingleProducts")!=null){
					products = c.getProductsviaCategory((Integer)session.getAttribute("clickedCategoryInSingleProducts"));
					session.removeAttribute("clickedCategoryInSingleProducts");
				}else{
					products = c.getAllProducts();
				}
				%>
                <div class="row products-list">
					<%for(int i=0; i<products.size(); i++){ %>
                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                            <img src="http://placehold.it/320x150" alt="">
                            <div class="caption">
                                <h4 class="pull-right">$<%=products.get(i).getPrice() %></h4>
                                <h4><a id="<%=products.get(i).getId() %>" href="javascript:{}" onclick="pressed(this);document.getElementById('productform').submit(); return false;"><%=products.get(i).getName() %></a>
                                </h4>
                                <p><%=products.get(i).getDescription() %></p>
                            </div>
                            <!-- div class="buying">
                                <a id="buy<%=products.get(i).getId() %>" href="javascript:{}" onclick="pressed(this);document.getElementById('buyproductform').submit(); return false;">buy this product</a>
                            </div> -->
                        </div>
                    </div>
                    <%} %>
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
    <script>
    function loadProducts(categoryId){
    	$.post('SelectCategoryServlet', { categoryId: categoryId }, function(responseJson){
    		var mainDiv = $('.products-list');
    		mainDiv.empty();
    		if(responseJson.length>0){
    			$.each(responseJson, function(key,value){
    				var outerDiv = $("<div></div>").addClass("col-sm-4 col-lg-4 col-md-4");
            		
            		var thumbnailDiv = $("<div></div>").addClass("thumbnail");
            		var imgDiv = $("<img></img>").attr("src","http://placehold.it/320x150");
            		var captionDiv = $("<div></div>").addClass("caption");
            		var h4PullRight = $("<h4></h4>").addClass("pull-right");
            		h4PullRight.html(value['price']);
            		h4PullRight.appendTo(captionDiv);
            		var h4Name = $("<h4></h4>");
            		var aElem = $("<a></a>").attr("id", value['id']);
            		aElem.html(value['name']);
            		aElem.appendTo(h4Name);
            		h4Name.appendTo(captionDiv);
            		var pElem = $("<p></p>");
            		pElem.html(value['description']);
            		pElem.appendTo(captionDiv);
            		var ratingDiv= $("<div></div>").addClass("buying");
            		var aRating = $("<a></a>").html("buy product");
            		aRating.appendTo(ratingDiv);
            		
            		imgDiv.appendTo(thumbnailDiv);
            		captionDiv.appendTo(thumbnailDiv);
            		ratingDiv.appendTo(thumbnailDiv);
            		thumbnailDiv.appendTo(outerDiv);
            		outerDiv.appendTo(mainDiv);
            		
    			});
    		}else{
    			var noResultsDiv = $("<div></div>").addClass("no-result-div col-sm-12 col-lg-12 col-md-12");
    			noResultsDiv.html("Sorry, there are no products of this category.");
    			noResultsDiv.appendTo(mainDiv);
    		}
    	});
    }
    
	function pressed(element) {
		var pressedBtn = element.id;
		document.getElementById("selectedProduct").value = document.getElementById(pressedBtn).id;
	}
    
    </script>
    
    

</body>

</html>
