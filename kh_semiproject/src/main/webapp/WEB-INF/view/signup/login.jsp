<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인</title>
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
        text-align: center;
    }
    form{
        position: absolute;
        font-size: 25px;
        height: 400px;
        width: 800px;
        left: 100px;
        border: 1px solid black;
    }
    input{
        height: 30px;
        width: 220px;
    }
    th{
        text-align: left;
    }
    table{
        position: absolute;
        top: 50px;
        left: 200px;
    }
    .IdInfo{
        position:fixed;
        left: 670px;
        top: 210px;
    }
    .PassInfo{
        position:fixed;
        left: 670px;
        top: 250px;
    }
    button{
        position: absolute;
        bottom: 220px;
        left: 306px;
        font-weight: bold;
        font-size: 20px;
        height: 40px;
        width: 80px;
        background-color: gray;
        color: black;
        border: none;
        font-size: 20px;
    }
    a{
        position: absolute;
        bottom: 290px;
        right: 365px;	
        width: 110px;
        height: 40px;
        text-align: center;
        line-height: 40px;
        background-color: gray;
        color: black;
        font-weight: bold;
        text-decoration-line: none;
        font-size: 21px;
    }
    </style>
</head>
<body>
    <body>
	 <c:if test="${signupSuccessFail==2}">
		<script>
        alert('회원가입성공 로그인하세요.');
		</script>
	</c:if>
   	<c:if test="${loginSucessFail==0}">
		<script>
        alert('아이디가 존재하지 않습니다.');
		</script>
	</c:if>
	<c:if test="${loginSucessFail==1}">
		<script>
        alert('비밀번호가 틀렸습니다.');
		</script>
	</c:if>
        <div class="title">
            <p>커뮤니티 사이트</p>
        </div>
        <div class="IdInfo"><p>영어,숫자만 가능</p></div>
        <div class="PassInfo"><p>10자이상 대소문자,숫자포함</p></div>
        <div class="login">
            <h2>로그인</h2>
            <form action="<%=request.getContextPath()%>/login" method="post">  
                <table>
                    <tr>
                        <th>아이디</th>    
                        <td><input type="text" name="id" required></td>
                    </tr>
                    <tr>
                        <th>비밀번호</th>
                        <td><input type="password" name="password" required></td>
                    </tr>
                </table>
                <button type="submit">확인</button>
            </form>
            <a href="<%=request.getContextPath()%>/signup">회원가입</a> 
        </div>
        <div class="footer">
            <p>copyright (c) 백의헌 게시판만들기</p>
        </div>
</body>
</html>