<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="x-ua-compatible" content="ie=edge">

  <title></title>

  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/bootstrap/dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">



	<%@ include file="_navbar.jsp" %>

	<%@ include file="_sidebar.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0 text-dark">Starter Page</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Starter Page</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <div class="content">
      <div class="container-fluid">
		<div class="row">
			<div class="col-sm-6">
				<div class="small-box bg-info">
	              <div class="inner">
	                <h3>150</h3>
	
	                <p>New Orders</p>
	              </div>
	              <div class="icon">
	                <i class="ion ion-bag"></i>
	              </div>
	              <a href="#" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
	            </div>
            </div>
			<div class="col-sm-6">
				<div class="small-box bg-warning">
	              <div class="inner">
	                <h3>44</h3>
	
	                <p>User Registrations</p>
	              </div>
	              <div class="icon">
	                <i class="ion ion-person-add"></i>
	              </div>
	              <a href="#" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
	            </div>
			</div>
		</div><!-- .row -->
		<div class="row">
			<div class="col-sm-4">
				<div class="card card-primary">
	              <div class="card-header">
	                <h3 class="card-title">Primary Outline</h3>
	
	                <div class="card-tools">
	                  <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
	                  </button>
	                </div>
	                <!-- /.card-tools -->
	              </div>
	              <!-- /.card-header -->
	              <div class="card-body">
	                The body of the card
	              </div>
	              <!-- /.card-body -->
	            </div>
			</div>
			<div class="col-sm-4">
				<div class="card card-success">
	              <div class="card-header">
	                <h3 class="card-title">Success Outline</h3>
	
	                <div class="card-tools">
	                  <button type="button" class="btn btn-tool" data-card-widget="remove"><i class="fas fa-times"></i>
	                  </button>
	                </div>
	                <!-- /.card-tools -->
	              </div>
	              <!-- /.card-header -->
	              <div class="card-body">
	                The body of the card
	              </div>
	              <!-- /.card-body -->
	            </div>
			</div>
			<div class="col-sm-4">
				<div class="card card-warning">
	              <div class="card-header">
	                <h3 class="card-title">Warning Outline</h3>
	
	                <div class="card-tools">
	                  <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
	                  </button>
	                </div>
	                <!-- /.card-tools -->
	              </div>
	              <!-- /.card-header -->
	              <div class="card-body">
	                The body of the card
	              </div>
	              <!-- /.card-body -->
	            </div>
			</div>
		</div><!-- .row -->
		<div class="row">
			
		</div><!-- .row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

	<%@ include file="_controlSidebar.jsp" %>
	
	<%@ include file="_footer.jsp" %>

</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script src="<%= request.getContextPath() %>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="<%= request.getContextPath() %>/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="<%= request.getContextPath() %>/resources/bootstrap/dist/js/adminlte.min.js"></script>
</body>
</html>
