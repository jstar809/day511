package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardVO;

public class DeleteBoardAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		
		BoardDAO dao=new BoardDAO();
		BoardVO vo=new BoardVO();
		vo.setBid(Integer.parseInt(request.getParameter("bid")));
		if(dao.delete(vo)){
			forward=new ActionForward();
			forward.setPath("main.do?cnt="+request.getParameter("cnt"));
			forward.setRedirect(true);
			System.out.println("글삭제cnt"+request.getParameter("cnt"));
			request.setAttribute("cnt", request.getParameter("cnt"));
		}
		else{
			throw new Exception("deleteB 오류");
		}
		return forward;
	}

}
