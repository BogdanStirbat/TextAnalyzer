<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.bogdans.textanalizer.model.AnalyzeFilenameOccurence" %>
<%@ page import="com.bogdans.textanalizer.model.AnalyzeResult" %>
<%@ page import="java.util.List" %>
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
    <br>
    
     <%
       AnalyzeResult result = (AnalyzeResult) request.getAttribute("result");
       if (result != null) {
    	   out.println("<div style=\"color:green\"> Term:" + result.getInputTerm() + "</div>");
         List<AnalyzeFilenameOccurence> occurences = result.getOccurences();
         if (occurences != null && occurences.size() > 0) {
        	 out.print("<table border=\"1\">");
           out.print("<tr>");
           out.print("<td>File name</td>");
           out.print("<td>Number of occurrences</td>");
           out.print("</tr>");
        	 for (AnalyzeFilenameOccurence analyzeResult : occurences) {
        		 out.print("<tr>");
        		 out.print("<td>" + analyzeResult.getFileName() + "</td>");
        		 out.print("<td>" + analyzeResult.getNumberOfOccurences() + "</td>");
        		 out.print("</tr>");
        	 }
        	 out.print("</table>");
         } else {
        	 out.print("<p>No data found.</p>");
         }
       }
     %>
  </div>
  
  <jsp:include page="footer.jsp" />
</body>
</html>