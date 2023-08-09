package board.controller.list;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import board.model.service.BoardService;
import board.model.vo.CommentVo;

/**
 * Servlet implementation class GetCommentServlet
 */
@WebServlet("/deletecomment")
public class DeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cidx=request.getParameter("cidx");
		int result=0;
		BoardService bs = new BoardService();
		result=bs.deleteComment(cidx);   
		
		if(result==1)
			System.out.println("댓글이 삭제되었습니다(from deleteCommentServlet)");
		else
			System.out.println("댓글 삭제실패(from deleteCommentServlet)");

		PrintWriter out=response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		System.out.println("json으로 보내기완료(from DeleteCommentServlet)");
	}
}
