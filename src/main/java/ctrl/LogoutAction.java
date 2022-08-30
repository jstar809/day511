package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		session.invalidate();
		
		ActionForward forward=new ActionForward();
		//1. 로그인시 cnt를 넣어줌
		request.setAttribute("cnt", request.getParameter("cnt"));
		forward.setPath("main.do");
		forward.setRedirect(true);
		return forward;
	}

}
