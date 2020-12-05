package cn.edu.svtcc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.UserBus;
import cn.edu.svtcc.domain.User;

/**
 * Servlet implementation class RePasswordServlet
 * 修改密码的逻辑
 */
@WebServlet("/RePasswordServlet")
public class RePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取来自页面的信息，用户名，旧密码，新密码，验证码
		String username = request.getParameter("username");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String checking = request.getSession().getAttribute("sb").toString();
		String checking_r = request.getParameter("request");
		//判断密码与再次输入的密码是否一致
		if(!checking.equals(checking_r)) {
			response.getWriter().print("checkingError");
			return;
		}
		//对用户进行验证，寻找是否有该用户，并验证其密码
		if(UserBus.login(new User(username,oldPassword)) != null) {
			//创建用户对象，参数为用户名和新密码
			User user = new User(username,newPassword);
			//通过用户名找到对应用户并修改密码
			if(UserBus.rePwd(user)){
				//返回数据OK
				response.getWriter().write("OK");
			} else {
				response.getWriter().write("Error");
			}
		} else {
			response.getWriter().write("PwdError");
		}
	}

}
