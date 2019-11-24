<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<form class="form-horizontal">
<fieldset>

<!-- Form Name -->
<legend>New Affiliate Signup</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="fname">First Name</label>  
  <div class="col-md-5">
  <input id="fname" name="fname" type="text" placeholder="First Name" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="lname">Last Name</label>  
  <div class="col-md-5">
  <input id="lname" name="lname" type="text" placeholder="Last Name" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="email">Email</label>  
  <div class="col-md-5">
  <input id="email" name="email" type="text" placeholder="Email" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="addr1">Address Line 1</label>  
  <div class="col-md-5">
  <input id="addr1" name="addr1" type="text" placeholder="Address Line 1" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="addr2">Address Line 2</label>  
  <div class="col-md-5">
  <input id="addr2" name="addr2" type="text" placeholder="Address Line 2" class="form-control input-md">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="city">City</label>  
  <div class="col-md-4">
  <input id="city" name="city" type="text" placeholder="City" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Select Basic -->
<div class="form-group">
  <label class="col-md-4 control-label" for="state">State</label>
  <div class="col-md-4">
    <select id="state" name="state" class="form-control">
      <option value="1">Option one</option>
      <option value="2">Option two</option>
    </select>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="zip">Zip Code</label>  
  <div class="col-md-4">
  <input id="zip" name="zip" type="text" placeholder="5 Digit Zip Code" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="paypal">PayPal Email</label>  
  <div class="col-md-5">
  <input id="paypal" name="paypal" type="text" placeholder="PayPal Email Address" class="form-control input-md">
  <span class="help-block">If you would like to be paid commissions via PayPal, enter your PayPal email address. Your PayPal account must be verified!</span>  
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="check">Check</label>  
  <div class="col-md-5">
  <input id="check" name="check" type="text" placeholder="Check" class="form-control input-md">
  <span class="help-block">If you would prefer to be paid by check, please enter the name you wish to appear on the check. This should be the affiliate account holders name or business name.</span>  
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="ssnein">SSN or EIN</label>  
  <div class="col-md-5">
  <input id="ssnein" name="ssnein" type="text" placeholder="SSN or EIN" class="form-control input-md" required="">
  <span class="help-block">We are required by law to 1099 every affiliate that generates over $600 per year in commissions. Please enter the tax payers SSN or EIN number.<br><br>You will also need to fill out a W-9 IRS form. You can download a copy here: <a href="http://www.irs.gov/pub/irs-pdf/fw9.pdf" target="_blank">W-9 Form</a>. You will need to fax back a completed and signed copy to 555-1212-1212 or email a completed and signed copy to test@test.com</span>  
  </div>
</div>

<!-- Textarea -->
<div class="form-group">
  <label class="col-md-4 control-label" for="notes">How do you intend to promote our product(s)?</label>
  <div class="col-md-4">                     
    <textarea class="form-control" id="notes" name="notes">Please let us know how you intend to promote I Get Fixed?</textarea>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="passwd">Password</label>  
  <div class="col-md-5">
  <input id="passwd" name="passwd" type="text" placeholder="Password" class="form-control input-md" required="">
  <span class="help-block">You will need your email address and the password you create here, to login to the affiliate area.</span>  
  </div>
</div>

<!-- Multiple Checkboxes (inline) -->
<div class="form-group">
  <label class="col-md-4 control-label" for="agree">Do You Agree?</label>
  <div class="col-md-4">
    <label class="checkbox-inline" for="agree-0">
      <input type="checkbox" name="agree" id="agree-0" value="1">
      Yes (You must agree to our terms of service which you can view by <a href="#" target="_blank">clicking here</a>
    </label>
  </div>
</div>

<div class="form-group">
<label class="col-md-4 control-label" for="submit"></label>
  <div class="col-md-4">
    <label class="checkbox-inline" for="submit">
      <input class="btn btn-success" type="submit" name="submit" id="submit" value="Create New Affiliate Account">
    </label>
  </div>
</div>

</fieldset>
</form>
