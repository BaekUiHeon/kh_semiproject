package board.controller.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import board.model.service.BoardService;
import board.model.vo.CommentVo;

/**
 * Servlet implementation class InsertCommentComment
 */
@WebServlet("/insertCommentComment")
public class InsertCommentComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCommentComment() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("insertcommentconmment doget");
		String idx= request.getParameter("idx");
		String content= request.getParameter("comment");
		String depth=request.getParameter("depth");
		String step=request.getParameter("step");
		String mid=(String)request.getSession().getAttribute("mid");
		String cidx=request.getParameter("cidx");
		int result=-1;
		BoardService bs= new BoardService();
		
		result=bs.insertComment(idx,content,depth,step,mid,cidx);
		
		if(result==1) {
			System.out.println("comment 입력완료.");
			List<CommentVo> commentlist= bs.getComment(idx);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("commentlist",commentlist);
			System.out.println("commentlist: "+commentlist);
			Gson gson = new Gson();
			
			PrintWriter out = response.getWriter();
			out.print(gson.toJson(map));
			out.flush();
			out.close();
			System.out.println("commentlist 전송완료(from insertCommentServlet)");
		}
		
		else if(result==-1)
			System.out.println("comment 입력실패");
	    }		
	}
