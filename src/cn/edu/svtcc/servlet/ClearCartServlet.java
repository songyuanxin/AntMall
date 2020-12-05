package cn.edu.svtcc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.OrderBus;
import cn.edu.svtcc.bus.OrderDetailBus;
import cn.edu.svtcc.bus.ProductBus;
import cn.edu.svtcc.bus.ReturnBus;
import cn.edu.svtcc.domain.Order;
import cn.edu.svtcc.domain.OrderDetail;
import cn.edu.svtcc.domain.Product;
import cn.edu.svtcc.domain.User;

/**
 * Servlet implementation class clearCartServlet 
 * 清空购物车的逻辑
 */
@WebServlet("/ClearCartServlet")
public class ClearCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClearCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().write("Error");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到客户端发来的数据
		/**
		 * 接收人，地址，电话，商品总价格
		 */
		String reciever = request.getParameter("reciever");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String totalPrice = request.getParameter("totalPrice");
		//从session域中取出之前加入的Map集合，由于是得到的是object类型，所以强转为map
		Map<Product,Integer> goods = (Map)request.getSession().getAttribute("mallList");
		//计数器
		int count = 0;
		//创建订单对象
		Order order = new Order();
		//从session域中获得用户对象
		User user = (User)request.getSession().getAttribute("user");
		/**
		 *  设置订单中的信息
		 */
		//订单编号，随机的UUID
		order.setOrderId(UUID.randomUUID().toString());
		//操作用户，从session中取出当前登录的用户名，得到object强转为String
		order.setUsername(user.getUsername());
		//设置总价格
		order.setTotalPrice(Double.parseDouble(totalPrice));
		//设置商品种类
		order.setProductNum(goods.size());
		//设置收件人
		order.setReciever(reciever);
		//设置地址
		order.setAddress(address);
		//设置电话
		order.setPhone(phone);
		//创建订单详情列表，泛型为订单详情的对象
		//该列表包括该订单中的所有订单详情
		List<OrderDetail> details = new ArrayList<>();
		//使用foreach迭代购物车中的产品对象
		//使用keySet()方法将Map对象中的K转换为一个Set集合，该集合中的每一个元素都是一个Product对象
		for(Product p:goods.keySet()) {
			//创建单个订单详情对象
			OrderDetail detail = new OrderDetail();
			//设置订单详情编号，使用UUID
			detail.setDetailId(UUID.randomUUID().toString());
			//设置该当前订单详情属于的订单编号
			detail.setOrderId(order.getOrderId());
			//设置产品编号
			detail.setPid(p.getPid());
			//设置产品单价，由于Product实体类中的Price为String类型，Orderdetail类中的UnitPrice为double类型，所以要强转为Double类型
			//由于商城全场八折，单价*0.8
			detail.setUnitPrice(Double.parseDouble(p.getPprice())*0.8);
			//设置商品数量
			detail.setCount(goods.get(p));
			//将该订单详情加入订单详情集合中
			details.add(detail);
		}
		List<Product> noGoods = new ArrayList<>();
		//修改產品表中的商品库存，判断是否执行成功，成功则计数器+1
		for(Product p:goods.keySet()) {
			p.setStuck(p.getStuck()-goods.get(p));
			if(ProductBus.updateProduct(p)) {
				count++;
			}
		}
		//调用向数据库orders表中加数据的方法，判断是否执行成功，成功则计数器+1
		if(OrderBus.putOrderData(order)) {
			count++;
		}
		//调用向数据库orderdetails表中添加数据的方法，判断是否执行成功，成功则计数器+1
		if(OrderDetailBus.putOrderDetailData(details)) {
			count ++;
		}
		//如果三个操作都执行成功，则计数器为3
		//判断是否两个操作是否执行成功
		if(count>=3) {
			//如果成功将信息加入数据库中，则将session中的商品信息清除（换句话说就是清空购物车）
			request.getSession().removeAttribute("mallList");
			response.getWriter().print("OK");
		} else {
			//如果执行失败，则将之前执行成功添加的数据删除
			//该方法不知道是否正确，所以可以无视他，正常情况下apach有回滚数据的方法
			ReturnBus.reDatabase(order);
			response.getWriter().print("Error");
		}
	}

}
