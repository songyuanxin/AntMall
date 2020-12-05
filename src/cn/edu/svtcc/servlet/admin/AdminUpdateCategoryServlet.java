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
 * Servlet implementation class AdminUpdateCategoryServlet
 * 管理员系统中，修改类别信息的逻辑
 */
@WebServlet("/admin/AdminUpdateCategoryServlet")
public class AdminUpdateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdateCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 通过get形式访问时，功能是获取数据转发给页面
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从页面获取类别id，强转
		int id = Integer.valueOf(request.getParameter("categoryId"));
		//通过id寻找对应的类别信息
		Category category = CategoryBus.getCategoryById(id);
		//将该类别对象放入request域中
		request.setAttribute("category", category);
		//请求转发
		request.getRequestDispatcher("admin_editCategory.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 通过post形式访问时，是处理修改类别信息的逻辑
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从页面获取类别编号
		int id = Integer.valueOf(request.getParameter("id"));
		//获取类别名称
		String categoryName = request.getParameter("categoryName");
		//创建类别对象，并存入数据
		Category category = new Category();
		category.setCategory(id);
		category.setCategoryName(categoryName);
		//获得所有类别信息
		List<Category> categoryList = CategoryBus.getAllCategory();
		//遍历所有类别
		for(Category c:categoryList) {
			//判断修改后的类别名称是否与之前的有重复
			if(c.getCategoryName().equals(category.getCategoryName())) {
				//重复则返回给页面信息Error2
				response.getWriter().write("Error2");
				return;
			}
		}
		//修改类别信息
		boolean r = CategoryBus.updateCategory(category);
		//修改成功，则返回信息OK
		if(r) {
			response.getWriter().write("OK");
		} else {
			response.getWriter().write("Error");
		}
	}

}
