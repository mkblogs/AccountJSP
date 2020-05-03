<!DOCTYPE html>
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
         <div class="form-group row">
              <label class="col-3 col-form-label form-control-label">Generated Account</label>
              <div class="col-3">
                   <div>${accountDTO.accountId}</div>
              </div>
          </div>
       </div>
    </div>
  </div>


<!-- Bootstrap core JavaScript -->
   <script type="text/javascript" src="webjars/jquery/3.4.1/jquery.min.js"></script>
   <script type="text/javascript" src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>

</html>
