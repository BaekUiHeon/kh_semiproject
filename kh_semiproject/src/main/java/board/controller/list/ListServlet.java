package board.controller.list;

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
 * Servlet implementation class ListServlet
 */
@WebServlet("/list")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("mid")!=null) { // 로그인 상태에서만 접속가능한 웹페이지
		String searchWord=request.getParameter("searchWord");
		String pageNoStr=request.getParameter("currentPage");
		int currentPage=1;
		int pageSize=10;
		int pageBlockSize=5;
		try {
			if(pageNoStr==null)
			System.out.println("현재 페이지가 없으므로 currentPage =1 대입");
			else
				currentPage=Integer.parseInt(pageNoStr);
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
		}
		BoardService bs=new BoardService();
		if(searchWord==null) {
			Map<String,Object> map = bs.selectList(currentPage,pageSize);
			List<boardVo> list=(List<boardVo>)map.get("list");
			int totalCnt=(int)map.get("totalCnt");
			int totalPageNum = totalCnt/pageSize + (totalCnt%pageSize == 0 ? 0 : 1);
			int startPageNum = 1;
			if((currentPage%pageBlockSize) == 0) {
				startPageNum = ((currentPage/pageBlockSize)-1)*pageBlockSize +1;
			} else {
				startPageNum = ((currentPage/pageBlockSize))*pageBlockSize +1;
			}
			int endPageNum = (startPageNum+pageBlockSize > totalPageNum) ? totalPageNum : startPageNum+pageBlockSize-1;
			request.setAttribute("totalPageNum", totalPageNum);
			request.setAttribute("startPageNum", startPageNum);
			request.setAttribute("endPageNum", endPageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("list",list);
			request.setAttribute("totalCnt",totalCnt);
			request.getRequestDispatcher("/WEB-INF/view/content/list.jsp").forward(request, response);
		}
		else {
		Map<String,Object> map = bs.selectSearchList(currentPage,pageSize,searchWord);
		List<boardVo> list=(List<boardVo>)map.get("list");
		int totalCnt=(int)map.get("totalCnt");
		int totalPageNum = totalCnt/pageSize + (totalCnt%pageSize == 0 ? 0 : 1);
		int startPageNum = 1;
		if((currentPage%pageBlockSize) == 0) {
			startPageNum = ((currentPage/pageBlockSize)-1)*pageBlockSize +1;
		} else {
			startPageNum = ((currentPage/pageBlockSize))*pageBlockSize +1;
		}
		int endPageNum = (startPageNum+pageBlockSize > totalPageNum) ? totalPageNum : startPageNum+pageBlockSize-1;
		request.setAttribute("totalPageNum", totalPageNum);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("list",list);
		request.setAttribute("searchWord", searchWord);
		request.setAttribute("totalCnt",totalCnt);
		request.getRequestDispatcher("/WEB-INF/view/content/list.jsp").forward(request, response);
		}
		}
		else{
			System.out.println("비로그인 상태로 로그인이 필요한 사이트에 접속함");
			response.sendRedirect(request.getContextPath()+"/main");
		}
	}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//	}
//}
