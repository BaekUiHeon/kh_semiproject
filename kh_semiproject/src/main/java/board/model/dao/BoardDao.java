package board.model.dao;
import static common.jdbc.JdbcTemplate.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import board.model.vo.CommentVo;
import board.model.vo.WriterVo;
import board.model.vo.BoardVo;

public class BoardDao {
	
	public int insertLike(SqlSession session,String mid,String idx) {
		int result=0;
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("mid",mid);
		map.put("idx",idx);
		result = session.insert("like.insertLike",map);
		return result;
	}
	public int deleteLike(SqlSession session,String mid,String idx) {
		int result=0;
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("mid",mid);
		map.put("idx",idx);
		result = session.delete("like.deleteLike",map);
		return result;
	}
	
	public int countLike(SqlSession session,String idx) {
		int result=-1;
		result = session.selectOne("like.countLike",idx);
		return result;
	}
	
	public BoardVo getBoard(SqlSession session,String idx) {
		BoardVo vo= session.selectOne("board.getBoard", idx);
		return vo;
	}
	
	public int write(SqlSession session,String mid,String subject,String content) {
		int result=0;
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("mid",mid);
		map.put("subject",subject);
		map.put("content",content);
		result=session.insert("board.write",map);
		return result;
	}
	
	public int rewrite(SqlSession session,String idx,String subject,String content) {
		int result=0;
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("idx",idx);
		map.put("content",content);
		result=session.update("board.updateContent",map);
		if(result==1) {  //content가 바뀌었다면 뒤이어 제목도 바꿈. service에서 rollback처리.
			map.put("subject", subject);
			session.update("board.updateSubject",map);
			session.commit();
		}
		else
			session.rollback();
		return result;
	}
	public String login(SqlSession session,String id) {
		String result=null;
		result=session.selectOne("writer.login",id);
		return result;
	}
	public List<BoardVo> selectList(SqlSession session, int currentPage, int pageSize, int totalCnt){
		List<BoardVo> result=null;
		int startRownum = 0;
		int endRownum = 0;
		System.out.println("총글개수:"+totalCnt);
		startRownum = (currentPage-1)*pageSize +1;
		endRownum = ((currentPage*pageSize) > totalCnt) ? totalCnt: (currentPage*pageSize);
		System.out.println("startRownum:"+startRownum);
		System.out.println("endRownum:"+endRownum);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("startRownum",startRownum);
		map.put("endRownum",endRownum);
		result= session.selectList("board.getList",map);
		return result;
	}
	public int getTotalCount(SqlSession session) {
		int result=session.selectOne("board.getTotalCount");
		return result;
	}
	public int getSearchTotalCount(SqlSession session,String searchWord) {
		int result=0;
		String searchWord1="%"+searchWord+"%";
		result= session.selectOne("board.getSearchTotalCount",searchWord1);
		return result;
	}
	public List<BoardVo> selectSearchList(SqlSession session, int currentPage, int pageSize, int totalCnt,String searchWord){
		List<BoardVo> result=null;
		int startRownum = 0;
		int endRownum = 0;
		String searchWord1="%"+searchWord+"%"; 
		System.out.println("총글개수:"+totalCnt);
		startRownum = (currentPage-1)*pageSize +1;
		endRownum = ((currentPage*pageSize) > totalCnt) ? totalCnt: (currentPage*pageSize);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("startRownum",startRownum);
		map.put("endRownum",endRownum);
		map.put("searchWord1",searchWord1);
		result=session.selectList("board.getSearchList",map);
		return result;
	}
	public int signup(SqlSession session,WriterVo vo) {
		int result=session.insert("writer.signup",vo);
		return result;
	}

	public int checkId(SqlSession session, String id) {
		int result=-1;
		result=session.selectOne("writer.checkId",id);
		return result;
	}

	public int deleteComment(SqlSession session,String cidx) {
		int result=-1;
		result=session.delete("comment.delete",cidx);
		return result;
	}
    public int insertComment(SqlSession session,String idx,String content,String mid) {
		int result=-1;
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("idx",idx);
		map.put("content",content);
		map.put("mid",mid);
		result= session.insert("comment.insert",map);
		return result;
	}
	public int insertComment(SqlSession session,String idx, String content,String depth,String step, 
											 String mid, String cidx) {
		int result=-1;
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("idx",idx);
		map.put("content",content);
		map.put("depth",depth);
		map.put("step",step);
		map.put("mid",mid);
		map.put("cidx",cidx);
		result=session.insert("comment.insertCoComment",map);
		return result;
	}
	
	public int updateComment(SqlSession session,String idx,String step)
	{
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("idx",idx);
		map.put("step",step);
		int result=session.update("comment.updateComment",map);
		return result;
	}
	
	public List<CommentVo> getComment(SqlSession session,String idx){ 
		List<CommentVo> commentList=session.selectList("comment.getComment",idx);
		return commentList;
	}
}
