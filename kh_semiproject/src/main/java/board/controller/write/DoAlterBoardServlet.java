package board.controller.write;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;

/**
 * Servlet implementation class AlterBoardServlet
 */
@WebServlet("/rewrite.do")
public class DoAlterBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoAlterBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String idx=request.getParameter("idx");
		BoardService bs= new BoardService();
		int result=bs.rewrite(idx,subject,content);
		System.out.println("게시글 수정을위한 doget진입");
		if(result==0) {
			System.out.println("게시물 수정실패");
			request.setAttribute("writeSucessFail", 0);
			request.getRequestDispatcher("WEB-INF/view/content/write.jsp");
		}
		else {
			System.out.println("게시물 수정성공");
			request.getSession().setAttribute("writeSucessFail", "게시물 수정성공");
			response.sendRedirect(request.getContextPath()+"/list");
		}
	}
}
