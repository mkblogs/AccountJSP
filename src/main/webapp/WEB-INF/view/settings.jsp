<!DOCTYPE html>
<html lang="en">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  

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
  <jsp:include page="menu.jsp"/>

  <!-- Page Content -->
  <div class="container">
    <div class="card card-outline-secondary">
      <div class="card-header">
        <h3 class="mb-0">Account Settings</h3>
       </div>
       <div class="card-body">
           <form:form class="form needs-validation" id="settingsForm"  action="settings" modelAttribute="settingsDTO" method="post" autocomplete="off">
               <div class="form-group row">
                 <label class="col-3 col-form-label form-control-label"></label>
                 <div class="col-3">
					<form:errors path="*" cssClass="alert alert-danger p-1" />                 
                 </div>              
              </div>
              <div class="form-group row">
                  <label class="col-3 col-form-label form-control-label">Connection Type</label>
                  <div class="col-3">
                      <form:select path="connectionType" class="form-control" >
                        <form:option value="">Select One</form:option>
                        <form:option value="PLAIN_JDBC">Plain_JDBC</form:option>
                       	<form:option value="TOMCAT">Tomcat DataSource</form:option>
                      	<form:option value="HIKARI">Hikari DataSource</form:option>
                       	<form:option value="ROW_MAPPER">Row Mapper With DBCP DataSource</form:option>
                       	<form:option value="ENTITY_MANAGER">Hibernate Entity Manager</form:option>
                       	<form:option value="JPA">JPA</form:option>
                       	<form:option value="QUERY_DSL">Query DSL</form:option>
                      </form:select>
                  </div>
                  <div class="col-6">
                  </div>
              </div>
              <div class="form-group row">
                <label class="col-3 col-form-label form-control-label">Generation Type</label>
                <div class="col-3">
                    <form:select path="primaryKeyGenerationType" class="form-control" >
                         <form:option value="">Select One</form:option>
                         <form:option value="AUTO">AUTO</form:option>
                         <form:option value="ATOMIC">Java Atomic</form:option>
                         <form:option value="SERIES">Series Table</form:option>
                         <form:option value="CACHE">CACHE</form:option>
                    </form:select>
                </div>
                <div class="col-6">                  
                </div>
            </div>            
          <sec:authorize access="hasRole('ADMIN')">
            <div class="form-group row">
            <label class="col-3 col-form-label form-control-label">Authentication Type</label>
            <div class="col-3">
                    <form:select path="authenticationType" class="form-control" >
                         <form:option value="">Select One</form:option>
                         <form:option value="DB">DB</form:option>                         
                    </form:select>
                </div>
                <div class="col-6">                  
                </div>
            </div>
            <div class="form-group row">
                <label class="col-3 col-form-label form-control-label">Encryption Type</label>
                <div class="col-3">
                    <form:select path="authenticationEncrypted" class="form-control" >
                         <form:option value="">Select One</form:option>
                         <form:option value="No">No Encryption</form:option>
                         <form:option value="Yes">Encryption</form:option>                         
                    </form:select>
                </div>
                <div class="col-6">                  
                </div>
            </div>             
           </sec:authorize>
           <sec:authorize access="hasRole('USER')">
             <div class="form-group row">
             	<label class="col-3 col-form-label form-control-label">Authentication Type</label>
             	<div class="col-3">
             	   ${settingsDTO.authenticationType}
             	   <form:hidden path="authenticationType"/>
                </div>
             	<div class="col-6">                  
             	</div>
            </div>
            <div class="form-group row">
              <label class="col-3 col-form-label form-control-label">Encryption Type</label>
              <div class="col-3">
                 ${settingsDTO.authenticationEncrypted ? 'Encrypted' : 'No Encryption'}
                 <form:hidden path="authenticationEncrypted"/>
              </div>
              <div class="col-6">                  
              </div>
            </div>              
           </sec:authorize> 
            
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
                  <input type="submit" id="submit" class="btn btn-info" value="Save Changes">
              </div>
	        </div>
	        <div class="form-group row">
              <label class="col-3 col-form-label form-control-label"></label>
              <div class="col-9 alert">
                   ${settingsDTO.message}                 
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
	
  $('#back').on('click', function (e) {
 	 url = $(this).data("id"); 
 	 window.location.href = "/"+url;
  }); 

  $('#submit').on('click', function (e) {
 	//alert("going to submit");
 	//document.forms[0].submit();
 	//$("#settingsForm").submit();
  }); 

  (function() {
    'use strict';
    window.addEventListener('load', function() {
      // Get the forms we want to add validation styles to
      var forms = document.getElementsByClassName('needs-validation');
      // Loop over them and prevent submission
      var validation = Array.prototype.filter.call(forms, function(form) {
        form.addEventListener('submit', function(event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }
          form.classList.add('was-validated');
        }, false);
      });
    }, false);
  })();
   
  </script>
  
</body>

</html>
