package cn.edu.svtcc.dao;

import java.sql.SQLException;
import java.util.List;
import java.sql.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.edu.svtcc.domain.Order;
import cn.edu.svtcc.domain.Product;
import cn.edu.svtcc.interf.OrderDAO;
/**
 * 实现向数据库添加订单的实现类
 * @author Shixuanming
 *
 */
public class OrderDAOimp implements OrderDAO{

	@Override
	public boolean putOrderData(Order order) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert orders(orderId,username,orderTime,totalPrice,productNum,address,reciever,phone) values(?,?,?,?,?,?,?,?)";
		try {
			int r = qr.update(sql,order.getOrderId(),order.getUsername(),new Date(System.currentTimeMillis()),order.getTotalPrice(),order.getProductNum(),order.getAddress(),order.getReciever(),order.getPhone());
			if(r>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Order> getAllOrders() {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from orders";
		List<Order> orderList = null;
		try {
			orderList = qr.query(sql, new BeanListHandler<Order>(Order.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public List<Order> getOrdersByUser(String username) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from orders where username=?";
		List<Order> orderList = null;
		try {
			orderList = qr.query(sql, new BeanListHandler<Order>(Order.class),username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public Order getOrderById(String orderId) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from orders where orderId=?";
		Order order = null;
		try {
			order = qr.query(sql, new BeanHandler<Order>(Order.class),orderId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public boolean updateOrder(Order order) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update orders set reciever=?,address=?,phone=? where orderId=?";
		int r = 0;
		try {
			r = qr.update(sql,order.getReciever(),order.getAddress(),order.getPhone(),order.getOrderId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(r>0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteOrder(String orderId) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "delete from orders where orderId=?";
		int r = 0;
		try {
			r = qr.update(sql,orderId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(r>0) {
			return true;
		} else {
			return false;
		}
	}
	
}
