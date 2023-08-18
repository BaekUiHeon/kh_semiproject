package board.model.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import board.model.dao.BoardDao;
import board.model.vo.CommentVo;
import board.model.vo.WriterVo;
import board.model.vo.BoardVo;
import static common.mybatis.MyBatisTemplate.*;


public class BoardService {
	
	private static SqlSession session=null;

	
	public int deleteComment(String cidx) {
		int result=-1;
		session=getSession();
		BoardDao dao=new BoardDao();
		result=dao.deleteComment(session,cidx);
		session.close();
		return result;
	}
	public int insertComment(String idx, String content,String depth,String step, String mid, String cidx) {
		int result=-1;
		session=getSession();
		BoardDao dao=new BoardDao();
		dao.updateComment(session,idx,step);
		result=dao.insertComment(session,idx,content,depth,step,mid,cidx);
		session.close();
		return result;
	}
	public int insertComment(String idx,String content,String mid) {
		int result=-1;
		session=getSession();
		BoardDao dao=new BoardDao();
		result=dao.insertComment(session,idx,content,mid);
		session.close();
		return result;
	}
	public List<CommentVo> getComment(String idx){
		List<CommentVo> commentList=null;
		session=getSession();
		BoardDao dao=new BoardDao();
		commentList=dao.getComment(session,idx);
		session.close();
		return commentList;
	}
	
	public int updateLike(String mid,String idx) {
		int result=0;
		session=getSession();
		BoardDao dao=new BoardDao();
		try {
		result=dao.insertLike(session,mid,idx);
		}
		catch(Exception e) {
			System.out.println("좋아요가 존재하였음");
		}
		if(result!=0) {
		result=dao.countLike(session,idx);
		session.close();
		}
		else{
		result=dao.deleteLike(session,mid,idx);
		if(result==1) {
			result=dao.countLike(session,idx);
			session.close();
		}
		}
		return result;
	}
	public int countLike(String idx) {
		int result=-1;
		session=getSession();
		BoardDao dao=new BoardDao();
		result=dao.countLike(session,idx);
		session.close();
		return result;
	}
	public BoardVo getBoard(String idx) {
		BoardVo result=null;
		session=getSession();
		BoardDao dao=new BoardDao();
		result=dao.getBoard(session,idx);
		session.close();
		return result;
	}
	
	public int rewrite(String idx,String subject,String content) {
		int result =0;
		session=getSession(false);
		BoardDao dao=new BoardDao();
		result=dao.rewrite(session,idx,subject,content);
		session.close();
		return result;
	}
	
	public int write(String mid,String subject,String content) {
		int result =0;
		session=getSession();
		BoardDao dao=new BoardDao();
		result=dao.write(session,mid,subject,content);
		session.close();
		return result;
	}
	
	public String login(String id) {
		session=getSession();
		BoardDao dao=new BoardDao();
		String result=(dao.login(session,id));
		session.close();
		return result;
	}
	public Map<String,Object> selectSearchList(int currentPage,int pageSize,String searchWord){
		session=getSession();
		BoardDao dao=new BoardDao();
		List<BoardVo> list=null;
		int totalCnt=dao.getSearchTotalCount(session,searchWord);
		list=dao.selectSearchList(session,currentPage,pageSize,totalCnt,searchWord);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("totalCnt",totalCnt);
		map.put("list",list);
		session.close();
		return map;
	}
	public Map<String,Object> selectList(int currentPage,int pageSize){
		session=getSession();
		BoardDao dao=new BoardDao();
		Map<String,Object> map=new HashMap<String,Object>();
		int totalCnt= dao.getTotalCount(session);
		List<BoardVo> list=null;
		list=dao.selectList(session,currentPage,pageSize,totalCnt);
		map.put("totalCnt",totalCnt);
		map.put("list",list);
		session.close();
		return map;
	}
	public int signup(WriterVo vo) {
		session=getSession();
		BoardDao dao=new BoardDao();
		int result=dao.signup(session,vo);
		return result;
	}
	
	public int checkId(String id) {
		int result=0;
		session=getSession();
		BoardDao dao=new BoardDao();
		result=dao.checkId(session,id);
		return result;
	}
}
