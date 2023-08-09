package board.model.dao;
import static common.jdbc.JdbcTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.model.vo.CommentVo;
import board.model.vo.WriterVo;
import board.model.vo.boardVo;

public class BoardDao {
	
	public int insertLike(Connection conn,String mid,String idx) {
		int result=-1;
		String query="insert into tbl_like values(?,?)";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,idx);
			pstmt.setString(2,mid);
			result=pstmt.executeUpdate();
			System.out.println("dao like입력완료");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public int deleteLike(Connection conn,String mid,String idx) {
		int result=-1;
		String query="delete from tbl_like where idx=? and id=?";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,idx);
			pstmt.setString(2,mid);
			result=pstmt.executeUpdate();
			System.out.println("dao like삭제완료");
			result=0;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public int checkLike(Connection conn,String mid,String idx) {
		int result=0;
		String query="select id countlike from tbl_like where id=? and idx=?";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,mid);
			pstmt.setString(2,idx);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=1;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public int countLike(Connection conn,String idx) {
		int result=-1;
		String query="select count(*) countlike from tbl_like where idx=?";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,idx);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{
			close(rs);
			close(pstmt);
		}
			return result;
	}
	
	public boardVo getBoard(Connection conn,String idx) {
		String query="select writer,idx,subject,content,to_char(wdate),id from (select * from tbl_board where idx=?) join tbl_writer using (id)";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boardVo vo=null;
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,idx);
			rs=pstmt.executeQuery();
			vo=new boardVo();
			if(rs.next()) {
				vo.setWriter(rs.getString(1));
				vo.setIdx(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setWdate(rs.getString(5));
				vo.setId(rs.getString(6));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return vo;
	}
	
	public int write(Connection conn,String mid,String subject,String content) {
		int result=0;
		String query="insert into tbl_board (id,subject,content) values(?,?,?)";
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,mid);
			pstmt.setString(2,subject);
			pstmt.setString(3,content);
			System.out.println("wirte의 exceuteUpdate직전");
			result=pstmt.executeUpdate();
			System.out.println("데이터베이스 게시물 insert완료");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	
	public int rewrite(Connection conn,String idx,String subject,String content) {
		int result=0;
		String query="update tbl_board set content=? where idx=?";
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,content);
			pstmt.setString(2,idx);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		if(result==1) { //content가 바뀌었다면 뒤이어 제목도 바꿈. service에서 rollback처리.
			query="update tbl_board set subject=? where idx=?";
			try {
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1,subject);
				pstmt.setString(2,idx);
				result=pstmt.executeUpdate();
				System.out.println("데이터베이스 게시물 수정완료");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				close(pstmt);
			}
		}
		return result;
	}
	public String login(Connection conn,String id,String password) {
		String result=null;
		PreparedStatement pstmt=null;
		String query="select password from TBL_WRITER where id=?";
		ResultSet rs= null;
			try {
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1,id);
				rs=pstmt.executeQuery();
				if(rs.next()==true) {
					result=rs.getString("password");
					System.out.println("데이터 베이스에서 로그인 가능여부 확인");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				close(pstmt);
				close(rs);
			}
		return result;
	}
	public List<boardVo> selectList(Connection conn, int currentPage, int pageSize, int totalCnt){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<boardVo> result=null;
		int startRownum = 0;
		int endRownum = 0;
		System.out.println("총글개수:"+totalCnt);
		startRownum = (currentPage-1)*pageSize +1;
		endRownum = ((currentPage*pageSize) > totalCnt) ? totalCnt: (currentPage*pageSize);
		System.out.println("startRownum:"+startRownum);
		System.out.println("endRownum:"+endRownum);
		String query="select * from (select tb1.*,rownum rn from((select writer,IDX,subject,content,to_char(WDATE,'yyyy-mm-dd') wdate,ID from TBL_BOARD join TBL_writer using (id) order by IDX) Tb1)) where rn between ? and ?";	
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,startRownum);
			pstmt.setInt(2,endRownum);
			rs=pstmt.executeQuery();
			result=new ArrayList<boardVo>();
			while(rs.next()==true) {
				boardVo vo=new boardVo
						(rs.getString("idx"),
						rs.getString("subject"),
						rs.getString("content"),
						rs.getString("wdate"),
						rs.getString("id"),
						rs.getString("writer"));
						result.add(vo);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
			close(pstmt);
			close(rs);
			}
		return result;
	}
	public int getTotalCount(Connection conn) {
		int result=0;
		String query="select count(*) total_count from tbl_board";
		ResultSet rs= null;
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			if(rs.next()==true)
				result=rs.getInt("total_count");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public int getSearchTotalCount(Connection conn,String searchWord) {
		int result=0;
		String query="select count(*) total_count from tbl_board where content like ? or subject like ?";
		ResultSet rs= null;
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(query);
			String searchWord1="%"+searchWord+"%";
			pstmt.setString(1, searchWord1);
			pstmt.setString(1, searchWord1);
			rs=pstmt.executeQuery();
			if(rs.next()==true)
				result=rs.getInt("total_count");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public List<boardVo> selectSearchList(Connection conn, int currentPage, int pageSize, int totalCnt,String searchWord){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<boardVo> result=null;
		int startRownum = 0;
		int endRownum = 0;
		System.out.println("총글개수:"+totalCnt);
		startRownum = (currentPage-1)*pageSize +1;
		endRownum = ((currentPage*pageSize) > totalCnt) ? totalCnt: (currentPage*pageSize);
		System.out.println("startRownum:"+startRownum);
		System.out.println("endRownum:"+endRownum);
		String query="select * from (select tb1.*,rownum rn from((select IDX,subject,content,to_char(WDATE,'yyyy-mm-dd'),ID from TBL_BOARD where subject like ? or content like ? order by IDX) Tb1) where rn between ? and ?)";	
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,searchWord);
			pstmt.setString(2,searchWord);
			pstmt.setInt(3,startRownum);
			pstmt.setInt(4,endRownum);
			rs=pstmt.executeQuery();
			result=new ArrayList<boardVo>();
			while(rs.next()==true) {
				boardVo vo=new boardVo
						(rs.getString("idx"),
						rs.getString("subject"),
						rs.getString("content"),
						rs.getString("wdate"),
						rs.getString("id"));
					result.add(vo);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
			close(pstmt);
			close(rs);
			}
		return result;
	}
	public int signup(Connection conn,WriterVo vo) {
		int result=0;
		String query="insert into tbl_writer values(?,?,?,?)";
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,vo.getId());
			pstmt.setString(2,vo.getWriter());
			pstmt.setString(3,vo.getPassword());
			pstmt.setString(4,vo.getEmailAddress());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}

	public int checkId(Connection conn, String id) {
		int result=-1;
		String query="select id from tbl_writer where id=?";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			if(rs.next()) {				
				result=1;
			}
			else
				result=0;
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
		System.out.println("dao [checkId] return : "+result);
		return result;
	}

	public int deleteComment(Connection conn,String cidx) {
		int result=-1;
		String query="delete from tbl_comment where cidx=?";
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,cidx);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
    public int insertComment(Connection conn,String idx,String content,String mid) {
		int result=-1;
		String query="insert into tbl_comment (idx,content,step,id) values(?,?,(select case when max(step) is null then 1 else max(step)+1 end as tbl from tbl_comment where idx=?),?)"; //완성처리해야함.
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,idx);
			pstmt.setString(2,content);
			pstmt.setString(3,idx);
			pstmt.setString(4,mid);
			result=pstmt.executeUpdate();
			System.out.println("result:"+result+"insert 실행되었음.");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		finally {
		close(pstmt);
		}	
		return result;
	}
	public int insertComment(Connection conn,String idx, String content,String depth,String step, 
											 String mid, String cidx) {
		int result=-1;
		String query="insert into tbl_comment (idx,content,ccidx,depth,step,id) values(?,?,?,?+1,?+1,?)"; //완성처리해야함.
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,idx);
			pstmt.setString(2,content);
			pstmt.setString(3,cidx);
			pstmt.setString(4,depth);
			pstmt.setString(5,step);
			pstmt.setString(6,mid);
			result=pstmt.executeUpdate();
			System.out.println("result:"+result+"insert 실행되었음.");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		finally {
		close(pstmt);
		}	
		return result;
	}
	
	public int updateComment(Connection conn,String idx,String step) {
		int result=-1;
		String query="update tbl_comment set step=step+1 where idx=? and step>?";
		PreparedStatement pstmt=null;
		System.out.println(idx+step);
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,idx);
			pstmt.setString(2,step);
			result=pstmt.executeUpdate();
			System.out.println("result="+result+"insert를 위한 updateComment가 실행되었음.");
		}
		catch (SQLException e) {
		e.printStackTrace();
		}
		finally {
		close(pstmt);
		}
		return result;
	}
	
	public List<CommentVo> getComment(Connection conn,String idx){ 
		List<CommentVo> commentList=null;
		String query="select idx,ccidx,depth,step,content,cidx,id,wdate,writer from ((select * from tbl_comment where idx=?) join tbl_writer using (id)) order by step asc,ccidx,step desc"; 
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		CommentVo vo=null;
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, idx);
			rs=pstmt.executeQuery();
			System.out.println("작성완료.");
			commentList=new ArrayList<CommentVo>();
			while(rs.next()) {
				vo= new CommentVo();
				vo.setIdx(rs.getInt("idx"));
				vo.setCcidx(rs.getInt("ccidx"));
				vo.setdepth(rs.getInt("depth"));
				vo.setStep(rs.getInt("Step"));
				vo.setContent(rs.getString("content"));
				vo.setCidx(rs.getInt("cidx"));
				vo.setId(rs.getString("id"));
				vo.setWdate(rs.getString("wdate"));
				vo.setWriter(rs.getString("writer"));
				commentList.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commentList;
	}
}
