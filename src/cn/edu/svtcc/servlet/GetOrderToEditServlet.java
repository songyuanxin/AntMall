package cn.edu.svtcc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.OrderBus;
import cn.edu.svtcc.domain.Order;

/**
 * Servlet implementation class GetOrderToEditServlet
 * 将订单数据得到并转发给编辑页面的逻辑
 */
@WebServlet("/GetOrderToEditServlet")
public class GetOrderToEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOrderToEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到订单编号
		String orderId = request.getParameter("orderId");
		//获得编号对应的订单对象
		Order order = OrderBus.getOrderById(orderId);
		//将对应订单中的订单编号，收件人，收件地址，收件人电话存入request域中
		request.setAttribute("orderId", order.getOrderId());
		request.setAttribute("reciever", order.getReciever());
		request.setAttribute("address", order.getAddress());
		request.setAttribute("phone", order.getPhone());
		//请求转发
		request.getRequestDispatcher("editOrder.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
