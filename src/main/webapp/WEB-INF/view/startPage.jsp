<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<div class="mainn">
        <div class="main col-xs-10 col-sm-7 col-md-4 col-lg-4">

          <div>
            <ul class="nav nav-tabs">
              <li class="active"><a href="#login" data-toggle="tab">Login</a></li>
              <li><a href="#signup" data-toggle="tab">Sign Up</a></li>
              <li><a href="#forgotpassword" data-toggle="tab">Forgot password</a></li>
            </ul>
          </div>

          <div class="tab-content">

            <div class=" tab-pane fade in active" id="login">

                <form class="form-horizontal">
                  <div class="form-group">
                    <label class="control-label col-sm-3" for="email">Email:</label>
                    <div class="col-sm-9 col-md-9 col-lg-9">
                      <input type="email" class="form-control" id="email" placeholder="Enter email">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label col-sm-3" for="pwd">Password:</label>
                    <div class="col-sm-9 col-md-9 col-lg-9">
                      <input type="password" class="form-control" id="pwd" placeholder="Enter password">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-6">
                      <div class="checkbox">
                        <label><input type="checkbox"> Remember me</label>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-6">
                      <button type="submit" class="btn btn-default">Login In</button>
                    </div>
                  </div>
                </form>
            </div>


            <div class="tab-pane fade" id="signup">

              <form class="form-horizontal">
                <div class="form-group">
                  <label class="control-label col-sm-3" for="email">Name:</label>
                  <div class="col-sm-9 col-md-9 col-lg-9">
                    <input type="text" class="form-control" id="name" placeholder="Enter Name">
                  </div>
                </div>
                <form class="form-horizontal">
                  <div class="form-group">
                    <label class="control-label col-sm-3" for="email">Email:</label>
                    <div class="col-sm-9 col-md-9 col-lg-9">
                      <input type="email" class="form-control" id="email" placeholder="Enter email">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label col-sm-3" for="pwd">Password:</label>
                    <div class="col-sm-9 col-md-9 col-lg-9">
                      <input type="password" class="form-control" id="pwd" placeholder="Enter password">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label col-sm-3" for="pwd">Confirm Password:</label>
                    <div class="col-sm-9 col-md-9 col-lg-9">
                      <input type="password" class="form-control" id="pwd" placeholder="Enter password">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-6">
                      <button type="submit" class="btn btn-default">Register</button>
                    </div>
                  </div>
                </form>
              </div>

            <div class="tab-pane fade" id="forgotpassword">

              <form class="" action="" method="post">
                <p>Forgot password? Its okay we got you covered. Enter your Email ID and we'll send you a link to reset your password.</p>
                <div class="form-group">

                  <div class="col-lg-12">
                    <input type="email" class="form-control" id="email" placeholder="Enter Email">
                  </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-12">
                      <button type="submit" class="btn btn-default" style="margin-top:15px;">Reset Password</button>
                    </div>
                  </div>

              </form>

            </div>

          </div>

        </div>
      </div>