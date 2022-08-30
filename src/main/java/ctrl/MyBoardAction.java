//package ctrl;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import board.BoardDAO;
//import board.BoardVO;
//
//public class MyBoardAction implements Action{
//
//	@Override
//	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		BoardVO bvo=new BoardVO();
//		BoardDAO bdao=new BoardDAO();
////		HttpSession session = request.getSession();
////		  bvo.setMid((String)session.getAttribute("mid"));  
//		String mid=request.getParameter("mid");
//		if(mid!=null) {
//			bvo.setMid(mid);
//		}
//		bdao.selectAll(bvo);
//		
//		  
//		ActionForward forward=null;
//		forward.setPath("main.do");
//		forward.setRedirect(false);
//		return forward;
//	}
//
//}
