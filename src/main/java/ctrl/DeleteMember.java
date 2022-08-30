package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberVO;

public class DeleteMember implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//System.out.println("로그: deletMember 들어왔는지?");
		//세션 사용하기
		HttpSession session = request.getSession();
		
		MemberVO mvo=new MemberVO();
		MemberDAO mdao=new MemberDAO();
		System.out.println("로그 deleteaMemberAction의mid:["+request.getParameter("mid")+"]");
		System.out.println("로그 deleteaMemberAction의mpw:["+request.getParameter("mpw")+"]");
		mvo.setMid(request.getParameter("mid"));
		mvo.setMpw(request.getParameter("mpw"));
		
		if(mdao.delete(mvo)) {
			System.out.println("회원 삭제 성공");
			// 삭제시 로그아웃
			session.invalidate();
			
		}else {
			System.out.println("회원 삭제 실페");
			
			//throw new Exception("비밀번호 잘못입력등 문제가 생겻습니다");
		}
		
		
		ActionForward forward=new ActionForward();
		forward.setPath("main.do");
		forward.setRedirect(true);
		
		return forward;
	}

}
