package cn.edu.svtcc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.UserBus;
import cn.edu.svtcc.dao.UserDAOimp;
import cn.edu.svtcc.domain.User;
import cn.edu.svtcc.interf.UserDAO;

/**
 * Servlet implementation class LoginServlet
 * 实现登录的逻辑
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		//从客户端获取文本框中的数据，用户名和密码
		//由于我是用jquery传递的数据，所以不用转码（暂时不知道为什么，应该是jquery内部实现了）
		//如果使用post提交要将接受的数据转码 ,例如 String username_r = new String(request.getParameter("userName").getBytes("ISO-8859-1"),"utf-8");
		String username_r = request.getParameter("userName");
		String password_r = request.getParameter("password");
		//创建一个user对象
		User user = new User();
		//设置用户名
		user.setUsername(username_r);
		//设置密码
		user.setPwd(password_r);
		//获得查询到的用户
		User users = UserBus.login(user);
		//调用DAO层的方法，进行登录验证
		if(users != null) {
			//验证是用户还是管理员
			if(users.getRoleId() == 1) {
				//如果验证成功，将当前登录的用户名放到session域中
				request.getSession().setAttribute("user", users);
				//将登录状态设置为true放入session域中
				request.getSession().setAttribute("state", true);
				response.getWriter().print("OK");
			}
			if(users.getRoleId() == 2) {
				request.getSession().setAttribute("admin", users);
				response.getWriter().print("admin");
			}
			
		} else {
			//验证失败
			response.getWriter().print("Error");
		}
	}

}
