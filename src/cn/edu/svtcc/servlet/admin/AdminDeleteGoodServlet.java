package cn.edu.svtcc.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.ProductBus;

/**
 * Servlet implementation class AdminDeleteGoodServlet
 * 管理员系统中，删除商品的逻辑
 */
@WebServlet("/admin/AdminDeleteGoodServlet")
public class AdminDeleteGoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteGoodServlet() {
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
		//从页面获取商品id，强转
		int id = Integer.valueOf(request.getParameter("id"));
		//通过id删除商品，删除成功返回信息OK
		if(ProductBus.deleteProductByID(id)) {
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("error");
		}
	}

}
