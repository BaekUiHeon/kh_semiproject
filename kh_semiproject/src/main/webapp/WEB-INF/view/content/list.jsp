<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
    <title>게시물 목록</title>
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
    .list{
        width: 800px;
        height: 400px;
        border: 1px solid black;
        margin: 5px auto;
    }
    .list table{
        position: relative;
        bottom: 40px;
        border-spacing: 0;
        border-collapse: collapse;
        margin: 5px auto;
    }
    .list table th{
        font-size: 20px;
        padding: 0;
        box-sizing:content-box;
        border: 1px solid black;
        text-align: center;
    }
    .list table td{
        font-size:20px;
        padding: 0;
        margin: 0;
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
    .col1{
        width: 120px;
    }
    .col2{
        width: 120px;
    }
    .col3{
        width: 320px;
    }
    .col4{
        width: 220px;
    }
    .notice{
        position: relative;
        left: 10px;
    }
    .write{
        position: absolute;
        bottom: 150px;
        right: 120px;
        width: 80px;
        height: 40px;
        text-align: center;
        line-height: 40px;
        background-color: gray;
        font-weight: bold;   
    }
    .write a{
    	 color: black;
    	 text-decoration: none;
    }
    td{
    font-size: 20px; 
    padding: 0;
    box-sizing: content-box;
    border: 1px solid black;
    text-align: center;
    }
    .search{
        position:relative;
        left: 570px;
        bottom: 40px;
    }
    .paging{
    	position:absolute;
    	bottom:80px;
    	left:400px;
    }
</style>
</head>
<body>
		<c:if test="${nullError==1}">
		<script>
        alert('해당하는 게시물이 없습니다');
		</script>
		</c:if>
		<c:if test="${success==1}">
		<script>
        alert('로그인성공');
		</script>
		</c:if>
		<c:if test="${writeSucessFail==1}">
		<script>
        alert('게시물작성이 완료되었습니다.');
		</script>
		</c:if>
        <div class="title">
            <p>커뮤니티 사이트</p>
        </div>
        <div class="list">
        <div class="notice"><p>총 개의 게시물이 있습니다</p></div>
        <span class="search">
            <form action ="<%=request.getContextPath()%>/list" method="get">
                <input type="search" name="searchWord">
                <input type="submit" value="찾기">
            </form>
        </span>
            <table>
                <tr>
                	<th class="col1">번호</th>
                    <th class="col2">작성자</th>
                    <th class="col3">제목</th>
                    <th class="col4">작성일</th>   
                </tr>
                <c:forEach items="${list}" var="item">
               <tr> 
                    <td><a href="<%=request.getContextPath()%>/board?idx=${item.idx}">${item.idx}</a></td>
                    <td>${item.writer}</td>
                    <td>${item.subject}</td>
                    <td>${item.wdate}</td>   
                </tr>
                </c:forEach>
    		</table>
               <div class="paging">
                <c:if test="${startPageNum!=1}">  <%--페이징 이전,번호,다음에 대한 코드 --%>
                	<a href="<%=request.getContextPath()%>/list?currentPageNum=${startPageNum-1}&searchWord=${searchWord}">이전</a>
                </c:if>
                <c:choose>
                <c:when test="${not empty searchWord }">
                <c:forEach begin="${startPageNum}" end="${endPageNum}" var="i"> 
                <a href="<%=request.getContextPath()%>/list?currentPage=${i}&searchWord=${searchWord}"><span>${i} </span></a> <%--searchWord검사필요--%>
                </c:forEach>
                </c:when>
                <c:otherwise>
                <c:forEach begin="${startPageNum}" end="${endPageNum}" var="i"> 
                <a href="<%=request.getContextPath()%>/list?currentPage=${i}"><span>${i} </span></a> <%--searchWord검사필요--%>
                </c:forEach>
                </c:otherwise>
                </c:choose>
                <c:if test="${endPageNum<totalPageNum}">
                	<a href="<%=request.getContextPath()%>/list?currentPageNum=${endPageNum+1}&searchWord=${searchWord}">다음</a>
                </c:if>
           		</div>
            <div class="write"><a href="<%=request.getContextPath()%>/write">작성</a></div>
        </div>
        <div class="footer">
            <p>copyright (c) 백의헌 게시판만들기</p>
        </div>
</body>
</html>