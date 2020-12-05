package cn.edu.svtcc.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import cn.edu.svtcc.domain.Order;
import cn.edu.svtcc.interf.ReturnDAO;
/**
 * 回滚实现类
 * 无视
 * @author Shixuanming
 *
 */
public class ReturnDAOimp implements ReturnDAO{

	@Override
	public boolean reDatabase(Order order) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql1 = "delete from orders where orderId=?";
		String sql2 = "delete from orderdetails where orderId=?";
		try {
			int r1 = qr.update(sql1,order.getOrderId());
			int r2 = qr.update(sql2,order.getOrderId());
			if((r1 + r2)!=0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
