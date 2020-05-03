<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  

<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Account</title>
  <!-- Bootstrap core CSS -->
  <link rel="stylesheet" href="webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>
  
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
           <form:form class="form"  action="edit" modelAttribute="accountDTO" method="post" autocomplete="off">
              <div class="form-group row">
                  <form:errors path="*" cssClass="alert alert-danger p-1" />
              </div>
              <div class="form-group row">
                  <label class="col-3 col-form-label form-control-label">Account Name</label>
                  <div class="col-3">
                     <form:input class="form-control" path="name"/>
                  </div>
                  <div class="col-6">
                    <form:errors path="name" cssClass="alert alert-danger p-1"  />            
                  </div>
                  <form:hidden path="accountId" />
              </div>
              <div class="form-group row">
                <label class="col-3 col-form-label form-control-label">Account Type</label>
                <div class="col-3">
                     <form:select path="type" class="form-control" >
                     <form:option value="">Select One</form:option>
                     <form:option value="Savings">Savings</form:option>
                     <form:option value="Current">Current</form:option>                   
                   </form:select> 
                </div>
                <div class="col-6">
                 <form:errors path="type" cssClass="alert alert-danger p-1"  />  
                </div>
            </div>
	            <div class="form-group row">
	              <label class="col-3 col-form-label form-control-label">Amount</label>
	              <div class="col-3">
	                   <form:input id="amount" path="amount" class="form-control" />
	              </div>
	              <div class="col-6">
	               <form:errors path="amount" cssClass="alert alert-danger p-1"  />  
	              </div>
	          </div>
	          <div class="form-group row">
	              <label class="col-3 col-form-label form-control-label"></label>
	              <div class="col-9">
	                  <sec:authorize access="hasRole('ADMIN')"> 
	   					 <input type="button" id="back"  data-id="adminlanding" class="btn btn-secondary" value="Cancel"> 
	   	              </sec:authorize>
					  <sec:authorize access="hasRole('USER')">  
	   					 <input type="button" id="back"  data-id="userlanding" class="btn btn-secondary" value="Cancel"> 
	   	              </sec:authorize>    
	                  <input type="reset" class="btn btn-info" value="Reset">
	                  <input type="submit" class="btn btn-info" value="Update Changes">
	              </div>
	          </div>
           </form:form>
       </div>
    </div>
  </div>
  

  <!-- Bootstrap core JavaScript -->
   <script type="text/javascript" src="webjars/jquery/3.4.1/jquery.min.js"></script>
   <script type="text/javascript" src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
   
   <script type="text/javascript">

    $("#amount").attr({
	    "type" : "number",
	});

  $('#back').on('click', function (e) {
 	 url = $(this).data("id"); 
 	 window.location.href = "/"+url;
  }); 
  
  </script>
   
</body>

</html>
