package cn.edu.svtcc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.OrderBus;
import cn.edu.svtcc.domain.Order;
import cn.edu.svtcc.domain.User;

/**
 * Servlet implementation class OrderServlet
 * 为订单页面提供信息的逻辑，负责转发信息
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//检查登录状态
		if(request.getSession().getAttribute("user")==null) {
			response.getWriter().write("请先登录。");
			return;
		}
		//从session域中获得当前登录的用户对象
		User user = (User)request.getSession().getAttribute("user");
		//获得当前登录的用户名
		String username = user.getUsername();
		//得到该用户的所有订单信息
		List<Order> orderList = OrderBus.getOrdersByUser(username);
		//存入request域中
		request.setAttribute("orderList", orderList);
		//请求转发
		request.getRequestDispatcher("order.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
