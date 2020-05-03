<!DOCTYPE html>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Account</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" />
</head>

<body>
	<!-- Navigation -->
	 <jsp:include page="menu.jsp"/>

	<!-- Page Content -->
	<div class="container">
		<div class="card card-outline-secondary">
			<div class="card-header">
				<h3 class="mb-0">Account Information</h3>
			</div>
			<div class="card-body">
				<div class="row">
					<div class="col-12">
						<label class="col-form-label form-control-label">Account Id: ${accountDTO.accountId}</label>
					</div>
				</div>
				
				<div class="row">
					<div class="col-12">
						<label class="col-form-label form-control-label">Account Name: ${accountDTO.name}</label>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<label class="col-form-label form-control-label">Account Type: ${accountDTO.type}</label>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<label class="col-form-label form-control-label">Amount: ${accountDTO.amount}</label>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<label class="col-form-label form-control-label">Created By: ${accountDTO.createdBy}</label>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<label class="col-form-label form-control-label">Created Name: ${accountDTO.createdName}</label>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<label class="col-form-label form-control-label">Created Ts: ${accountDTO.createdTs}</label>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<label class="col-form-label form-control-label">Modified By: ${accountDTO.lastModifiedBy}</label>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<label class="col-form-label form-control-label">Modified Name: ${accountDTO.lastModifiedName}</label>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<label class="col-form-label form-control-label">Modified Ts: ${accountDTO.lastModifiedTs}</label> 
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<label class="col-form-label form-control-label">Version: ${accountDTO.version}</label>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
					   <sec:authorize access="hasRole('ADMIN')">  
    					 <input type="button" id="back"  data-id="adminlanding" class="btn btn-info" value="Back"> 
    	               </sec:authorize>
					    <sec:authorize access="hasRole('USER')">  
    					 <input type="button" id="back"  data-id="userlanding" class="btn btn-info" value="Back"> 
    	               </sec:authorize> 	
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript -->
	<script type="text/javascript" 	src="webjars/jquery/3.4.1/jquery.min.js"></script>
     <script type="text/javascript">
     
     $('#back').on('click', function (e) {
    	 url = $(this).data("id"); 
    	 window.location.href = "/"+url;
    })
     
     </script>
</body>

</html>
