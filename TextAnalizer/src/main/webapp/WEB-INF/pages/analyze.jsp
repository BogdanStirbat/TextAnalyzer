<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Analyze</title>
</head>
<body>
  <jsp:include page="header.jsp" />
  
  <div id="title">
    <h1>Make text analysis</h1>
  </div>
  
  <div id="content">
    <form action="/TextAnalizer/analyze" method="POST" enctype="multipart/form-data">
      <%
        String inputText = (String) request.getAttribute("inputText");
        if (inputText != null && inputText.trim().length() != 0) {
        	out.println("<div style=\"color:green\"> Inseted: " + inputText + "</div>");
        }
      %>
      <label>Insert term: </label>
      <input type="text" name="input_term">
      <br>
      <input type="submit" value="Analyze term"> 
    </form>
  </div>
  
  <jsp:include page="footer.jsp" />
</body>
</html>