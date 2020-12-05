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
 * Servlet implementation class AdminAddProviderServlet
 * 管理员系统中，添加供应商的逻辑
 */
@WebServlet("/admin/AdminAddProviderServlet")
public class AdminAddProviderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddProviderServlet() {
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
		//获得供应商id
		Integer providerId = 0;
		try {
			//从页面获取id，强转
			providerId = Integer.valueOf(request.getParameter("provider"));
		} catch(Exception e) {
			e.printStackTrace();
			response.getWriter().write("Error2");
			return;
		}
		//获得供应商名称
		String providerName = request.getParameter("providerName");
		//创建供应商对象并存入数据
		Provider provider = new Provider();
		provider.setProviderId(providerId);
		provider.setProviderName(providerName);
		//获得所有供应商数据
		List<Provider> providerList = ProviderBus.getAllProvider();
		//遍历所以供应商
		for(Provider p : providerList) {
			//判断是否有id或名称重复
			if(p.getProviderId()==providerId || p.getProviderName().equals(providerName)) {
				//重复则返回Error3
				response.getWriter().write("Error3");
				return;
			}
		}
		//添加供应商
		boolean r = ProviderBus.addProvider(provider);
		//添加成功则返回OK
		if(r) {
			response.getWriter().write("OK");
		} else {
			response.getWriter().write("Error");
		}
	}

}
