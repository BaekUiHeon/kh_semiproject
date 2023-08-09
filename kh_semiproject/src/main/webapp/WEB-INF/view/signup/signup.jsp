<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page errorPage="/WEB-INF/view/error/errorForm.jsp"--%>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!DOCTYPE html>
<html>	
<head>
<meta charset="EUC-KR">
<title>회원가입</title>
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
    .signup{
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
        left: 705px;
        top: 180px;
    }
    .PassInfo{
        position:fixed;
        left: 705px;
        top: 220px;
    }
    button{
        position: absolute;
        bottom: 20px;
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
        bottom: 90px;
        right: 150px;
        width: 80px;
        height: 40px;
        text-align: center;
        line-height: 40px;
        background-color: gray;
        color: black;
        font-weight: bold;
        text-decoration-line: none;
        font-size: 21px;
    }
    #check_id{
    width: 70px;
    }
    </style>
</head>
<body>
	<c:if test="${passwordError==1}"> <%// 회원가입 실패에 따른 출력문들 %>
	<script>
        alert('비밀번호가 다릅니다');
	</script>
	</c:if>
	<c:if test="${signupSuccessFail==1}">
	<script>
        alert('회원가입 실패');
	</script>
	</c:if>
	
    <div class="title">
        <p>커뮤니티 사이트</p>
    </div>
    <div class="IdInfo"><p>영어,숫자만 가능</p></div>
    <div class="PassInfo"><p>10자이상 대소문자,숫자포함</p></div>
    <div class="signup">
        <h2>회원가입</h2>
        <form action="<%=request.getContextPath()%>/signup" method="post"> <!--주소 입력해주어야함-->
            <table>
                <tr>
                    <th>아이디</th>    
                    <td><input type="text"  name="id" pattern="[A-Za-z0-9]+" required></td> 
                    <td class="check_id"><input type="button" id="check_id" value="중복검사"></td> 
                </tr>
                <tr>
                    <th>비밀번호</th>
                    <td><input type="password"  name="password1" pattern="^(?=.*[a-z])(?=.*[A-Z]).{10,}$" required></td>
                </tr>
                <tr>
                    <th>비밀번호확인</th>
                    <td><input type="password"  name="password2" pattern="^(?=.*[a-z])(?=.*[A-Z]).{10,}$" required></td>
                </tr>
                <tr>
                    <th>닉네임</th>
                    <td><input type="text"  name="writer"></td>
                </tr>
                <tr>
                    <th>이메일</th>
                    <td><input type="text"  name="emailAddress" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}"></td>
                </tr>
            </table>
            <button type="submit">확인</button>
        </form>
    </div>
    <div><a href="<%=request.getContextPath()%>/login">로그인</a></div>
    <div class="footer">
        <p>copyright (c) 백의헌 게시판만들기</p>
    </div>
    <script>
        	$("#check_id").click(check_id);
        	function check_id(){
        		var idValue = $("input[name='id']").val();
        		$.ajax({
        			url:"${pageContext.request.contextPath}/checkid",
        			type:'get',
        			data: {id:idValue},
        			success: resultIdCheck
        		});
        	}
        	function resultIdCheck(result){
        		if(result==1){
        			alert("중복된 아이디입니다.");
        		}
        		else if(result==0){
        			alert("사용 가능한 아이디입니다.");
        		}
        		else {
        			alert("체크실패");
        		}
        	}
        </script>
</body>
</html>