<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.bogdans.textanalizer.model.FileUploadResultModel" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload a file</title>
</head>
<body>
  <jsp:include page="header.jsp" />
  
  <div id="title">
    <h1>Upload a file</h1>
  </div>
  
  <div id="content">
    <form action="/TextAnalizer/index" method="POST" enctype="multipart/form-data">
      <%
          FileUploadResultModel result = (FileUploadResultModel) request.getAttribute("result");
          if (result != null && result.getMessage() != null) {
        	  if (result.isSuccess()) {
        		  out.println("<div style=\"color:green\">" + result.getMessage() + "</div>");
        	  } else {
        		  out.println("<div style=\"color:red\">" + result.getMessage() + "</div>");
        	  }
          }
      %>
      <label>Select file: </label>
      <input type="file" name="file">
      <br>
      <input type="submit" value="Load file"> 
    </form>
  </div>
  
  <jsp:include page="footer.jsp" />
</body>
</html>