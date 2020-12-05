package cn.edu.svtcc.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.CategoryBus;
import cn.edu.svtcc.bus.ProductBus;
import cn.edu.svtcc.bus.ProviderBus;
import cn.edu.svtcc.domain.Category;
import cn.edu.svtcc.domain.Product;
import cn.edu.svtcc.domain.Provider;

/**
 * Servlet implementation class AdminGetGoodToEditServlet
 * 管理员系统中，将数据获取并转发到编辑页面的逻辑
 */
@WebServlet("/admin/AdminGetGoodToEditServlet")
public class AdminGetGoodToEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminGetGoodToEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从页面获取商品id
		int id = 0;
		try {
			id = Integer.valueOf(request.getParameter("id"));
		} catch(Exception e) {
			id = 0;
		}
		//通过id寻找对应商品，判断是否存在
		if(ProductBus.getProductByID(id)!=null) {
			//如果存在，将商品得到
			Product pro = ProductBus.getProductByID(id);
			//得到所有类别
			List<Category> categoryList = CategoryBus.getAllCategory();
			//得到所有供应商
			List<Provider> providerList = ProviderBus.getAllProvider();
			//将对应商品，所有类别，所有供应商放入request域中
			request.setAttribute("Product", pro);
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("providerList", providerList);
			//请求转发
			request.getRequestDispatcher("admin_editGood.jsp").forward(request, response);
		} else {
			//若对应商品不存在
			response.getWriter().print("id不存在");
			//自动刷新，跳转到新的页面
			response.setHeader("refresh", "2;AdminGoodsListServlet?pageIndex=1");
		}
	}

}
