package board.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.model.dao.BoardDao;
import board.model.vo.CommentVo;
import board.model.vo.WriterVo;
import board.model.vo.boardVo;

import static common.jdbc.JdbcTemplate.*;

public class BoardService {
	
	private static Connection conn=null;

	
	public int deleteComment(String cidx) {
		int result=-1;
		conn=getConnection();
		BoardDao dao=new BoardDao();
		result=dao.deleteComment(conn,cidx);
		close(conn);
		return result;
	}
	public int insertComment(String idx, String content,String depth,String step, String mid, String cidx) {
		int result=-1;
		conn=getConnection();
		BoardDao dao=new BoardDao();
		dao.updateComment(conn,idx,step);
		result=dao.insertComment(conn,idx,content,depth,step,mid,cidx);
		close(conn);
		return result;
	}
	public int insertComment(String idx,String content,String mid) {
		int result=-1;
		conn=getConnection();
		BoardDao dao=new BoardDao();
		result=dao.insertComment(conn,idx,content,mid);
		close(conn);
		return result;
	}
	public List<CommentVo> getComment(String idx){
		List<CommentVo> commentList=null;
		conn=getConnection();
		BoardDao dao=new BoardDao();
		commentList=dao.getComment(conn,idx);
		close(conn);
		return commentList;
	}
	
	public int updateLike(String mid,String idx) {
		int result=-1;
		conn=getConnection();
		BoardDao dao=new BoardDao();
		result=dao.checkLike(conn,mid,idx);
		if(result==0)
			result=dao.insertLike(conn,mid,idx);
		else if(result==1){
			result=dao.deleteLike(conn,mid,idx);
		}
		close(conn);
		return result;
	}
	public int countLike(String idx) {
		int result=-1;
		conn=getConnection();
		BoardDao dao=new BoardDao();
		result=dao.countLike(conn,idx);
		close(conn);
		return result;
	}
	public boardVo getBoard(String idx) {
		boardVo result=null;
		conn=getConnection();
		BoardDao dao=new BoardDao();
		result=dao.getBoard(conn,idx);
		close(conn);
		return result;
	}
	
	public int rewrite(String idx,String subject,String content) {
		int result =0;
		conn=getConnection();
		BoardDao dao=new BoardDao();
		result=dao.rewrite(conn,idx,subject,content);
		if(result==0) {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int write(String mid,String subject,String content) {
		int result =0;
		conn=getConnection();
		BoardDao dao=new BoardDao();
		result=dao.write(conn,mid,subject,content);
		close(conn);
		return result;
	}
	
	public String login(String id,String password) {
		conn=getConnection();
		BoardDao dao=new BoardDao();
		String result=(dao.login(conn,id,password));
		close(conn);
		return result;
	}
	public Map<String,Object> selectSearchList(int currentPage,int pageSize,String searchWord){
		conn=getConnection();
		BoardDao dao=new BoardDao();
		List<boardVo> list=null;
		int totalCnt=dao.getSearchTotalCount(conn,searchWord);
		list=dao.selectSearchList(conn,currentPage,pageSize,totalCnt,searchWord);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("totalcnt",totalCnt);
		map.put("list",list);
		close(conn);
		return map;
	}
	public Map<String,Object> selectList(int currentPage,int pageSize){
		conn=getConnection();
		BoardDao dao=new BoardDao();
		Map<String,Object> map=new HashMap<String,Object>();
		int totalCnt= dao.getTotalCount(conn);
		List<boardVo> list=null;
		list=dao.selectList(conn,currentPage,pageSize,totalCnt);
		map.put("totalCnt",totalCnt);
		map.put("list",list);
		close(conn);
		return map;
	}
	public int signup(WriterVo vo) {
		conn=getConnection();
		BoardDao dao=new BoardDao();
		int result=dao.signup(conn,vo);
		return result;
	}

	public int checkId(String id) {
		int result=-1;
		conn=getConnection();
		BoardDao dao=new BoardDao();
		result=dao.checkId(conn,id);
		return result;
	}
}
