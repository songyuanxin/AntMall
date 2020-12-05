package cn.edu.svtcc.interf;

import java.util.List;

import cn.edu.svtcc.domain.Order;
/**
 * 
 * @author Shixuanming
 *	订单的数据库访问层接口
 */
public interface OrderDAO {
	public boolean putOrderData(Order order);
	public List<Order> getAllOrders();
	public List<Order> getOrdersByUser(String username);
	public Order getOrderById(String orderId);
	public boolean updateOrder(Order order);
	public boolean deleteOrder(String orderId);
}
