package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardVO;

public class InsertBoardAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		
		BoardDAO dao=new BoardDAO();
		BoardVO vo=new BoardVO();
		vo.setMid(request.getParameter("mid"));
		vo.setMsg(request.getParameter("msg"));
		System.out.println("인서트 borad action의cnt: 개수"+request.getParameter("cnt"));
		
		if(dao.insert(vo)){
			request.setAttribute("cheak", 1);
			forward=new ActionForward();
			forward.setPath("main.do?my=1");  //글작성인지 내글보기인지 를 비교하는 방식
			forward.setRedirect(false);
			//vo.setMy(0);
		}
		else{
			throw new Exception("insertB 오류");
		}
		//내글 보기 일때 를 구분하기 위해 사용
		
		return forward;
	}

}
