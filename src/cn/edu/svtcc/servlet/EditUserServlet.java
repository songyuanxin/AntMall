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
 * Servlet implementation class EditUserServlet
 * 编辑用户信息的逻辑
 */
@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserServlet() {
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
		//分别获得用户名，电话，session中的验证码以及用户输入的验证码
		String username = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String checking = request.getSession().getAttribute("sb").toString();
		String checking_r = request.getParameter("request");
		//将用户输入的验证与session中的验证码进行对比，验证是否输入正确
		if(!checking_r.equals(checking)) {
			//输入错误则返回checkingError信息
			response.getWriter().write("checkingError");
			return;
		}
		//创建User对象并存入数据
		User user=  new User();
		user.setUsername(username);
		user.setPhone(phone);
		//修改用户信息
		if(UserBus.editUser(user)){
			//更新当前登录的用户信息
			User users = UserBus.getUser(username);
			request.getSession().setAttribute("user", users);
			//返回信息OK
			response.getWriter().write("OK");
		}
	}

}
