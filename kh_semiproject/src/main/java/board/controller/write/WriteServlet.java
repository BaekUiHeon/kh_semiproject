package board.controller.write;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;

/**
 * Servlet implementation class WriteServlet
 */
@WebServlet("/write")
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("게시물작성 서블릿 doget접속.");
	if(request.getSession().getAttribute("mid")!=null) {
		request.getRequestDispatcher("/WEB-INF/view/content/write.jsp").forward(request, response);
	}
	else{
		System.out.println("비로그인 상태로 로그인이 필요한 사이트에 접속함");
		response.sendRedirect(request.getContextPath()+"/semi/main");
		}
	}
}
//  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	
//	}
//	
//	}
