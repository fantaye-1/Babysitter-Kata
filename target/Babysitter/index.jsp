<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>kata</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <a class="navbar-brand" href="#">kata</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarNav">
	    <ul class="navbar-nav">
	      <li class="nav-item active">
	        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">Features</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">Features</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
	      </li>
	    </ul>
	  </div>
	</nav>
	
	<div class="container-fluid">
	  <h1>Welcome to kara page</h1>  <br> 
	   
	   <p>This kata simulates a babysitter working and getting paid for one night based on the rule given.</p>
	   
	   <form action="KaraServlet" method="post">	       
		  <div class="form-row">	  
		    <div class="form-group col-md-3">
		      <label for="inputStartTime">Start Time</label>
		      <input type="text" class="form-control" id="inputStartTime" name="inputStartTime">
		    </div>
		    <div class="form-group col-md-1">
		      <label for="inputStartAmPm">AM/PM </label> 
		      <select id="inputStartAmPm" class="form-control" name="inputStartAmPm">
		        <option selected>AM</option>
		        <option>PM</option>
		      </select>
		    </div>  
		      
		  </div>
		  
		  <div class="form-row">	  
		    <div class="form-group col-md-3">
		      <label for="inputBedtime">Bedtime Time</label>
		      <input type="text" class="form-control" id="inputBedtime" name="inputBedtime">
		    </div>
		    <div class="form-group col-md-1">
		      <label for="inputBedtimeAmPm">AM/PM </label> 
		      <select id="inputBedtimeAmPm" class="form-control" name="inputBedtimeAmPm">
		        <option selected>AM</option>
		        <option>PM</option>
		      </select>
		    </div>  
		      
		  </div>
		  <div class="form-row">	  
		    <div class="form-group col-md-3">
		      <label for="inputEndTime">End Time</label>
		      <input type="text" class="form-control" id="inputEndTime" name="inputEndTime">
		    </div>
		    <div class="form-group col-md-1">
		      <label for="inputEndAmPm">AM/PM </label> 
		      <select id="inputEndAmPm" class="form-control" name="inputEndAmPm">
		        <option selected>AM</option>
		        <option>PM</option>
		      </select>
		    </div>  
		      
		  </div>
		 
		  <button type="submit" class="btn btn-primary">Calculate</button>
	</form>
	   
	  <!-- <p> you can also login </p>
	   <button type="button" class="btn btn-primary btn-lg">Login/logout</button>
		 <form action="KaraServlet" method="post">
		
		     Enter your name: <input type="text" name="yourName" size="20">
		    <input type="submit" value="Call Servlet" />
		    
	    </form> -->
	</div>
	   
</body>
</html>