package board.controller.list;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.CommentVo;
import board.model.vo.boardVo;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idx = request.getParameter("idx");
		int countLike=0;
		BoardService bs=null; 
		bs=new BoardService();
		List<CommentVo> commentList= bs.getComment(idx);
		countLike=bs.countLike(idx);
		request.setAttribute("countLike",countLike);
		request.setAttribute("commentlist",commentList);
		if(idx!=null) {
		boardVo vo=bs.getBoard(idx);
		if(vo!=null) {
		request.setAttribute("vo", vo);
		request.getRequestDispatcher("/WEB-INF/view/content/board.jsp").forward(request, response); //<추후 JSP작업 필요함.(vo 사용)>
		}
		else {
			request.setAttribute("nullError",1);
			request.getRequestDispatcher("/list").forward(request, response); //해당하는 게시물이 없다면 list로 다시이동해야함.
		}
		}
		else { //번호없이 이주소를 쳤다면 list로 바로이동하도록함.
			response.sendRedirect(request.getContextPath()+"/list");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
