package cn.edu.svtcc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.ProductBus;
import cn.edu.svtcc.domain.Product;

/**
 * Servlet implementation class GoodsListServlet
 * 各类商品列表的数据获取逻辑
 */
@WebServlet("/GoodsListServlet")
public class GoodsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//默认当前页为第一页
		int pageIndex = 1;
		//判断session中是否存入了访问页码
		if(request.getSession().getAttribute("GoodsPageIndex") != null) {
			try {
				//获得页面，强转
				pageIndex = Integer.valueOf(request.getParameter("pageIndex").toString());
			} catch (Exception e) {
				pageIndex = 1;
			}
			
		}
		//从全局域获得每页显示的商品数量
		int pageSize = Integer.valueOf(this.getServletContext().getInitParameter("pageSize"))-15;
		//从客户端接收需要展示的商品类别
		/**比如点击电脑办公，客户端传入的类别就为2
		    *点击家具家居，类别就为3
		 */
		int category = Integer.valueOf(request.getParameter("category"));
		//通过接收到的category到数据库中寻找类别为该类别的商品信息
		//并将得到的商品信息放入一个商品集合中
		//该集合中每个元素都是一个Product对象，一个对象就对应数据库中的一行商品数据
		List<Product> goodsList = ProductBus.getAllProductByPage(category,pageSize,pageIndex);
		//获得该类所有商品
		List<Product> allGoods = ProductBus.getAllProduct(category);
		//获得总页数
		int pageNum = allGoods.size()/pageSize;
		//将得到的需要展示的商品列表，实质为一个集合，放入request域中
		request.setAttribute("Goods", goodsList);
		request.setAttribute("category", category);
		request.setAttribute("pageNum", pageNum);
		request.getSession().setAttribute("GoodsPageIndex", pageIndex);
		//获取转发器
		//请求转发，goods_list.jsp
		request.getRequestDispatcher("goods_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
