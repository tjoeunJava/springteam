<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">    
    <title>Insert title here</title>
  </head>
  <body>
    <script>
      alert("축하합니다 !! 회원으로 가입하셨습니다 !!");
      location.href="${root}user/login";
    </script>
  </body>
</html>