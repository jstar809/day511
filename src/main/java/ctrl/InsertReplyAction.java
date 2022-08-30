package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.ReplyVO;

public class InsertReplyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		
		BoardDAO dao=new BoardDAO();
		ReplyVO vo=new ReplyVO();
		vo.setBid(Integer.parseInt(request.getParameter("bid")));
		vo.setMid(request.getParameter("mid"));
		vo.setRmsg(request.getParameter("rmsg"));
		if(dao.insertR(vo)){
			forward=new ActionForward();
			forward.setPath("main.do");
			forward.setRedirect(false);
		}
		else{
			throw new Exception("insertR 오류");
		}
		
		return forward;
	}

}
