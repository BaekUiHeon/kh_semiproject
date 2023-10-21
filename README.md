# BUH_BOARD
## 프로젝트 목표 
- 세미 프로젝트는 화려함보다는 과정에서 학습하고 있는 MVC 패턴에 대한 이해와
페이징, 좋아요, 대 댓글 등의 기능을<br> 한 줄 한 줄 이해하면서 구현하는 데 중점을 두었습니다.
<br>


## 프로젝트 소개
- Servlet과 JSP를 활용한 게시판 기능 구현 
- 대댓글, 좋아요, 페이징 기능이 포함된 커뮤니티 게시판
<br>

## 개발환경
|Category|Detail|
|--------|------|
|FrontEnd|HTML5, CSS, JavaScript, JQuery|
|BackEnd|Java(JDK 11), servlet, jsp|
|OS|Windows 10|
|IDE|Eclipse, VSCode, SQL Developer|
|Server|Tomcat 9.0|
|DateBase|Oracle(21c)|
<br>

## 프로젝트 기능 구현

<br>

## 1. 페이징

### 1-1. 페이징이 적용된 커뮤니티 화면 
![image](https://github.com/BaekUiHeon/kh_semiproject/assets/135290607/a26a9629-1d60-4691-81b2-b8de365bbfb1)


### 1-2. 페이징 알고리즘 도식화
![image](https://github.com/BaekUiHeon/kh_semiproject/assets/135290607/c64ef8f4-d369-4a03-b9b7-0c5942ac5c20)


> `Controller`
1. 현재페이지 (default값 1)
2. 페이지당 게시물 수 10 (설계에 따른 고정값)
3. 페이지 번호갯수 5 (설계에 따른 고정값)
4. 현재페이지, 페이지당 게시물 수를 인자로하여 Dao,Service 함수 실행

> `Dao,Service`
1. getTotal 함수로 총 게시물 수 구함.
2. 현재페이지 + 페이지당 게시물 수 + 총 게시물 수를 인자로 하여 데이터베이스로 부터 데이터 가져옴.
3. Controller로 총 게시물 수와 데이터를 반환

> `Controller`
1. 총 게시물 수와, 데이터를 바탕으로 총 페이지번호, 시작 페이지 번호, 끝 페이지 번호를 구함.
2. 페이지 번호들 + 현재 페이지번호 + 게시물리스트를 가지고 JSP로 이동
<br>

### 1-3. 페이징 알고리즘 JSP단
![image](https://github.com/BaekUiHeon/kh_semiproject/assets/135290607/68300b99-75ef-458c-b35f-4da0343710a2)

<br>

## 2. 좋아요

### 2-1. Ajax 좋아요 알고리즘 도식화
![image](https://github.com/BaekUiHeon/kh_semiproject/assets/135290607/775164e2-7271-496c-b9b4-29a67aa71f6d)

> `JSP`<br>
1.JSP에서 좋아요 클릭
  
> `Controller,Service,Dao`
  1. Controller -> Service -> Dao의 경로로 해당아이디와 게시물 번호를 전달
  2. Dao를통해 데이터베이스에 해당 게시글번호와 ID로 `Insert`시도
  3. 오류 발생시 중복된 값이 있다고 판단하고 `Delete`시도
  4. 해당 게시글 좋아요수를 `Count`하여 최신화
<br>

## 3. 대댓글

### 3-1. Ajax로 구현한 대 댓글 적용 모습

![image](https://github.com/BaekUiHeon/kh_semiproject/assets/135290607/833e6b46-b826-4e02-b529-8df420731b75)


![image](https://github.com/BaekUiHeon/kh_semiproject/assets/135290607/8a2d974d-5c40-4652-bd66-dee32301a2e9)

> `comment`
1. 대 댓글 기능은 모두 `Ajax`로 구현하였습니다.<br>
2. 댓글 달기 클릭시 댓글을 작성할수 있는 `input`창이 생성되고 작성버튼을 클릭하면 `Ajax`로 작성한 댓글이 화면에 최신화 됩니다.<br>
삭제또한 `Ajax`를 통해 구현하였습니다.
<br>

>
           //대댓글 구현 코드(html)
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

>        
            //대댓글 작성버튼 클릭시 댓글 입력창 생성 코드
          	$(document).on("click",".writecomment", writeboard);
            function writeboard(){
            	var html = $("<td><input type='text' class='comment_comment'></td>"+"<td><input type='button' class='writeboard' value='작성'></td>");
            	$(".writeboard").parent().remove();
            	$(".comment_comment").parent().remove();
            	html.appendTo($(this).parent().parent());
            }
            
            $(document).on("click", ".writeboard", write_comment_comment);
            
>
            //대댓글 작성 실행코드 
            function write_comment_comment() {
                console.log("write_comment_comment 실행됨.");
                console.log($(this).parent().prev().children().val());
                $.ajax({
                    url: "${pageContext.request.contextPath}/insertCommentComment",
                    data: {
                        cidx: $(this).parent().prev().prev().children("input[name=cidx]").val(),
                        idx: $(this).parent().prev().prev().children("input[name=idx]").val(),
                        step: $(this).parent().prev().prev().children("input[name=step]").val(),
                        depth: $(this).parent().prev().prev().children("input[name=depth]").val(),
                        comment: $(this).parent().prev().children().val()
                    },
                    type: "POST",
                    dataType: "json",
                    success: getcomment
                });
            }

>
            //write_comment_comment 이후 댓글화면 최신화 ajax 코드
          	function getcomment(data) {
          	    var commentlist = data.commentlist;
          	    var table = $("<table id='commentTable'></table>"); 
          	    $.each(commentlist, function(index, item) {
          	        var row = $("<tr></tr>");
          	        var nb="";
          	        for (var i = 0; i < item.depth; i++) {
          	            nb+="&nbsp;&nbsp;";
          	            } 
          	      var td= $("<td>"+nb+ item.writer+ ": " + item.content + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + item.wdate+"</td>")
          	        td.appendTo(row);
          	        if ("${mid}" != item.id) {
          	            $("<td><input type='button' value='댓글달기' class='writecomment'><input type='hidden' name='cidx'  value='"+item.cidx+"'><input type='hidden' name='idx'  value='"+item.idx+"'><input type='hidden' name='step'  value='"+item.step+"'><input type='hidden' name='depth'  value='"+item.depth+"'></td>").appendTo(row);
          	        } else {
          	            $("<td><input type='button' value='삭제' class='deletecomment'><input type='hidden' name='cidx' value='"+item.cidx+"'></td>").appendTo(row);
          	        }
          	        row.appendTo(table);
          	    });
          	    $("table").replaceWith(table);
          	}   	 
       		

## 프로젝트 소감
- 한 땀 한 땀 코드를 구현하면서 `MVC 패턴`에 대해 이해할 수 있었고,
`Ajax`를 포함한 자바스크립트를 많이 사용해 볼 수 있었습니다.<br>
또한 페이징이나 대 댓글 등의 알고리즘을 이용한 코드 구현을 통해 코딩에 대한 재미와 자신감을 얻을 수 있었습니다.
포트폴리오를 위해서 Repository를 새로 만들어야 한다는 점을 뒤늦게 깨닫게 되어 초반 작업에 대한 commit이 부족한 점 양해부탁드립니다.
