package board.controller.write;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;

/**
 * Servlet implementation class DoWriteServlet
 */
@WebServlet("/write.do")
public class DoWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result=0;
		System.out.println("게시글 등록을위한 doget진입");
		BoardService bs= new BoardService();
		String mid=(String)request.getSession().getAttribute("mid");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String idx=request.getParameter("idx");
		System.out.println("idx(dowriteservlet):"+idx);
		if(idx!=null) {  							//idx(게시물번호)가 있다면 수정이니 idx 이용하여 수정.
			result=bs.rewrite(idx,subject,content);
		}
		else {										//아니라면 현재 계정을 이용하여 수정하면됨.
			result=bs.write(mid,subject,content);       
		}
		if(result==0) {
			System.out.println("게시물 작성실패");
			request.setAttribute("writeSucessFail", 0);
			request.getRequestDispatcher("WEB-INF/view/content/write.jsp");
		}
		else {
			System.out.println("게시물 작성성공");
			request.setAttribute("writeSucessFail", 1);
			response.sendRedirect(request.getContextPath()+"/list");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
