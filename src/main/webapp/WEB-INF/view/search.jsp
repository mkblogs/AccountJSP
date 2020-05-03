<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Account</title>
  <!-- Bootstrap core CSS -->
  <link rel="stylesheet" href="webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>
  <link rel="stylesheet" href="webjars/datatables/1.10.20/css/jquery.dataTables.min.css"/>
  <link rel="stylesheet" href="/css/style.css"/>
</head>

<body>
  <!-- Navigation -->
   <jsp:include page="menu.jsp"/>
   

  <!-- Page Content -->
  <div class="container">
    <div class="card card-outline-secondary">
      <div class="card-header">
        <h3 class="mb-0">Account Search</h3>
       </div>
       <div class="card-body">
           <form:form class="form" role="form" modelAttribute="accountDTO" action="search" method="post" autocomplete="off">
             <div class="form-group row">
                  <div class="col-3">
                      <label class="col-form-label form-control-label">Account Name</label>
                      <form:input class="form-control" path="name"/>
                  </div>
                  <div class="col-3">
                      <label class="col-form-label form-control-label">Account Type</label>
                      <form:select path="type" class="form-control" >
	                     <form:option value="">Select One</form:option>
	                     <form:option value="Savings">Savings</form:option>
	                     <form:option value="Current">Current</form:option>                   
                      </form:select>
                  </div>
                  <div class="col-3">
                        <label class="col-form-label form-control-label">Amount</label>
                         <form:input id="amount" path="amount" class="form-control" />
                  </div>
                  <div class="col-3">
                        <label class="col-form-label form-control-label" for="test">&nbsp;</label>
                        <input type="submit" id="search" class="btn btn-info btn-sm mt-auto" value="Search">
                        <input type="reset" id="reset" class="btn btn-info btn-sm mt-auto" value="Reset">
                  </div>
              </div>
              </form:form>
          </div>
       </div>
       <hr/>
       <div class="row">
         <div class="col-12">
           <table id="example" class="display" style="width:100%">
             <thead>
                 <tr>
                     <th>ID</th>
                     <th>Name</th>
                     <th>Type</th>
                     <th>Amount</th>
                     <th>Created Name</th>
                     <th>Created Date</th>
                     <th>Modified Name</th>
                     <th>Modified Date</th>
                     <th>Version</th>
                     <th>Actions</th>
                 </tr>
             </thead>
             <tbody>
                 <c:forEach  items="${accounts}" var ="account">
	                <tr>
	                   <td>${account.accountId} </td>
	                   <td>${account.name} </td>
	                   <td>${account.type} </td>
	                   <td>${account.amount} </td>
	                   <td>${account.createdName} </td>
	                   <td>${account.createdTs} </td>
	                   <td>${account.lastModifiedName} </td>
	                   <td>${account.lastModifiedTs} </td>
	                   <td>${account.version} </td>
					   <td>Hello</td>
				   </tr>
                 </c:forEach>
                
             </tbody>
          </table>
       </div>
    </div>
    <div id="overlay" style="display:none;">
       <div class="spinner"></div>
       <br/>Loading...
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
  aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-body">
        <label> Are you sure to delete the record ?</label>
        <input type="hidden" name="deletedId" id="deletedId" value=""/>
      </div>
      <div class="modal-footer">
        <button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
        <button type="button" id="deleteModelRecord" class="btn btn-info">Delete</button>
      </div>
    </div>
  </div>
</div>
<!--Success Modal -->
<div class="modal fade" id="successDeleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
  aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-body">
        <label> Successfully Deleted Record<br/> It will redirect to the  search page</label>
      </div>
      <div class="modal-footer">
        <button type="button" id="SuccessReload" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<!--Failure Modal -->
<div class="modal fade" id="failureDeleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
  aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-body">
        <label> Failed to Delete Record<br/> It will redirect to the  search page</label>
      </div>
      <div class="modal-footer">
        <button type="button" id="FailureReload" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
  <!-- Bootstrap core JavaScript -->
  <script type="text/javascript" src="webjars/jquery/3.4.1/jquery.min.js"></script>
  <script type="text/javascript" src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="webjars/datatables/1.10.20/js/jquery.dataTables.min.js"></script>
  
   <script type="text/javascript">

   $("#amount").attr({
	    "type" : "number",
	});
   
   $('#example').DataTable({
       "aaSorting": [],        
       "stripeClasses": [ 'firstrow', 'secondrow' ],
       "columns" : [
    	   {"data" : "accountId"},
           {"data" : "accountName"},
           {"data" : "accountType"},
           {"data" : "amount"},
           {"data" : "createdName"},
           {"data" : "createdTs"},
           {"data" : "lastModifiedName"},
           {"data" : "lastModifiedTs"},
           {"data" : "version"},
           {
              data: null,
              className: "center",
              fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {            	 
                dataHTML =  "<a class='editLink' href='/edit?id="+oData.accountId+"'>EDIT</a>" +
                            "/ <a class='editLink' href='/view?id="+oData.accountId+"'>VIEW</a> " +
                            "/ <a class='editLink' href='javascript:deletefn("+oData.accountId+")'>DELETE</a> ";
                  $(nTd).html(dataHTML);
                },
          }
         ],
    });
    function deletefn (value) {
      $("#deletedId").val(value);
      $('#deleteModal').modal('show');
    }

    $('#deleteModelRecord').on('click', function (e) {    	
       $('#deleteModal').modal('hide');
       accountId = $("#deletedId").val();
       callAjaxToDeleteAccount(accountId);
    });
    
    function callAjaxToDeleteAccount(accountId) {
    	$('#overlay').fadeIn();
    	 $.ajax({
             type: "DELETE",             
             url: "/delete?id="+accountId,             
             cache: false,
             timeout: 600000,
             success: function (data) {
            	 $('#overlay').fadeOut();          	              
                 $('#successDeleteModal').modal('show');
             },
             error: function (e) {
                 $('#overlay').fadeOut();
                 $('#failureDeleteModal').modal('show');
             }
         });
     }
    
    $('#FailureReload').on('click', function (e) {
    	 window.location.href = '/search';
    });
    
    $('#SuccessReload').on('click', function (e) {
   	 	 window.location.href = '/search';
   });
    
   </script>
</body>

</html>
