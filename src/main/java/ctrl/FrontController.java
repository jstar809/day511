package ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do") // *.do 요청을 수행하면 해당 어노테이션에 의해 FC로 오게됨!!!
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		// ★ FrontController fc=new FrontController(); xxx
		// 객체화를 하지않았는데, 메서드를 사용할수있었다...?!!
		// 서블릿 컨테이너(==객체를 관리하는 것) == 웹 서버 == 톰캣이 서블릿을 객체화해주고있었음!!    	
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		String cp=request.getContextPath();
		String command=uri.substring(cp.length());
		System.out.println(command);
		
		System.out.println("프론트 컨토를로러의 cnt(null이면 5로 지정 될예정):"+request.getParameter("cnt"));

		ActionForward forward=null;
		//메인
		if(command.equals("/main.do")) {
			try {
				forward=new MainAction().execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//로그아웃
		else if(command.equals("/logout.do")) {
			try {
				forward=new LogoutAction().execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//글삭제
		else if(command.equals("/deleteB.do")) {
			try {
				forward=new DeleteBoardAction().execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//좋아여!
		else if(command.equals("/fav.do")) {
			try {
				forward=new FavAction().execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//로그인
		else if(command.equals("/login.do")) {
			try {
				forward=new LoginAction().execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}// 댓글 삭제
		else if(command.equals("/deleteR.do")) {
			try {
				forward=new DeleteReplyAction().execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}// 글작성
		else if(command.equals("/insertB.do")) {
			try {
				forward=new InsertBoardAction().execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//댓글 작성
		else if(command.equals("/insertR.do")) {
			try {
				forward=new InsertReplyAction().execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//회원가입
		else if(command.equals("/insertM.do")) {
			try {
				forward=new InsertMemberAction().execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//내글보기
		else if(command.equals("/myboard.do")) {
			try {
				forward=new MainAction().execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}// 회원 탈퇴
		else if(command.equals("/deleteMember.do")) {
			try {
				forward=new DeleteMember().execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		/*
		if(forward==null) {
			forward=new ActionForward();
			forward.setPath("error/error.jsp");
			forward.setRedirect(false);
		}

		RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
		try {
			dispatcher.forward(request, response);
			// : 타겟페이지(인자)로 request,response 객체를 전달하는 메서드
			// : 제어권을 넘겨줌 -> 클라이언트가 응답을 확인할수있음
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 */

		if(forward!=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}
			else {
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}else {

			//forward 값 이 null 일떄
			//PrintWriter out = response.getWriter();
			//out.println("<script>alert('요청처리실패!');history.go(-1);</script>");
			response.sendRedirect("main.do");
		}
	}

}
