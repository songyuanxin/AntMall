package cn.edu.svtcc.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.ProviderBus;
import cn.edu.svtcc.domain.Provider;

/**
 * Servlet implementation class AdminUpdateProviderServlet
 * 管理员系统中，修改供应商信息的逻辑
 */
@WebServlet("/admin/AdminUpdateProviderServlet")
public class AdminUpdateProviderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdateProviderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 通过get的形式访问，则是主要负责获取数据，并转发到页面
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从页面获取供应商id，强转
		Integer providerId = Integer.valueOf(request.getParameter("providerId"));
		//通过id获取对应供应商的信息
		Provider provider = ProviderBus.getProviderById(providerId);
		//将获取到的供应商对象放入request域中
		request.setAttribute("provider", provider);
		//请求转发
		request.getRequestDispatcher("admin_editProvider.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 通过post的形式访问，是修改供应商信息的逻辑
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从页面获取供应商id，强转
		Integer providerId = Integer.valueOf(request.getParameter("id"));
		//获取供应商名称
		String providerName = request.getParameter("providerName");
		//创建供应商对象，并存入数据
		Provider provider = new Provider();
		provider.setProviderId(providerId);
		provider.setProviderName(providerName);
		//获取所有供应商信息
		List<Provider> providerList = ProviderBus.getAllProvider();
		//遍历所有供应商
		for(Provider p : providerList) {
			//判断修改的供应商名称是否与之前的有重复
			if(p.getProviderName().equals(providerName)) {
				//有重复则返回给页面信息Error2
				response.getWriter().write("Error2");
				return;
			}
		}
		//修改供应商信息
		boolean r = ProviderBus.updateProvider(provider);
		//修改成功，则返回页面信息OK
		if(r) {
			response.getWriter().write("OK");
		} else {
			response.getWriter().write("Error");
		}
	}

}
