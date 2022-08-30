package ctrl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.BoardDAO;
import board.BoardSet;
import board.BoardVO;
import member.MemberDAO;
import member.MemberVO;

public class MainAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		BoardDAO dao=new BoardDAO();
		BoardVO vo=new BoardVO();
		MemberDAO mdao=new MemberDAO();
		MemberVO mvo =new MemberVO();
		//내글 보기 클릭시
		//내글보기에 넘어가진 친구들
//		String d=request.getParameter("cheak");
//		if(d.equals("1")){
//			vo.setMy(1);
//		}
			String mid=request.getParameter("mid");
			vo.setMid(mid);
		
		//로그인 상태라면 그로그인 아이디가 쓴 글의 개수를 새어주는 로직
		HttpSession session = request.getSession();
		if(session.getAttribute("mid")!=null) {
			String smid=(String) session.getAttribute("mid");
			BoardVO vvo=new BoardVO();
			//아이디당 글개수 찾는로직
			System.out.println("smid:"+smid);
			vvo.setMid(smid);
			int mb=dao.selectAll_bcnt_MB(vvo);
			//아이디당 댓글 갯수 찾는로직
			int rc=dao.countR(vvo);
			System.out.println("지금 아이디의 글갯수 mb의값"+mb);
			System.out.println("지금 아이디의 댓굴갯수 rc의값"+rc);
			
			//세션으로 하면 메모리 낭비가 심하지만 리퀘스트가 안됨 ㅠㅠ
			session.setAttribute("mb", mb);
			session.setAttribute("rc", rc);
			
			//request.setAttribute("mb", mb);
			String paramCnt=request.getParameter("cnt");
			System.out.println("맨인 액션의 cnt로그인시"+paramCnt);

		}
//			request.setAttribute("mid", vo.getMid());
		
//		vo.setMid((String)session.getAttribute("mid"));  
		System.out.println(vo.getMid()+ "아이디");  
		  
		String paramCnt=request.getParameter("cnt");
		System.out.println("맨인 액션의 cnt"+paramCnt);
		if(paramCnt==null || paramCnt.equals("")){
			vo.setCnt(5);// 페이지 처음 들어 갔을때 보여주
		}
		else {
			vo.setCnt(Integer.parseInt(paramCnt));
		}
		//내글 쓰기인지 아닌지
		System.out.println("main액샨에서 리퀘스트 my의 값"+request.getParameter("my"));
		if(request.getParameter("my")!=null) {
			vo.setMy(Integer.parseInt(request.getParameter("my")));
			
		}
		
		ArrayList<BoardSet> datas=dao.selectAll(vo);
		request.setAttribute("datas", datas);
		request.setAttribute("cnt", vo.getCnt());
		//친구들 만들기
		ArrayList<MemberVO> mdatas=mdao.newFriend(mvo);
		request.setAttribute("mdatas", mdatas);
		
//////////////////////////////////////////
		
		ActionForward forward=new ActionForward();
		forward.setPath("main.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
