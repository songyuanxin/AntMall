package cn.edu.svtcc.servlet.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.ProductBus;
import cn.edu.svtcc.domain.Product;

/**
 * Servlet implementation class AdminGoodsListServlet
 * 管理员系统中，获取数据并转发到商品展示页面的逻辑
 */
@WebServlet("/admin/AdminGoodsListServlet")
public class AdminGoodsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminGoodsListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//默认页面为1
		int pageIndex = 1;
		//判断session域中是否存在页面
		if(request.getSession().getAttribute("pageIndex") != null) {
			try {
				//将session中的页码获取
				pageIndex = Integer.valueOf(request.getParameter("pageIndex").toString());
			} catch (Exception e) {
				//若发生异常，便赋值为默认值
				pageIndex = 1;
			}
			
		}
		//设置每页显示的商品数量
		int pageSize = Integer.valueOf(this.getServletContext().getInitParameter("pageSize"));
		//获取所有商品
		List<Product> allGoods = ProductBus.getAllGoods();
		//获取总页数
		int pageNum = allGoods.size()/pageSize;
		//获取分页后的数据
		List<Product> goods = ProductBus.getProductByPage(pageSize, pageIndex);
		//将当前页码放入session域中
		request.getSession().setAttribute("pageIndex", pageIndex);
		//将分页后的商品，总页数放入request域中
		request.setAttribute("Goods", goods);
		request.setAttribute("pageNum", pageNum);
		//请求转发
		request.getRequestDispatcher("admin_goods_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
