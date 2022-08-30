package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardVO;

public class FavAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		
		BoardDAO dao=new BoardDAO();
		BoardVO vo=new BoardVO();
		vo.setBid(Integer.parseInt(request.getParameter("bid")));
		if(dao.update(vo)){
			//1.좋아여 를 눌러도 페이징 유지
			String cnt=request.getParameter("cnt");
			request.setAttribute("cnt", cnt);
			System.out.println("좋아여맥션 "+cnt);
			//===================================
			forward=new ActionForward();
			forward.setPath("main.do");
			forward.setRedirect(false);
		}
		else{
			throw new Exception("fav 오류");
		}
		
		return forward;
	}

}
