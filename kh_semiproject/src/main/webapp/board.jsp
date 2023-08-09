<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시글목록</title>
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
    <style>
    body{
        position: relative;
        width: 1000px;
        min-height: 600px;
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
    .content{
        width: 800px;
        min-height: 200px;
        border: 1px solid black;
        margin: 5px auto;
    }
    p{
        display: inline-block;
    }
    .comment{
        position: relative;
        left: 100px;
    }
    input[type=submit]{
        height: 27px;
        font-weight: bold;
        color: black;
    }
    .rootcomment{
        width: 715px;
        height: 20px;
    }
    nav{
        position: absolute;
        bottom: 60px;
        right: 100px;
    }
    nav a{
        display: inline-block;
        background-color: gray;
        width: 80px;
        height: 40px;
        text-align: center;
        line-height: 40px;
        font-weight: bold;
        font-size: 20px;
        color: black;
        text-decoration: none;
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
    .content1{
    	width: 600px;
    }
    .like{
        display: inline-block;
        position: absolute;
        right: 200px;
        top: 100px;
        text-align: center;
        line-height: 40px;
        font-weight: bold;
        width: 120px;
        height: 40px;
        font-size: 20px;
        background-color: gray;
        color: black;
        text-decoration: none;
    }
    </style>
</head>
<body>
    <body>
        <div class="title">
            <p>커뮤니티 사이트</p>
        </div>
        <div class="content">
        	<input type="button" value="좋아요:${countLike}" class="like">
            <p>제목:${vo.subject}</p>
            <div>
                <p>작성자:${vo.writer} &nbsp&nbsp</p>
                <p>작성일:${vo.wdate}</p>
            </div>
            <div class=content1>
                <p>내용:${vo.content}</p>
            </div>
            
	        <div>
                <p>댓글</p>
            </div>
            <table>
            <c:forEach items="${commentlist}" var="item">
                <tr>
                	<td>
            		<c:forEach begin="1" end="${item.depth - 1}" varStatus="loop">
            		&nbsp&nbsp
        			</c:forEach>
                    ${item.writer}: ${item.content } &nbsp&nbsp&nbsp  ${item.wdate }</td>
                    <c:if test="${mid!=item.id}">
                    <td><input type="button" class="writecomment" value="댓글달기">
                    <input type="hidden" name="cidx" value="${item.cidx}">
                    <input type="hidden" name="idx" value="${item.idx}">
                    <input type="hidden" name="step" value="${item.step}">
                    <input type="hidden" name="depth" value="${item.depth}">
                    </td>
                    </c:if>
                    <c:if test="${mid==item.id}">
                    <td><input type="button" class="deletecomment" value="삭제"><input type="hidden" name="cidx" value="${item.cidx}"></td><%--삭제의경우 삭제처리후 다시뜨게하기보다 성공여부에 따라 여기만 삭제--%>
                    </c:if>
                </tr>
                </c:forEach>
            </table> 
        </div>
        <c:if test="${mid!=vo.id}">
        <div class="comment">
            <p>댓글작성</p>
            <input type="text" id="comment" class="rootcomment">
            <input type="button" value="작성완료">
        </div>
        </c:if>
        <nav>
            <a href="<%=request.getContextPath()%>/list">목록</a> 
            <c:if test="${mid == vo.id}"> 
            <a href="<%=request.getContextPath()%>/write?idx=${vo.idx}&content=${vo.content}&subject=${vo.subject}">수정</a>
            </c:if>
            <a href="<%=request.getContextPath()%>/write">작성</a>
        </nav>
        <div class="footer">
            <p>copyright (c) 백의헌 게시판만들기</p>
        </div>
        <script>
        	$(".like").click(update_like);
        	function update_like(){
        	$.ajax({
        		url: "${pageContext.request.getContextPath()}/getlike",
        		data:{idx:"${vo.idx}",mid:"${mid}"},
        		type:"get",
        		success:getlike
        	});
        	}
        	function getlike(result) {
        	    $(".like").val("좋아요:" + result);
        	}
        	
          	$(document).on("click",".comment input[type=button]", insertcomment);
        	function insertcomment(){
          		console.log("insertcomment");
        		var comment= $("#comment").val();
        		$.ajax({
        			url:"${pageContext.request.contextPath}/insertcomment",
        			data:{comment:comment, idx:"${vo.idx}"},
        			type:"get",
        			dataType:"Json",
        			success:getcomment
        		}
        		);
        	}
          	
          	function getcomment(data) {
          	    var commentlist = data.commentlist;
          	    var table = $("<table></table>"); 
          	    $.each(commentlist, function(index, item) {
          	        var row = $("<tr></tr>");
          	        var nb="";
          	        for (var i = 0; i < item.depth; i++) {
          	            nb+="&nbsp;&nbsp;";
          	            } 
          	      var td= $("<td>"+nb+ item.writer+ ": " + item.content + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + item.wdate+"</td>")
          	        td.appendTo(row);
          	        if ("${mid}" != item.id) {
          	            $("<td><input type='button' value='댓글달기' class='writecomment'><input type='hidden' name='cidx'  value='"+item.cidx+"'></td><input type='hidden' name='idx'  value='"+item.idx+"'></td><input type='hidden' name='step'  value='"+item.step+"'></td><input type='hidden' name='depth'  value='"+item.depth+"'></td>").appendTo(row);
          	        } else {
          	            $("<td><input type='button' value='삭제' class='deletecomment'><input type='hidden' name='cidx' value='"+item.cidx+"'></td>").appendTo(row);
          	        }
          	        row.appendTo(table);
          	    });
          	
          	    $("table").replaceWith(table);
          	}   	 
          	
          	$(document).on("click",".writecomment", writeboard);
            function writeboard(){
            	var html = $("<td><input type='text' class='comment_comment'></td>"+"<td><input type='button' class='writeboard' value='작성'></td>");
            	$(".writeboard").parent().remove();
            	$(".comment_comment").parent().remove();
            	html.appendTo($(this).parent().parent());
            }
            
            $(document).on("click", ".writeboard", write_comment_comment);
	       
            function write_comment_comment() {
                console.log("write_comment_comment 실행됨.");
                console.log($(this).parent().prev().children().val());
                $.ajax({
                    url: "${pageContext.request.contextPath}/insertcomment",
                    data: {
                        cidx: $(this).parent().prev().prev().children("input[name=cidx]").val(),
                        idx: $(this).parent().prev().prev().children("input[name=idx]").val(),
                        step: $(this).parent().prev().prev().children("input[name=step]").val(),
                        depth: $(this).parent().prev().prev().children("input[name=depth]").val(),
                        comment: $(this).parent().prev().children().val()
                    },
                    type: "get",
                    dataType: "json",
                    success: getcomment
                });
            }
       		
            $(document).on("click", ".deletecomment",deletecomment);
      		
       		function deletecomment(){
       			var $location= $(this).parent().parent();
      			var cidx = $(this).closest('td').find('input[type="hidden"]').val();
       			$.ajax({
       				url:"${pageContext.request.contextPath}/deletecomment",
       				data:{cidx:cidx},	  
       				type:'get',
       				success: function(data) {
       		            dodeletecomment(data, $location); 
       		        }
       		    });
       		}
      		
       		function dodeletecomment(data, $row) {
       		    if (data == 1) { 
       		        $row.remove(); 
       		    } else {
       		        alert("삭제 실패");
       		    }
       		}
      </script>
</body>
</html>