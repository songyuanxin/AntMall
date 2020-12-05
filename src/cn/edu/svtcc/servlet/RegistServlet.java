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
 * Servlet implementation class RegistServlet
 * 注册用户的逻辑
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
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
		//从客户端获取文本框中的数据，用户名、密码，重复的密码和电话以及验证码
		//由于我是用jquery传递的数据，所以不用转码（暂时不知道为什么，应该是jquery内部实现了）
		//如果使用post提交要将接受的数据转码 ,例如 String username_r = new String(request.getParameter("userName").getBytes("ISO-8859-1"),"utf-8");
		String username_r = request.getParameter("userName");
		String password_r = request.getParameter("password");
		String checking_r = request.getParameter("request");
		String phone = request.getParameter("phone");
		//object转为String
		String checking = request.getSession().getAttribute("sb").toString();
		//判断密码与再次输入的密码是否一致
		if(!checking.equals(checking_r)) {
			response.getWriter().print("checkingError");
			return;
		}
		//创建一个user对象，并传入响应的数据,用户名，密码和电话
		User user = new User(username_r,password_r,phone);
		//调用注册用户的方法，验证是否注册成功
		if(UserBus.addUser(user)) {
			//注册成功
			response.getWriter().print("OK");
		} else {
			//注册失败
			response.getWriter().print("Error1");
		}
	}

}
