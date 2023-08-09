<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
 <title>메인화면</title>
    <style>
    body{
        position: relative;
        width: 1000px;
        height: 600px;
        border: 1px solid black;
    }
    .title{
        font-weight: bold;
        width: 800px;
        height: 50px;
        border: 1px solid black;
        margin: 5px auto;
        text-align: center;
    }
    .footer{
        position: absolute;
        bottom: 0px;
        left: 99px;
        width: 800px;
        height: 50px;
        border: 1px solid black;
        margin: 5px auto;
        text-align: center;
    }
    .login{
        position: absolute;
        bottom: 250px;
        left: 370px;
        width: 80px;
        height: 40px;
        text-align: center;
        line-height: 40px;
        background-color: gray;
    }
    .signup{
        position: absolute;
        bottom: 250px;
        left: 570px;
        width: 80px;
        height: 40px;
        text-align: center;
        line-height: 40px;
        background-color: gray;
    }
    a{
    text-decoration: none;
    }
    .login a{
        font-weight: bold;
        color: black;
    }
    .signup a{
        font-weight: bold;
        color: black;
    }
    .logout input{
        position: absolute;
        font-weight: bold;
        bottom: 250px;
        left: 370px;
        width: 80px;
        font-size: 14px;
        height: 40px;
        text-align: center;
        line-height: 40px;
        background-color: gray;
        border:none;
    }
      .list a{
        position: absolute;
        color:black;
        font-size: 14px;
        font-weight: bold;
        bottom: 250px;
        left: 570px;
        width: 80px;
        height: 40px;
        text-align: center;
        line-height: 40px;
        background-color: gray;
    }
    </style>
</head>
<body>
    <div class="title">
        <p>커뮤니티 사이트</p>
    </div>
    <c:if test="${empty mid}">
    <div class="login">
        <a href="<%=request.getContextPath()%>/login">로그인</a>
    </div>
    <div class="signup">
        <a href="<%=request.getContextPath()%>/signup">회원가입</a>
    </div>
    </c:if>
    <c:if test="${not empty mid}">
    <form action="<%=request.getContextPath()%>/logout" method="post">
    <div class="logout">
        <input type="submit" value="로그아웃">
    </div>
    </form>
    <div class="list">
    	<a href="<%=request.getContextPath()%>/list">게시판으로</a>
    </div>
    </c:if>
    <div class="footer">
        <p>copyright (c) 백의헌 게시판만들기</p>
    </div>
</body>
</html>