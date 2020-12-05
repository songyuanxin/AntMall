package cn.edu.svtcc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Servlet implementation class MallServlet
   * 实现购物车内逻辑
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//告诉浏览器响应方式
		response.setContentType("text/html;charset=utf8");
		//判断是否能够接受到数据
		if(request.getParameter("id")==null ||request.getParameter("count")==null) {
			response.getWriter().print("非法访问！");
		}
		//接收客户端传过来的数据,由于接收到的是String类型,需要强制转换
		//得到详细页面中的商品id
		int id = Integer.valueOf(request.getParameter("id"));
		//得到商品数量
		int count = Integer.valueOf(request.getParameter("count"));
		//找到此id对应的Product对象
		Product pro = ProductBus.getProductByID(id);
		if(pro.getStuck()<count) {
			response.getWriter().print("Error2");
			return;
		}
		//检查登录状态
		if(request.getSession().getAttribute("state")==null) {
			response.getWriter().print("noLogin");
			return;
		}
		//如果第一次访问购物车
		if(request.getSession().getAttribute("mallList")==null) {
			//创建一个双列集合Map<K,V>,K为Product类型对象，V为此商品的数量
			Map<Product,Integer> mallList = new HashMap<Product,Integer>();
			//将该商品的数据放入结合中
			mallList.put(pro,count);
			//创建Session,将集合放入session域中
			request.getSession().setAttribute("mallList", mallList);
			response.getWriter().print("OK");
		} else {
			//之后访问购物车
			//从Session域中得到之前存入的Map<Product,count>集合
			Map<Product,Integer> mallList = (Map<Product,Integer>) request.getSession().getAttribute("mallList");
			//如果商品不存在购物车中
			if(!mallList.containsKey(pro)) {
				//直接将商品加入集合中
				mallList.put(pro, count);
			} else {
				//商品存在购物车中
				int oldCount = mallList.get(pro);
				//将之前该商品的数量取出来加新的商品数量
				int newCount = oldCount + count;
				//放入集合中，由于商品存在，K值唯一，所以将替换之前存在的该商品
				mallList.put(pro, newCount);
			}
			//将集合放入Session域中
			request.getSession().setAttribute("mallList", mallList);
			response.getWriter().print("OK");
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
