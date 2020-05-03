<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Account</title>
  <!-- Bootstrap core CSS -->
  <link rel="stylesheet" href="webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>
  <link rel="stylesheet" href="/css/signin.css"/>
</head>

<body>
  <!-- Page Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-12 text-center">
        <form id="myForm" action="login" class="form-signin needs-validation" 
         	method="post" autocomplete="off" novalidate="novalidate">
		   <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
		  <c:if test="${param.error != null}">
    		<p style='color:red'>
    			Invalid username and password.
    		</p>
    	</c:if>
    	<c:if test="${param.logout != null}">
    		<p style='color:blue'>
    			You have been logged out.
    		</p>
    	</c:if>
		  
		  <input type="text" id="username" name="username" class="form-control" placeholder="Login"  required="required" autofocus="autofocus"/>
		  <input type="password" id="password" name="password" class="form-control" placeholder="Password" required="required" />
		  <div class="checkbox mb-3">
			<label>
			  <input type="checkbox" value="remember-me"> Remember me
			</label>
		  </div>
		  <button class="btn btn-lg btn-info btn-block" type="submit">Sign in</button>
		  <p class="mt-5 mb-3 text-muted">&copy; 2020-2021</p>
		</form>
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript -->
  <script type="text/javascript" src="webjars/jquery/3.4.1/jquery.min.js"></script>
  <script type="text/javascript" src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  
  <script type="text/javascript">

//Disable form submissions if there are invalid fields
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
