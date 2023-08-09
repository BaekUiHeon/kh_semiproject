package board.controller.list;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idx=request.getParameter("idx");
		String mid=request.getParameter("mid");
		int countLike=-1;
		BoardService bs=bs=new BoardService();
		int result=bs.updateLike(mid,idx);
		
		if(result==1)
			System.out.println(" getLikeServlet에서 좋아요가 추가되었습니다");
		else if(result==0)
			System.out.println("getLikeServlet에서 좋아요가 삭제되었습니다.");
		else
			System.out.println("getLikeServlet에서 좋아요관련 함수가 제대로 동작하지 않았습니다.");
			
		countLike=bs.countLike(idx);
		System.out.println("getLikeServlet에서 좋아요를 counting하였음. ");
		
		PrintWriter out = response.getWriter();
		if(countLike!=-1) {
			out.print(countLike);
			out.flush();
			out.close();
			System.out.println("getLikeServlet 업데이트 종료");
		}
		else {
			out.print("가져오기 실패");
			out.flush();
			out.close();
		}
	}
}
