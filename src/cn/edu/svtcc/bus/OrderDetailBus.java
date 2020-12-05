package cn.edu.svtcc.bus;

import java.util.List;

import cn.edu.svtcc.dao.OrderDetailDAOimp;
import cn.edu.svtcc.domain.OrderDetail;
import cn.edu.svtcc.interf.OrderDetailDAO;

public class OrderDetailBus {
	public static boolean putOrderDetailData(List<OrderDetail> details) {
		OrderDetailDAO dDAO = new OrderDetailDAOimp();
		boolean r = dDAO.putOrderDetailData(details);
		if(r) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean deleteOrderDetails(String orderId) {
		OrderDetailDAO dDAO = new OrderDetailDAOimp();
		boolean r = dDAO.deleteOrderDetails(orderId);
		if(r) {
			return true;
		} else {
			return false;
		}
	}
}
