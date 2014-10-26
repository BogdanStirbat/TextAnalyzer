<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Imported files</title>
</head>
<body>
  <jsp:include page="header.jsp" />
  
  <div id="title">
    <h1>Imported files</h1>
  </div>
  
  <div id="content">
    <%
      List<String> importedFiles = (List<String>) request.getAttribute("importedFiles");
      if (importedFiles == null || importedFiles.size() == 0) {
    %>
    	  <p>No data found.</p>
    <%
      } else {
    %>
        <form action="/TextAnalizer/files" method="POST" enctype="multipart/form-data" id="deleteForm">
    <%
           for(String fileName : importedFiles) {
    %>
            <label>
    <%
        	   out.print(fileName);
    %>
             </label><input type="button" value="Delete" onclick="deleteFile('<%out.print(fileName);%>')"> <br>
    <%
           }
    %>
        <input type="hidden" name="input_file" id="input_file" value="">
        </form>
    <%
      }
    %>
  </div>
  
  <script type="text/javascript">
     function deleteFile(fileName){
    	 var inputFile = document.getElementById("input_file");
    	 inputFile.value = fileName;
    	 var form = document.getElementById("deleteForm");
    	 form.submit();
     }
  </script>
  
  <jsp:include page="footer.jsp" />
</body>
</html>