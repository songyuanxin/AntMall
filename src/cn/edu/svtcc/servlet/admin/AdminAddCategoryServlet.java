package cn.edu.svtcc.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.CategoryBus;
import cn.edu.svtcc.domain.Category;

/**
 * Servlet implementation class AdminAddCategoryServlet
 * 管理员系统中，管理添加类别的逻辑
 */
@WebServlet("/admin/AdminAddCategoryServlet")
public class AdminAddCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddCategoryServlet() {
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
		/**
		 * 获得类别id
		 */
		Integer categoryId = 0;
		try {
			//从页面获得
			categoryId = Integer.valueOf(request.getParameter("category"));
		} catch(Exception e) {
			e.printStackTrace();
			response.getWriter().write("Error2");
			return;
		}
		//从页面获得类别名称
		String categoryName = request.getParameter("categoryName");
		//创建类别对象，并存入数据
		Category category = new Category();
		category.setCategory(categoryId);
		category.setCategoryName(categoryName);
		//获得所有类别
		List<Category> categoryList = CategoryBus.getAllCategory();
		//遍历所有类别
		for(Category c:categoryList) {
			//判断是否有重复id和名称
			if(c.getCategoryName().equals(category.getCategoryName()) || c.getCategory()==categoryId) {
				response.getWriter().write("Error3");
				return;
			}
		}
		//添加类别
		boolean r = CategoryBus.addCategory(category);
		//添加成功则返回信息OK
		if(r) {
			response.getWriter().write("OK");
		} else {
			response.getWriter().write("Error");
		}
	}

}
