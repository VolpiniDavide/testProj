<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/logStyle.css">
<link rel="stylesheet" type="text/css" href="CSS/primocss.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="video_banner">
		<div class="videoLoop" id="videoContainer">
			<video id="background_video" loop="" muted="" autoplay="">
			
				<source src="http://www.onyxtechnology.it/wp-content/uploads/2017/05/video-vele.mp4" type="video/mp4">
			
			</video>
			
			<div id="overlay" style="z-index: 100;">

				<div class="reg">
				
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url var="url" value="/registration" />
				<form:form action= "${url}" method="post" modelAttribute="userForm">
					<form:input type = "email" path ="email" placeholder ="email" /><br>
					<form:input type = "password" path ="password" placeholder="password"/><br>
					<form:input type = "number" path ="number" placeholder="phone number"/><br>
					<form:input type = "text" path ="firstName" placeholder="first name"/><br>
					<form:input type = "text" path ="lastName" placeholder="last name"/><br>
					<input type="submit" value="invia">
					</form:form> <br><br>
					<p style ="text-align: center;"> ${Message} </p>
					</div> 
					
					</div>
		</div>

		<div class="content">
			<div class="banner-heading svg">
				             
					</div>
	</div>
	</div>

</body>
</html>