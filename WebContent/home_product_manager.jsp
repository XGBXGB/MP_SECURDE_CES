<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
	<input type="hidden" id="editId"/>
	<div class="modal fade" id="deleteModal" role="dialog">
		<div class="modal-dialog">
	    
	      <!-- Modal content-->
	     	<div class="modal-content">
	     		<div class="modal-header">
	        		<button type="button" class="close" data-dismiss="modal">&times;</button>
	         		<h4 class="modal-title">Confirm Delete</h4>
	       		</div>
	       		<div class="modal-body" id="deleteModalBody">
	       		</div>
	       		<div class="modal-footer">
	        	 	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        	 	<button  id="deleteButton" type="button" onclick="" class="btn btn-danger" data-dismiss="modal">Delete</button>
	       		</div>
	     	</div>
	   	</div>
	</div>
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" 
     aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog">
	        <div class="modal-content">
	            <!-- Modal Header -->
	            <div class="modal-header">
	                <button type="button" class="close" 
	                   data-dismiss="modal">
	                       <span aria-hidden="true">&times;</span>
	                       <span class="sr-only">Close</span>
	                </button>
	                <h4 class="modal-title" id="myModalLabel">
	                    Add New Product
	                </h4>
	            </div>
	            
	                
	            <form class="form-horizontal">
	            <!-- Modal Body -->
		            <div class="modal-body">
	                	<div class="form-group">
	                    	<label  class="col-sm-2 control-label"
	                              for="addProductName">Product Name</label>
	                    	<div class="col-sm-10">
	                        	<input type="text" class="form-control" id="addProductName" placeholder="Name"/>
	                    	</div>
	                  	</div>
	                  	<div class="form-group">
	                    	<label class="col-sm-2 control-label" for="addCategory" >Category</label>
	                    	<div class="col-sm-10">
	                        	<select id="addCategory" class="form-control">
	                        		<option value="1">Boots</option>
	                        		<option value="2">Shoes</option>
	                        		<option value="3">Sandals</option>
	                        		<option value="4">Slippers</option>
	                        	</select>
	                    	</div>
	                  	</div>
	                	<div class="form-group">
	                    	<label  class="col-sm-2 control-label"
	                              for="addPrice">Price</label>
	                    	<div class="col-sm-10">
	                        	<input type="number" class="form-control" id="addPrice"/>
	                    	</div>
	                  	</div>
	                  	
	                  	<div class="form-group">
  							<label for="addDescription" class="col-sm-2 control-label" >Description:</label>
	                    	<div class="col-sm-10">
  								<textarea class="form-control" rows="5" id="addDescription"></textarea>
  							</div>
						</div>
	                  	<div class="form-group">
		                  	<label class="col-sm-2 control-label"
		                              for="addImage">Image</label>
		                              <div class="col-sm-10">
		                  	<div id="addImage" class="input-group image-preview">
				                <input id="addImagePath" type="text" class="form-control image-preview-filename" disabled="disabled"> <!-- don't give a name === doesn't send on POST/GET -->
				                <span class="input-group-btn">
				                    <!-- image-preview-clear button -->
				                    <button type="button" class="btn btn-default image-preview-clear" style="display:none;">
				                        <span class="glyphicon glyphicon-remove"></span> Clear
				                    </button>
				                    <!-- image-preview-input -->
				                    <div class="btn btn-default image-preview-input">
				                        <span class="glyphicon glyphicon-folder-open"></span>
				                        <span class="image-preview-input-title">Browse</span>
				                        <input type="file" accept="image/png, image/jpeg, image/gif" name="input-file-preview"/> <!-- rename it -->
				                    </div>
				                </span>
	            			</div>
	            			</div>
	           			</div>
		            </div>
		            <!-- Modal Footer -->
		            <div class="modal-footer">
		                <button type="button" class="btn btn-default" data-dismiss="modal"> Cancel </button>
		                <button id="addButton" type="submit" class="btn btn-success" data-dismiss="modal"> Add </button>
		            </div>
	            
	            </form>
        	</div>
    	</div>
	</div>
	
	<div class="modal fade" id="editModal" tabindex="-1" role="dialog" 
     aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog">
	        <div class="modal-content">
	            <!-- Modal Header -->
	            <div class="modal-header">
	                <button type="button" class="close" 
	                   data-dismiss="modal">
	                       <span aria-hidden="true">&times;</span>
	                       <span class="sr-only">Close</span>
	                </button>
	                <h4 class="modal-title" id="myModalLabel">
	                    Edit Product
	                </h4>
	            </div>
	            
	                
	            <form class="form-horizontal" role="form">
	            <!-- Modal Body -->
		            <div class="modal-body">
	                	<div class="form-group">
	                    	<label  class="col-sm-2 control-label"
	                              for="editProductName">Product Name</label>
	                    	<div class="col-sm-10">
	                        	<input type="text" class="form-control" id="editProductName" placeholder="Name"/>
	                    	</div>
	                  	</div>
	                  	<div class="form-group">
	                    	<label class="col-sm-2 control-label" for="editCategory" >Category</label>
	                    	<div class="col-sm-10">
	                        	<select id="editCategory" class="form-control">
	                        		<option value="1">Boots</option>
	                        		<option value="2">Shoes</option>
	                        		<option value="3">Sandals</option>
	                        		<option value="4">Slippers</option>
	                        	</select>
	                    	</div>
	                  	</div>
	                	<div class="form-group">
	                    	<label  class="col-sm-2 control-label"
	                              for="editPrice">Price</label>
	                    	<div class="col-sm-10">
	                        	<input type="number" class="form-control" id="editPrice"/>
	                    	</div>
	                  	</div>
	                  	<div class="form-group">
  							<label for="editDescription" class="col-sm-2 control-label" >Description:</label>
	                    	<div class="col-sm-10">
  								<textarea class="form-control" rows="5" id="editDescription"></textarea>
  							</div>
						</div>
	                  	<div class="form-group">
		                  	<label class="col-sm-2 control-label"
		                              for="editImage">Image</label>
		                              <div class="col-sm-10">
		                  	<div id="editImage" class="input-group image-preview">
				                <input id="editImagePath" type="text" class="form-control image-preview-filename" disabled="disabled"> <!-- don't give a name === doesn't send on POST/GET -->
				                <span class="input-group-btn">
				                    <!-- image-preview-clear button -->
				                    <button type="button" class="btn btn-default image-preview-clear" style="display:none;">
				                        <span class="glyphicon glyphicon-remove"></span> Clear
				                    </button>
				                    <!-- image-preview-input -->
				                    <div class="btn btn-default image-preview-input">
				                        <span class="glyphicon glyphicon-folder-open"></span>
				                        <span class="image-preview-input-title">Browse</span>
				                        <input type="file" accept="image/png, image/jpeg, image/gif" name="input-file-preview"/> <!-- rename it -->
				                    </div>
				                </span>
	            			</div>
	            			</div>
	           			</div>
		            </div>
		            <!-- Modal Footer -->
		            <div class="modal-footer">
		                <button type="button" class="btn btn-default" data-dismiss="modal"> Cancel </button>
		                <button id="editButton" class="btn btn-primary" data-dismiss="modal"> Save Changes </button>
		            </div>
	            
	            </form>
        	</div>
    	</div>
	</div>

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
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> Hello <%=u.getFirstName()%>!</a></li>
			    <li>
	                <form action="LoginServlet" method="GET">
	                	<button type="submit" name="logout" style="margin-top:14px;background-color:transparent;color:#9d9d9d;border:none"><span class="glyphicon glyphicon-log-out"></span>Logout</button>
	                </form>
                </li>
		    </ul>
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <div class="col-md-3">
                <p class="lead">Edit Products</p>
                <div class="list-group">
                	<a href="#" onclick="loadProducts(-1)" class="list-group-item">All</a>
                    <a href="#" onclick="loadProducts(1)" class="list-group-item">Boots</a>
                    <a href="#" onclick="loadProducts(2)" class="list-group-item">Shoes</a>
                    <a href="#" onclick="loadProducts(3)" class="list-group-item">Sandals</a>
                    <a href="#" onclick="loadProducts(4)" class="list-group-item">Slippers</a>
                </div>
            </div>

            <div class="col-md-9">

                <div class="row products-list">
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
    <a href="" data-toggle="modal" data-target="#addModal" class="float">+</a>
    
    <script>
    $(document).on('click', "#addButton", function(){
    	var name = $("#addProductName").val();
    	var categoryId = $("#addCategory").val();
    	var price = $("#addPrice").val();
    	var description = $("#addDescription").val();
    	var imagePath = $("#addImagePath").val();
    	
    	$.post('AddProductServlet', { name: name, categoryId: categoryId, price: price, description: description, imagePath: imagePath}, function() {
    		loadProducts(-1);
    	});
    });
    
    $(document).on('click', "#editButton", function(){
    	var id = $("#editId").val();
    	var name = $("#editProductName").val();
    	var categoryId = $("#editCategory").val();
    	var price = $("#editPrice").val();
    	var description = $("#editDescription").val();
    	var imagePath = $("#editImagePath").val();
    	
    	$.post('EditProductServlet', { id: id, name: name, categoryId: categoryId, price: price, description: description, imagePath: imagePath}, function() {
    		loadProducts(-1);
    	});
    });
    
    $(document).on('click', '#close-preview', function(){ 
        $('.image-preview').popover('hide');
        // Hover befor close the preview
        $('.image-preview').hover(
            function () {
               $('.image-preview').popover('show');
            }, 
             function () {
               $('.image-preview').popover('hide');
            }
        );    
    });

    $(function() {
        // Create the close button
        var closebtn = $('<button/>', {
            type:"button",
            text: 'x',
            id: 'close-preview',
            style: 'font-size: initial;',
        });
        closebtn.attr("class","close pull-right");
        // Set the popover default content
        $('.image-preview').popover({
            trigger:'manual',
            html:true,
            title: "<strong>Preview</strong>"+$(closebtn)[0].outerHTML,
            content: "There's no image",
            placement:'bottom'
        });
        // Clear event
        $('.image-preview-clear').click(function(){
            $('.image-preview').attr("data-content","").popover('hide');
            $('.image-preview-filename').val("");
            $('.image-preview-clear').hide();
            $('.image-preview-input input:file').val("");
            $(".image-preview-input-title").text("Browse"); 
        }); 
        // Create the preview image
        $(".image-preview-input input:file").change(function (){     
            var img = $('<img/>', {
                id: 'dynamic',
                width:250,
                height:200
            });      
            var file = this.files[0];
            var reader = new FileReader();
            // Set preview image into the popover data-content
            reader.onload = function (e) {
                $(".image-preview-input-title").text("Change");
                $(".image-preview-clear").show();
                $(".image-preview-filename").val(file.name);            
                img.attr('src', e.target.result);
                $(".image-preview").attr("data-content",$(img)[0].outerHTML).popover("show");
            }        
            reader.readAsDataURL(file);
        });  
    });
    
    $(document).on("click", ".product-name", function() {
    	var productId = $(this).attr('id').substring(5);
    	var name = $(this).text();
    	var price = $("#price_" + productId).text().substring(1);
    	var category = $("#cat_" + productId).text();
    	var description = $("#description_" + productId).text();
    	var imagePath = $("#img_" + productId).attr('src');
    	
    	$("#editId").val(productId);
    	
    	$("#editProductName").val(name);
    	$("#editCategory").val(category);
    	$("#editPrice").val(price);
    	$("#editDescription").val(description);
    	$("#editImagePath").val(imagePath);
    	
    }); 
    
    $(document).on("click", ".x-button", function () {
    	var productId = $(this).attr('id').substring(2);
    	var name = $("#name_" + productId).text().bold();
    	$('#deleteModalBody').html("Are you sure you want to delete " + name + " from the list?");
    	$('#deleteButton').attr("onclick", "deleteProduct(\"" + productId + "\")");
   	});
    
    function deleteProduct(productId) {
    	$.post('DeleteProductServlet', { id: productId}, function() {
    		loadProducts(-1);
    	});
    	$('#deleteButton').attr("onclick", "");
    }
    
    function loadProducts(categoryId){
    	$.post('SelectCategoryServlet', { categoryId: categoryId }, function(responseJson){
    		var mainDiv = $('.products-list');
    		mainDiv.empty();
    		if(responseJson.length>0){
    			$.each(responseJson, function(key,value){
    				var outerDiv = $("<div></div>").addClass("col-sm-4 col-lg-4 col-md-4");
            		var inputCat = $("<input></input>").attr("type", "hidden").attr("id", "cat_" + value['id']);
            		inputCat.html(value['categoryId']);
            		inputCat.appendTo(outerDiv);
            		var thumbnailDiv = $("<div></div>").addClass("thumbnail");
            		var imgContainer = $("<div></div>").attr("id", "img_container");
            		var imgDiv = $("<img></img>").attr("src","valak.jpg").attr("id", "img_" + value['id']);
            		var xButton = $("<button></button>").addClass("x-button");
            		imgDiv.appendTo(imgContainer);
            		xButton.attr("data-toggle", "modal");
            		xButton.attr("data-target", "#deleteModal");
            		xButton.attr("id", "x_" + value['id']);
            		xButton.html("x");
            		xButton.appendTo(imgContainer);
            		var captionDiv = $("<div></div>").addClass("caption");
            		var h4PullRight = $("<h4></h4>").addClass("pull-right").attr("id", "price_" + value['id']);
            		h4PullRight.html("$" + value['price']);
            		h4PullRight.appendTo(captionDiv);
            		var h4Name = $("<h4></h4>");
            		var aElem = $("<a></a>").attr("id", "name_" + value['id']).addClass("product-name");
            		aElem.attr("data-toggle", "modal");
            		aElem.attr("data-target", "#editModal");
            		aElem.html(value['name']);
            		aElem.appendTo(h4Name);
            		h4Name.appendTo(captionDiv);
            		var pElem = $("<p></p>").attr("id", "description_" + value['id']);
            		pElem.html(value['description']);
            		pElem.appendTo(captionDiv);
            		
            		imgContainer.appendTo(thumbnailDiv);
            		captionDiv.appendTo(thumbnailDiv);
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
	
	$(document).ready(function() {
		loadProducts(-1);
	});
    </script>
    
</body>

</html>

</html>