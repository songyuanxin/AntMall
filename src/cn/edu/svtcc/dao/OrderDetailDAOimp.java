package cn.edu.svtcc.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import cn.edu.svtcc.domain.OrderDetail;
import cn.edu.svtcc.interf.OrderDetailDAO;
/**
 * 实现向数据库中添加订单详情数据的实现类
 * @author Shixuanming
 *
 */
public class OrderDetailDAOimp implements OrderDetailDAO {

	@Override
	public boolean putOrderDetailData(List<OrderDetail> details) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert orderdetails(detailId,orderId,pid,unitPrice,count) value(?,?,?,?,?)";

		try {
			for (OrderDetail detail : details) {
				qr.update(sql, detail.getDetailId(), detail.getOrderId(), detail.getPid(), detail.getUnitPrice(),
						detail.getCount());
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean deleteOrderDetails(String orderId) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "delete from orderdetails where orderId=?";
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
