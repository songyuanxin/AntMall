package cn.edu.svtcc.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.CategoryBus;
import cn.edu.svtcc.bus.ProviderBus;
import cn.edu.svtcc.domain.Category;
import cn.edu.svtcc.domain.Provider;

/**
 * Servlet implementation class AdminCategoryServlet
 * 获得类别数据并转发的逻辑
 */
@WebServlet("/admin/AdminCategoryServlet")
public class AdminCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得所有类别
		List<Category> categoryList = CategoryBus.getAllCategory();
		//获得所有供应商
		List<Provider> providerList = ProviderBus.getAllProvider();
		//将类别和供应商放入request域中
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("providerList", providerList);
		//请求转发
		request.getRequestDispatcher("adminAddProduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
