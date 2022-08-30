package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberVO;

public class InsertMemberAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		
		MemberDAO dao=new MemberDAO();
		MemberVO vo=new MemberVO();
		vo.setMid(request.getParameter("mid"));
		vo.setMname(request.getParameter("mname"));
		vo.setMpw(request.getParameter("mpw"));
		
		boolean flag =false;//회원가입 실페시
		forward=new ActionForward();
		if(dao.insert(vo)){
			forward.setPath("windowX.jsp?");
			forward.setRedirect(true);
			
		}
		else{
			forward.setPath("windowX.jsp?insertM="+flag);
			forward.setRedirect(false);
			throw new Exception("insertM 오류");
			
		}
		return forward;
	}

}
