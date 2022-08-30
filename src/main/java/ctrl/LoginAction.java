package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberVO;

public class LoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberDAO dao=new MemberDAO();
		MemberVO vo=new MemberVO();
		vo.setMid(request.getParameter("mid"));
		vo.setMpw(request.getParameter("mpw"));
		vo=dao.selectOne(vo);
		if(vo!=null) {
			HttpSession session=request.getSession();
			session.setAttribute("mid", vo.getMid());
			System.out.println("로그인 완료 되서"+session.getAttribute("mid"));
		}
		else {
			System.out.println("로그: 로그인 실패");
			request.setAttribute("tf", false);
		}
		//1.로그인 시 cnt
		//request.setAttribute("cnt", request.getParameter("cnt"));
		//==========================
		System.out.println("로그인 do cnt:"+request.getParameter("cnt"));
		ActionForward forward=new ActionForward();
		forward.setPath("main.do");
		forward.setRedirect(true);
		return forward;
	}

}
