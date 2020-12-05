package cn.edu.svtcc.bus;

import java.util.List;

import cn.edu.svtcc.dao.OrderDAOimp;
import cn.edu.svtcc.domain.Order;
import cn.edu.svtcc.interf.OrderDAO;

public class OrderBus {
	public static boolean putOrderData(Order order) {
		OrderDAO oDAO = new OrderDAOimp();
		boolean r = oDAO.putOrderData(order);
		if(r) {
			return true;
		} else {
			return false;
		}
	}
	public static List<Order> getAllOrders(){
		OrderDAO oDAO = new OrderDAOimp();
		List<Order> orderList = oDAO.getAllOrders();
		return orderList;
	}
	public static List<Order> getOrdersByUser(String username){
		OrderDAO oDAO = new OrderDAOimp();
		List<Order> orderList = oDAO.getOrdersByUser(username);
		return orderList;
	}
	public static Order getOrderById(String orderId) {
		OrderDAO oDAO = new OrderDAOimp();
		Order order = oDAO.getOrderById(orderId);
		return order;
	}
	public static boolean updateOrder(Order order) {
		OrderDAO oDAO = new OrderDAOimp();
		boolean r = oDAO.updateOrder(order);
		return r;
	}
	public static boolean deleteOrder(String orderId) {
		OrderDAO oDAO = new OrderDAOimp();
		boolean r = oDAO.deleteOrder(orderId);
		return r;
	}
}
