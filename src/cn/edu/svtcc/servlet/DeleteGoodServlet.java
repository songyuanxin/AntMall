package cn.edu.svtcc.servlet;

import java.io.IOException;
import java.util.HashMap;

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
 * Servlet implementation class deleteGoodServlet
 * 删除购物车中商品的逻辑
 */
@WebServlet("/DeleteGoodServlet")
public class DeleteGoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到客户端接收到的要删除商品的id
		int id = Integer.valueOf(request.getParameter("id"));
		//通过id调用在数据库查询单个商品数据的方法，得到一个Product对象
		Product pro = ProductBus.getProductByID(id);
		//从session域中得到购物车信息，实质为一个Map<K,V>列表，K为Product对象，V为商品数量
		HashMap<Product,Integer> mallList = (HashMap<Product,Integer>)request.getSession().getAttribute("mallList");
		//在Map集合中（购物车中）找到该商品，将他从集合中移除
		mallList.remove(pro);
		if(mallList.size()>0) {
			//将移除该商品后的集合重新放入session域中，替换之前的集合
			request.getSession().setAttribute("mallList", mallList);
			response.sendRedirect("cart.jsp");
		} else {
			request.getSession().removeAttribute("mallList");
			response.sendRedirect("cart.jsp");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
