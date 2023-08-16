package board.controller.list;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.WriterVo;

/**
 * Servlet implementation class GetLikeServlet
 */
@WebServlet("/getlike")
public class GetLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idx=request.getParameter("idx");
		String mid=(String)request.getSession().getAttribute("mid");
		BoardService bs=new BoardService();
		int result=bs.updateLike(mid,idx);
		
		PrintWriter out = response.getWriter();
		if(result!=-1) {
			System.out.println("getLikeServlet 업데이트 성공");
			out.print(result);
			out.flush();
			out.close();
		}
		else {
			System.out.println("getLikeServlet 업데이트 실패");
			out.print("가져오기 실패");
			out.flush();
			out.close();
		}
	}
}
