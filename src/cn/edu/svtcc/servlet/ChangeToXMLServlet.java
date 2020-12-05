package cn.edu.svtcc.servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.svtcc.bus.OrderBus;
import cn.edu.svtcc.domain.Order;

/**
 * Servlet implementation class ChangeToXMLServlet
 * 将订单数据转换成xml文件，为安卓提供接口
 */
@WebServlet("/ChangeToXMLServlet")
public class ChangeToXMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeToXMLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//连接文件
		File f = new File("D:/Java/JavaWeb/Shoping/WebContent/WEB-INF"+File.separator+"inf.xml");
		//判断文件是否存在
		if(!f.exists()) {
			f.createNewFile();
		}
		//获得订单数据
		List<Order> orderList = OrderBus.getAllOrders();
		//获得字节输出流
		FileOutputStream fi = new FileOutputStream(f);
		//字符流
		OutputStreamWriter ir = new OutputStreamWriter(fi,"utf-8");
		//带缓冲区的字符输出流
		BufferedWriter bw = new BufferedWriter(ir);
		//内容写入
		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		bw.write("<orders>\n");
		for(Order order:orderList) {
			bw.write("\t<order>\r\n" + 
					"		<orderId>"+order.getOrderId()+"</orderId>\r\n" + 
					"		<username>"+order.getUsername()+"</username>\r\n" + 
					"		<orderTime>"+order.getOrderTime()+"</orderTime>\r\n" + 
					"		<totalPrice>"+order.getTotalPrice()+"</totalPrice>\r\n" + 
					"		<productNum>"+order.getProductNum()+"</productNum>\r\n" + 
					"		<addres>"+order.getAddress()+"</addres>\r\n" + 
					"		<reciever>"+order.getReciever()+"</reciever>\r\n" + 
					"		<phone>"+order.getPhone()+"</phone>\r\n" + 
					"	</order>\n");
		}
		bw.write("</orders>");
		bw.flush();
		bw.close();
		//请求转发
		request.getRequestDispatcher("WEB-INF/inf.xml").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
