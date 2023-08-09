package board.controller.login;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.boardVo;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("mid")==null) { //비로그인 상태라면 실행됨.
		if(request.getSession().getAttribute("signupSuccessFail")!=null) {
			request.setAttribute("signupSuccessFail",2);
			request.getSession().removeAttribute("signupSuccessFail");
		}
		request.getRequestDispatcher("/WEB-INF/view/signup/login.jsp").forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath()+"/list"); //로그인상태라면 게시목록으로 바로보냄. 
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService bs=new BoardService();
		System.out.println("로그인 시도시작");
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String result = bs.login(id,password);
		String signupSuccessFail=(String)request.getSession().getAttribute("signupSuccessFail");
		if(signupSuccessFail!=null) { 
			request.setAttribute("singupSuccessFail",1);
			request.getSession().removeAttribute("signupSuccessFail");
		}
		if(result!=null) { 
			if(password.equals(result)) {
				System.out.println("로그인 성공");
				request.getSession().setAttribute("mid", id);
				response.sendRedirect(request.getContextPath()+"/list");
			}
			else {
				System.out.println("비밀번호가 틀렸습니다");
				request.setAttribute("loginSucessFail",1);
				request.getRequestDispatcher("/WEB-INF/view/signup/login.jsp").forward(request, response);
			}
		}
		if(result==null) {
			request.setAttribute("loginSucessFail", 0); 
			System.out.println("아이디가 존재하지 않습니다.");
			request.getRequestDispatcher("/WEB-INF/view/signup/login.jsp").forward(request, response);
			}
		}
	}
