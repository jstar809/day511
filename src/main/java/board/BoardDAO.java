package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.JDBCUtil;

public class BoardDAO {
	Connection conn;
	PreparedStatement pstmt;
	final String sql_selectAll="SELECT * FROM BOARD ORDER BY BID DESC LIMIT 0,?";
	// LIMIT 은 MySQL
	// Oracle 은 ROWNUM 을 사용함 (※ SQL문 실행순서 유의!)
	final String sql_selectAll_bcnt="SELECT * FROM BOARD";
	//내글 보기에 글전체 개수 확인 로직
	final String sql_selectAll_bcnt_MB="SELECT * FROM BOARD where mid=?";
	//댓글 보여쥬기
	final String sql_selectAll_R="SELECT * FROM REPLY WHERE BID=? ORDER BY RID DESC";
	final String sql_selectAll_Ra="SELECT * FROM REPLY WHERE MID=? ORDER BY RID DESC";

	final String sql_insert="INSERT INTO BOARD(MID,MSG) VALUES(?,?)";
	final String sql_delete="DELETE FROM BOARD WHERE BID=?";
	final String sql_insert_R="INSERT INTO REPLY(MID,BID,RMSG) VALUES(?,?,?)";
	final String sql_delete_R="DELETE FROM REPLY WHERE RID=?";
	final String sql_update="UPDATE BOARD SET FAVCNT=FAVCNT+1 WHERE BID=?";
	final String sql_selectAll_MB="SELECT * FROM BOARD WHERE MID=? ORDER BY BID DESC LIMIT 0,?";
	public boolean insert(BoardVO bvo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_insert);
			pstmt.setString(1, bvo.getMid());
			pstmt.setString(2, bvo.getMsg());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}
	public boolean delete(BoardVO bvo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_delete);
			pstmt.setInt(1,bvo.getBid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}
	public boolean insertR(ReplyVO rvo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_insert_R);
			pstmt.setString(1, rvo.getMid());
			pstmt.setInt(2, rvo.getBid());
			pstmt.setString(3, rvo.getRmsg());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}
	public boolean deleteR(ReplyVO rvo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_delete_R);
			pstmt.setInt(1, rvo.getRid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}
	public boolean update(BoardVO bvo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_update);
			pstmt.setInt(1, bvo.getBid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}
	
//	final String sql_selectAll_bcnt_MB="SELECT * FROM BOARD where mid=?";
//	회원 탈퇴시 내글이 ==0인것을 반환
	public int selectAll_bcnt_MB(BoardVO bvo) {
		conn=JDBCUtil.connect();
		int i=0;
		try {
			pstmt=conn.prepareStatement(sql_selectAll_bcnt_MB);
			pstmt.setString(1, bvo.getMid());
			ResultSet rs= pstmt.executeQuery();
			
			while(rs.next()) {
				i++;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return i;
	}
	//아이디당 댓글수 갯수를 찾는로직
	
	public int countR(BoardVO bvo) {
		conn=JDBCUtil.connect();
		int i=0;
		try {
			pstmt=conn.prepareStatement(sql_selectAll_Ra);
			pstmt.setString(1, bvo.getMid());
			ResultSet rs= pstmt.executeQuery();
			
			while(rs.next()) {
				i++;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return i;
	}
	public ArrayList<BoardSet> selectAll(BoardVO bvo){ // 유지보수 용이
		ArrayList<BoardSet> datas=new ArrayList<BoardSet>();
		conn=JDBCUtil.connect();
		try {
			
			
			//글개수확인하기
			pstmt=conn.prepareStatement(sql_selectAll_bcnt);
			ResultSet z=pstmt.executeQuery();
			int bcnt=0;// 글개수를 확인 하는 친구
			while(z.next()) {
				bcnt++;
			}
			int mb=0;// 로그인한 아이디 글개수 보여주기위한 인트값
			System.out.println("글전체 개수"+bcnt);
			System.out.println("지금 현재 글작성인지 상태1이면 글작성 중  0이면 다른거=="+bvo.getMy());
			// 내글보기 목록 출력+++++++++++++++++++++
			if((bvo.getMid()!= null) &&(bvo.getMy()!=1) ) {  //로그인 상태일때   //  글출력이 아닐때
				//글개수만 출력할게요=========================================
				System.out.println("내글보기 시작");
				pstmt=conn.prepareStatement(sql_selectAll_bcnt_MB);
				pstmt.setString(1,bvo.getMid());	
				ResultSet mbr=pstmt.executeQuery();
				while(mbr.next()) {
					mb++;
				}
				System.out.println("boardDOA로그/지정한아이디당 글개수를 보여주는 로그:"+mb);
				//===================================================
				//원래대롱(내글보기)
				pstmt=conn.prepareStatement(sql_selectAll_MB);
				
				pstmt.setString(1,bvo.getMid());
				pstmt.setInt(2, bvo.getCnt());
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=
			// 일반 글목록 출력
			}else {
				
				pstmt=conn.prepareStatement(sql_selectAll);
				pstmt.setInt(1, bvo.getCnt());
				System.out.println(bvo+"bvo");
			}
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()) {
				BoardSet bs=new BoardSet();
				BoardVO boardVO=new BoardVO();
				boardVO.setBid(rs.getInt("BID"));
				boardVO.setFavcnt(rs.getInt("FAVCNT"));
				boardVO.setMid(rs.getString("MID"));
				boardVO.setMsg(rs.getString("MSG"));
				boardVO.setRcnt(rs.getInt("RCNT")); ///// rList.size();
				bs.setBoardVO(boardVO);

				ArrayList<ReplyVO> rList=new ArrayList<ReplyVO>();
				pstmt=conn.prepareStatement(sql_selectAll_R);
				pstmt.setInt(1, rs.getInt("BID")); // 현재 BID
				ResultSet rs2=pstmt.executeQuery();

				int i=0;// 댓글 개수를 기억해주는 친구
				while(rs2.next()) {
					
					i++; //댓글이 하나 생길때마다 +1
					ReplyVO replyVO=new ReplyVO();

					replyVO.setBid(rs2.getInt("BID"));
					replyVO.setMid(rs2.getString("MID"));
					replyVO.setRid(rs2.getInt("RID"));
					replyVO.setRmsg(rs2.getString("RMSG"));

					rList.add(replyVO);
				}
				//댓글 ㄱㄱ
				boardVO.setRcnt(i); 
				// 글전채 개수를 지정 하지만 원래는 반복문 밖에서 진행해야하지만 귀찬아서 여기에 진헹
				//이친구도 마찬가지
				boardVO.setMb(mb);
				//System.out.println("dao에서 저장된 mb값"+mb);
				boardVO.setBcnt(bcnt); 

				bs.setBoardVO(boardVO);
				bs.setrList(rList);				

				datas.add(bs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		System.out.println(datas);
		return datas;
	}
}
