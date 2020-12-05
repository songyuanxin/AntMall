package cn.edu.svtcc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.ProductBus;
import cn.edu.svtcc.dao.ProductDAOimp;
import cn.edu.svtcc.domain.Product;
import cn.edu.svtcc.interf.ProductDAO;

/**
 * Servlet implementation class IndexGoodsServlet
 * 商品展示主页的逻辑
 */
@WebServlet("/IndexGoodsServlet")
public class IndexGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从客户端接收数据，显示的商品的类别，主页的类别就为1
		int category = 1;
		//从数据库中找出所有该类别的商品，放入List集合中，该集合每个元素都为Product对象
		//实质数据库中每一行都背封装成是一个Product对象
		List<Product> goodsList = ProductBus.getAllProduct(category);
		//截取该集合的前5个商品
		List<Product> showGoods = goodsList.subList(0, 5);
		//截取该集合除了前五个，之后的商品
		goodsList = goodsList.subList(6,goodsList.size());
		//将两个截取后的集合放入request域对象中
		//两个截取实质是为了分别展示热销商品和秒杀商品
		request.setAttribute("Goods", goodsList);
		request.setAttribute("Show", showGoods);
		//获取转发器
		//请求转发,index.jsp
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
